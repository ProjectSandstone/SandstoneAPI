/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2016 Sandstone <https://github.com/ProjectSandstone/>
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

import com.github.jonathanxd.adapter.utils.classu.CodeAPIUtil
import com.github.jonathanxd.codeapi.CodeAPI
import com.github.jonathanxd.codeapi.CodePart
import com.github.jonathanxd.codeapi.CodeSource
import com.github.jonathanxd.codeapi.MutableCodeSource
import com.github.jonathanxd.codeapi.common.CodeArgument
import com.github.jonathanxd.codeapi.common.CodeParameter
import com.github.jonathanxd.codeapi.gen.value.source.PlainSourceGenerator
import com.github.jonathanxd.codeapi.gen.visit.bytecode.BytecodeGenerator
import com.github.jonathanxd.codeapi.helper.PredefinedTypes
import com.github.jonathanxd.codeapi.interfaces.MethodInvocation
import com.github.jonathanxd.codeapi.literals.Literals
import com.github.jonathanxd.codeapi.types.Generic
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

object MethodListenerGen {
    private val regex = Regex("[^A-Za-z\\\\._$]")

    fun create(plugin: PluginContainer, method: Method, instance: Any?, listenerData: ListenerData): EventListener<Event> {

        val `class` = this.createClass(plugin, instance, method, listenerData)

        val isStatic = Modifier.isStatic(method.modifiers)

        return if (!isStatic) {
            `class`.getConstructor(instance!!.javaClass).newInstance(instance)
        } else {
            `class`.newInstance()
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
                .withModifiers(Modifier.PUBLIC)
                .withQualifiedName(name)
                .withImplementations(Generic.type(EventListener::class.java.toType()).of(eventType.toGeneric()))
                .withBody(genBody(method, instance, listenerData))
                .build()

        val source = CodeAPI.sourceOfParts(codeClass)

        val generator = BytecodeGenerator()

        val bytes = generator.gen(source)[0].bytecode

        val definedClass = EventGenClassLoader.defineClass(codeClass, bytes, lazy { PlainSourceGenerator().gen(source) }) as SandstoneClass<EventListener<Event>>

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
            val instanceType = instance!!.javaClass.toType()

            source.add(CodeAPI.field(Modifier.PRIVATE or Modifier.FINAL, instanceType, instanceFieldName))
            source.add(CodeAPI.constructorBuilder()
                    .withModifiers(Modifier.PUBLIC)
                    .withParameter(CodeParameter(instanceFieldName, instanceType))
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

            val namedParameters = listenerData.namedParameters

            val arguments = mutableListOf<CodeArgument>()

            val accessEventVar = CodeAPI.accessLocalVariable(eventType, eventVariableName)

            arguments.add(CodeAPI.argument(CodeAPI.cast(eventType, namedParameters[0].value.aClass.toType(), accessEventVar)))

            namedParameters.forEachIndexed { i, named ->
                if (i > 0) {
                    val name = named.name
                    val typeInfo = named.value

                    val toAdd: CodeArgument

                    if (typeInfo.aClass == Property::class.java
                            && typeInfo.related.isNotEmpty()) {
                        toAdd = CodeArgument(this.callGetPropertyDirectOn(accessEventVar, name, typeInfo.related[0].aClass, true))
                    } else {
                        toAdd = CodeArgument(this.callGetPropertyDirectOn(accessEventVar, name, typeInfo.aClass, false))
                    }

                    arguments.add(toAdd)
                }
            }

            val argsArray = arguments.toTypedArray()
            if (isStatic) {
                body.add(CodeAPIUtil.invokeStaticMethod(method, *argsArray))
            } else {
                body.add(CodeAPIUtil.invokeVirtualMethod(CodeAPI.accessThisField(instance!!.javaClass.toType(), instanceFieldName), method, *argsArray))
            }

            return body
        }

        val onEvent = CodeAPI.methodBuilder()
                .withModifiers(Modifier.PUBLIC)
                .withBody(genOnEventBody())
                .withReturnType(PredefinedTypes.VOID)
                .withName("onEvent")
                .withParameters(
                        CodeAPI.parameter(eventType, eventVariableName), CodeAPI.parameter(PluginContainer::class.java, pluginContainerVariableName)
                )
                .build()

        source.add(onEvent)

        val isBeforeModsMethod = CodeAPI.methodBuilder()
                .withModifiers(Modifier.PUBLIC)
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(PredefinedTypes.BOOLEAN, Literals.BOOLEAN(listenerData.beforeModifications))
                ))
                .withName("isBeforeModifications")
                .withReturnType(Boolean::class.javaPrimitiveType)
                .build()

        source.add(isBeforeModsMethod)

        val getPriorityMethod = CodeAPI.methodBuilder()
                .withModifiers(Modifier.PUBLIC)
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(EventPriority::class.java,
                                CodeAPI.accessStaticField(EventPriority::class.java, EventPriority::class.java, listenerData.priority.name)
                        )
                ))
                .withName("getPriority")
                .withReturnType(EventPriority::class.java)
                .build()

        source.add(getPriorityMethod)

        val ignoreCancelledMethod = CodeAPI.methodBuilder()
                .withModifiers(Modifier.PUBLIC)
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(PredefinedTypes.BOOLEAN, Literals.BOOLEAN(listenerData.ignoreCancelled))
                ))
                .withName("ignoreCancelled")
                .withReturnType(Boolean::class.javaPrimitiveType)
                .build()

        source.add(ignoreCancelledMethod)

        return source
    }

    private fun callGetPropertyDirectOn(target: CodePart, name: String, type: Class<*>, propertyOnly: Boolean): MethodInvocation {
        val getPropertyMethod = CodeAPI.invokeInterface(PropertyHolder::class.java, target, "getProperty",
                CodeAPI.typeSpec(Property::class.java, Class::class.java, String::class.java),
                CodeAPI.argument(Literals.CLASS(type)), CodeAPI.argument(Literals.STRING(name))
        )

        if (propertyOnly)
            return getPropertyMethod

        return CodeAPI.invokeInterface(GetterProperty::class.java, CodeAPI.cast(Property::class.java, GetterProperty::class.java, getPropertyMethod),
                "getValue",
                CodeAPI.typeSpec(Any::class.java))
    }
}