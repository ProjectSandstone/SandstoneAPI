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
package com.github.projectsandstone.api.util.internal.gen.event

import com.github.jonathanxd.codeapi.CodeAPI
import com.github.jonathanxd.codeapi.CodePart
import com.github.jonathanxd.codeapi.MutableCodeSource
import com.github.jonathanxd.codeapi.builder.ClassDeclarationBuilder
import com.github.jonathanxd.codeapi.bytecode.VISIT_LINES
import com.github.jonathanxd.codeapi.bytecode.VisitLineType
import com.github.jonathanxd.codeapi.bytecode.gen.BytecodeGenerator
import com.github.jonathanxd.codeapi.common.CodeModifier
import com.github.jonathanxd.codeapi.common.CodeParameter
import com.github.jonathanxd.codeapi.common.InvokeType
import com.github.jonathanxd.codeapi.common.TypeSpec
import com.github.jonathanxd.codeapi.conversions.parameterNames
import com.github.jonathanxd.codeapi.conversions.toCodeArgument
import com.github.jonathanxd.codeapi.conversions.toMethodDeclaration
import com.github.jonathanxd.codeapi.literal.Literals
import com.github.jonathanxd.codeapi.util.codeType
import com.github.jonathanxd.iutils.type.TypeUtil
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.event.Cancellable
import com.github.projectsandstone.api.event.Event
import com.github.projectsandstone.api.event.annotation.Mutable
import com.github.projectsandstone.api.event.annotation.Name
import com.github.projectsandstone.api.util.internal.Debug
import com.github.projectsandstone.api.util.internal.gen.SandstoneClass
import com.github.projectsandstone.api.util.internal.gen.event.EventFactoryClassGen.create
import com.github.projectsandstone.api.util.internal.gen.save.ClassSaver
import com.github.projectsandstone.api.util.internal.getImplementation
import com.github.projectsandstone.api.util.internal.parameterNames
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

/**
 * This class generates an implementation of an event factory, this method will create the event class
 * and direct-call the constructor instead of calling the [SandstoneEventGen.gen].
 *
 * Additional properties that are mutable must be annotated with [com.github.projectsandstone.api.event.annotation.Mutable] annotation.
 *
 * Creating our own event factory:
 *
 * - Create an factory interface (you could use default methods of Java 8, or Kotlin default functions).
 * - Call [create] method and cache the factory instance.
 * - Use cached factory instance to create events.
 *
 * Example: [com.github.projectsandstone.api.event.SandstoneEventFactory]
 */
object EventFactoryClassGen {


    fun <T : Any> create(factoryClass: Class<T>): T {
        val superClass = factoryClass.superclass

        if (!factoryClass.isInterface)
            throw IllegalArgumentException("Factory class must be an interface.")

        if (superClass != null && factoryClass != Any::class.java || factoryClass.interfaces.isNotEmpty())
            throw IllegalArgumentException("Factory class must not extend any class.")

        val body = MutableCodeSource()

        val declaration = ClassDeclarationBuilder.builder()
                .withModifiers(CodeModifier.PUBLIC)
                .withQualifiedName("${factoryClass.canonicalName}\$Impl")
                .withImplementations(factoryClass.codeType)
                .withBody(body)
                .build()

        factoryClass.declaredMethods.forEach {
            if (!it.isDefault) {

                val kFunc = it.kotlinFunction
                val cl = it.declaringClass.kotlin

                val impl = kFunc?.let { getImplementation(cl, it) }

                if (kFunc != null && impl != null) {
                    val base = kFunc
                    val delegateClass = impl.first
                    val delegate = impl.second

                    val parameters = base.parameterNames.mapIndexed { i, it ->
                        CodeParameter(delegate.parameters[i + 1].type.codeType, it)
                    }

                    val arguments = mutableListOf<CodePart>(CodeAPI.accessThis()) + parameters.map { it.toCodeArgument() }

                    val invoke = CodeAPI.invoke(
                            InvokeType.INVOKE_STATIC,
                            delegateClass.codeType,
                            CodeAPI.accessStatic(),
                            delegate.name,
                            TypeSpec(delegate.returnType.codeType, delegate.parameters.map { it.type.codeType }),
                            arguments
                    ).let {
                        if (kFunc.returnType.jvmErasure.java == Void.TYPE)
                            it
                        else
                            CodeAPI.returnValue(kFunc.returnType.jvmErasure.codeType, it)
                    }

                    val methodDeclaration = it.toMethodDeclaration()
                    val methodBody = methodDeclaration.body as MutableCodeSource
                    methodBody.add(invoke)

                    body.add(methodDeclaration)
                } else {

                    val eventType = it.returnType
                    val ktNames = it.parameterNames

                    if (!Event::class.java.isAssignableFrom(eventType))
                        throw IllegalArgumentException("Failed to generate implementation of method '$it': event factory methods must return a type assignable to 'Event'.")

                    val parameterNames = it.parameters.mapIndexed { i, it ->
                        if (it.isAnnotationPresent(Name::class.java))
                            it.getDeclaredAnnotation(Name::class.java).value
                        else
                            ktNames[i]
                    }

                    val properties = SandstoneEventGenUtil.getProperties(eventType)
                    val additionalProperties = mutableListOf<PropertyInfo>()

                    it.parameters.forEachIndexed { i, parameter ->
                        val find = properties.any { it.propertyName == parameterNames[i] && it.type == parameter.type }

                        if (!find) {
                            val name = parameterNames[i]

                            val getterName = "get${name.capitalize()}"
                            val setterName = if (parameter.isAnnotationPresent(Mutable::class.java)) "set${name.capitalize()}" else null


                            additionalProperties += PropertyInfo(
                                    propertyName = name,
                                    type = parameter.type,
                                    getterName = getterName,
                                    setterName = setterName
                            )
                        }
                    }

                    val eventTypeInfo = TypeUtil.toReference(it.genericReturnType)

                    val impl = SandstoneEventGenUtil.genImplementation(eventTypeInfo, additionalProperties)

                    val methodDeclaration = it.toMethodDeclaration()
                    val methodBody = methodDeclaration.body as MutableCodeSource

                    val jClass = impl.javaClass

                    val ctr = jClass.declaredConstructors[0]

                    val arguments = ctr.parameters.map {
                        val name = it.getDeclaredAnnotation(Name::class.java).value

                        if (name == "cancelled"
                                && it.type == java.lang.Boolean.TYPE
                                && Cancellable::class.java.isAssignableFrom(eventType))
                            return@map Literals.FALSE

                        return@map CodeAPI.accessLocalVariable(it.type.codeType, name)
                    }

                    methodBody.add(CodeAPI.returnValue(eventType, CodeAPI.invokeConstructor(jClass.codeType, CodeAPI.constructorTypeSpec(*ctr.parameterTypes), arguments)))

                    body.add(methodDeclaration)
                }
            }

        }

        val generator = BytecodeGenerator()

        generator.options.set(VISIT_LINES, VisitLineType.FOLLOW_CODE_SOURCE)

        val bytecodeClass = generator.gen(declaration)[0]

        val bytes = bytecodeClass.bytecode
        val disassembled = lazy { bytecodeClass.disassembledCode }

        @Suppress("UNCHECKED_CAST")
        val sandstoneClass = EventGenClassLoader.defineClass(declaration, bytes, disassembled) as SandstoneClass<T>

        if (Debug.FACTORY_GEN_DEBUG) {
            ClassSaver.save(Sandstone.sandstonePath, "factorygen", sandstoneClass)
        }

        return sandstoneClass.javaClass.newInstance()
    }

}