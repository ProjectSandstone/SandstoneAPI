/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) Sandstone <https://github.com/ProjectSandstone/>
 *      Copyright (c) contributors
 *
 *
 *      Permission is hereby granted, free of charge, to any person obtaining a copy
 *      of this software and associated documentation files (the "Software"), to deal
 *      in the Software without restriction, including without limitation the rights
 *      to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *      copies of the Software, and to permit persons to whom the Software is
 *      furnished to do so, subject to the following conditions:
 *
 *      The above copyright notice and this permission notice shall be included in
 *      all copies or substantial portions of the Software.
 *
 *      THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *      IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *      FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *      AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *      LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *      OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *      THE SOFTWARE.
 */
package com.github.projectsandstone.api.util.internal.gen.event.listener

import com.github.jonathanxd.codeapi.*
import com.github.jonathanxd.codeapi.base.MethodInvocation
import com.github.jonathanxd.codeapi.base.Typed
import com.github.jonathanxd.codeapi.base.impl.ReturnImpl
import com.github.jonathanxd.codeapi.bytecode.VISIT_LINES
import com.github.jonathanxd.codeapi.bytecode.VisitLineType
import com.github.jonathanxd.codeapi.bytecode.extra.Dup
import com.github.jonathanxd.codeapi.bytecode.extra.Pop
import com.github.jonathanxd.codeapi.bytecode.gen.BytecodeGenerator
import com.github.jonathanxd.codeapi.common.CodeModifier
import com.github.jonathanxd.codeapi.common.CodeParameter
import com.github.jonathanxd.codeapi.conversions.createInvocation
import com.github.jonathanxd.codeapi.conversions.createStaticInvocation
import com.github.jonathanxd.codeapi.factory.field
import com.github.jonathanxd.codeapi.literal.Literals
import com.github.jonathanxd.codeapi.type.Generic
import com.github.jonathanxd.codeapi.util.Stack
import com.github.jonathanxd.codeapi.util.codeType
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.event.Event
import com.github.projectsandstone.api.event.EventListener
import com.github.projectsandstone.api.event.EventPriority
import com.github.projectsandstone.api.event.ListenerData
import com.github.projectsandstone.api.event.property.GetterProperty
import com.github.projectsandstone.api.event.property.Property
import com.github.projectsandstone.api.event.property.PropertyHolder
import com.github.projectsandstone.api.plugin.PluginContainer
import com.github.projectsandstone.api.util.internal.Debug
import com.github.projectsandstone.api.util.internal.gen.SandstoneClass
import com.github.projectsandstone.api.util.internal.gen.event.EventGenClassLoader
import com.github.projectsandstone.api.util.internal.gen.save.ClassSaver
import com.github.projectsandstone.api.util.toGeneric
import com.github.projectsandstone.api.util.toType
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.*

object MethodListenerGen {
    private val regex = Regex("[^A-Za-z\\\\._$]")

    fun create(plugin: PluginContainer, method: Method, instance: Any?, listenerData: ListenerData): EventListener<Event> {

        val klass = this.createClass(plugin, instance, method, listenerData)

        val isStatic = Modifier.isStatic(method.modifiers)

        return if (!isStatic) {
            try {
                klass.classLoader.loadClass(instance!!::class.java.canonicalName)
            } catch (e: ClassNotFoundException) {
                throw IllegalStateException("Cannot lookup for Plugin class: '${instance!!::class.java}' from class loader: '${klass.classLoader}'")
            }

            klass.getConstructor(instance::class.java).newInstance(instance)
        } else {
            klass.newInstance()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun createClass(plugin: PluginContainer, instance: Any?, method: Method, listenerData: ListenerData): Class<EventListener<Event>> {
        val baseCanonicalName = "${EventListener::class.java.`package`.name}.generated."
        val declaringName = method.declaringClass.canonicalName.replace('.', '_')
        val cleanId = plugin.id.replace(regex, "_")

        val name = "$baseCanonicalName${cleanId}_${declaringName}_${method.name}"

        val eventType = listenerData.eventType

        val codeClass = CodeAPI.aClassBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withQualifiedName(name)
                .withImplementations(Generic.type(EventListener::class.java.toType()).of(eventType.toGeneric()))
                .withSuperClass(Types.OBJECT)
                .withBody(genBody(method, instance, listenerData))
                .build()

        val source = CodeAPI.sourceOfParts(codeClass)

        val generator = BytecodeGenerator()

        generator.options.set(VISIT_LINES, VisitLineType.FOLLOW_CODE_SOURCE)

        val bytecodeClass = generator.gen(source)[0]

        val bytes = bytecodeClass.bytecode

        val definedClass = EventGenClassLoader.defineClass(codeClass, bytes, lazy { bytecodeClass.disassembledCode }, (plugin.classLoader as ClassLoader)) as SandstoneClass<EventListener<Event>>

        if (Debug.LISTENER_GEN_DEBUG) {
            ClassSaver.save(Sandstone.sandstonePath, "listenergen", definedClass)
        }

        return definedClass.javaClass
    }

    private const val eventVariableName: String = "event"
    private const val pluginContainerVariableName: String = "pluginContainer"
    private const val instanceFieldName: String = "\$instance"

    private fun genBody(method: Method, instance: Any?, listenerData: ListenerData): CodeSource {
        val source = MutableCodeSource()

        val isStatic = Modifier.isStatic(method.modifiers)

        if (!isStatic) {
            val instanceType = instance!!::class.java.toType()

            source.add(field(EnumSet.of(CodeModifier.PRIVATE, CodeModifier.FINAL), instanceType, instanceFieldName))
            source.add(CodeAPI.constructorBuilder()
                    .withModifiers(CodeModifier.PUBLIC)
                    .withParameters(CodeParameter(instanceType, instanceFieldName))
                    .withBody(CodeAPI.sourceOfParts(
                            CodeAPI.setThisField(instanceType, instanceFieldName, CodeAPI.accessLocalVariable(instanceType, instanceFieldName))
                    ))
                    .build()
            )
        }

        source.addAll(genMethods(method, instance, listenerData))

        return source
    }

    private fun genMethods(method: Method, instance: Any?, listenerData: ListenerData): CodeSource {
        val source = MutableCodeSource()

        val isStatic = Modifier.isStatic(method.modifiers)
        val eventType = Event::class.java.toType()

        // This is hard to maintain, but, is funny :D
        fun genOnEventBody(): CodeSource {
            val body = MutableCodeSource()

            val parameters = listenerData.parameters

            val arguments = mutableListOf<CodePart>()

            val accessEventVar = CodeAPI.accessLocalVariable(eventType, eventVariableName)

            arguments.add(CodeAPI.cast(eventType, parameters[0].type.aClass.toType(), accessEventVar))

            parameters.forEachIndexed { i, param ->
                if (i > 0) {
                    val name = param.name
                    val typeInfo = param.type

                    val toAdd: CodePart

                    if (typeInfo.aClass == Property::class.java
                            && typeInfo.related.isNotEmpty()) {
                        toAdd = this.callGetPropertyDirectOn(accessEventVar, name, typeInfo.related[0].aClass, true, param.isNullable)
                    } else {
                        toAdd = this.callGetPropertyDirectOn(accessEventVar, name, typeInfo.aClass, false, param.isNullable)
                    }

                    arguments.add(toAdd)
                }
            }


            if (isStatic) {
                body.add(method.createStaticInvocation(arguments))
            } else {
                body.add(method.createInvocation(CodeAPI.accessThisField(instance!!::class.java.toType(), instanceFieldName), arguments))
            }

            return body
        }

        val onEvent = CodeAPI.methodBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withBody(genOnEventBody())
                .withReturnType(Types.VOID)
                .withName("onEvent")
                .withParameters(
                        CodeAPI.parameter(eventType, eventVariableName), CodeAPI.parameter(PluginContainer::class.java, pluginContainerVariableName)
                )
                .build()

        source.add(onEvent)

        val isBeforeModsMethod = CodeAPI.methodBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(Types.BOOLEAN, Literals.BOOLEAN(listenerData.beforeModifications))
                ))
                .withName("isBeforeModifications")
                .withReturnType(Types.BOOLEAN)
                .build()

        source.add(isBeforeModsMethod)

        val getPriorityMethod = CodeAPI.methodBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(EventPriority::class.java,
                                CodeAPI.accessStaticField(EventPriority::class.java, EventPriority::class.java, listenerData.priority.name)
                        )
                ))
                .withName("getPriority")
                .withReturnType(EventPriority::class.java.codeType)
                .build()

        source.add(getPriorityMethod)

        val ignoreCancelledMethod = CodeAPI.methodBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(Types.BOOLEAN, Literals.BOOLEAN(listenerData.ignoreCancelled))
                ))
                .withName("ignoreCancelled")
                .withReturnType(Types.BOOLEAN)
                .build()

        source.add(ignoreCancelledMethod)

        return source
    }

    private fun callGetPropertyDirectOn(target: CodePart, name: String, type: Class<*>, propertyOnly: Boolean, isNullable: Boolean): CodePart {
        val getPropertyMethod = CodeAPI.invokeInterface(PropertyHolder::class.java, target,
                if(propertyOnly) "getProperty" else "getGetterProperty",
                CodeAPI.typeSpec(if(propertyOnly) Property::class.java else GetterProperty::class.java, Class::class.java, String::class.java),
                listOf(Literals.CLASS(type), Literals.STRING(name))
        )

        val elsePart = if(isNullable) Literals.NULL else CodeAPI.returnVoid()

        val getPropMethod = checkNull(getPropertyMethod, elsePart)

        if(propertyOnly)
            return getPropMethod

        return CodeAPI.ifStatement(CodeAPI.checkNotNull(Dup(getPropMethod, GetterProperty::class.codeType)),
                // Body
                CodeAPI.source(CodeAPI.invokeInterface(GetterProperty::class.java, Stack,
                        "getValue",
                        CodeAPI.typeSpec(Any::class.java),
                        emptyList())),
                // Else
                CodeAPI.source(
                        Pop,
                        elsePart
                ))

    }

    private fun checkNull(part: Typed, else_: CodePart) = CodeAPI.ifStatement(CodeAPI.checkNotNull(Dup(part)), CodeAPI.source(Stack), CodeAPI.source(Pop, else_))
}