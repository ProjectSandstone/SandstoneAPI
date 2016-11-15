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
package com.github.projectsandstone.api.util.internal.gen.event

import com.github.jonathanxd.codeapi.CodeAPI
import com.github.jonathanxd.codeapi.CodePart
import com.github.jonathanxd.codeapi.CodeSource
import com.github.jonathanxd.codeapi.MutableCodeSource
import com.github.jonathanxd.codeapi.builder.IfExpressionHelper
import com.github.jonathanxd.codeapi.common.*
import com.github.jonathanxd.codeapi.gen.value.source.PlainSourceGenerator
import com.github.jonathanxd.codeapi.gen.visit.bytecode.BytecodeGenerator
import com.github.jonathanxd.codeapi.helper.Helper
import com.github.jonathanxd.codeapi.helper.PredefinedTypes
import com.github.jonathanxd.codeapi.impl.CodeConstructor
import com.github.jonathanxd.codeapi.impl.CodeMethod
import com.github.jonathanxd.codeapi.impl.MethodSpecImpl
import com.github.jonathanxd.codeapi.interfaces.MethodInvocation
import com.github.jonathanxd.codeapi.literals.Literals
import com.github.jonathanxd.codeapi.types.CodeType
import com.github.jonathanxd.codeapi.types.Generic
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.event.annotation.Named
import com.github.projectsandstone.api.event.property.*
import com.github.projectsandstone.api.util.extension.codeapi.plusAssign
import com.github.projectsandstone.api.util.internal.Debug
import com.github.projectsandstone.api.util.internal.gen.SandstoneClass
import com.github.projectsandstone.api.util.internal.gen.genericFromTypeInfo
import com.github.projectsandstone.api.util.internal.gen.save.ClassSaver
import com.github.projectsandstone.api.util.succeed
import com.github.projectsandstone.api.util.succeedReturn
import java.lang.reflect.Modifier
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

object SandstoneEventGenUtil {

    val propertiesFieldName = "#properties"
    val propertiesUnmodName = "immutable#properties"
    val propertiesFieldType = Generic.type(Helper.getJavaType(Map::class.java))
            .of(PredefinedTypes.STRING)
            .of(Helper.getJavaType(com.github.projectsandstone.api.event.property.Property::class.java))

    private var count: Int = 0

    @Suppress("UNCHECKED_CAST")
    fun <T> genImplementation(typeInfo: TypeInfo<T>, additionalProperties: List<PropertyInfo>): SandstoneClass<T> {

        val type: CodeType = genericFromTypeInfo(typeInfo)
        val classType = typeInfo.aClass
        val isItf = classType.isInterface

        val typeInfoLiter = typeInfo.toString()
                .replace(".", "_")
                .replace("<", "_of_").replace(">", "__")
                .replace(", ", "_and_")

        val name = "${typeInfoLiter}Impl$$count"
        ++count

        var codeClassBuilder = CodeAPI
                .aClassBuilder()
                .withBody(MutableCodeSource())
                .withModifiers(Modifier.PUBLIC)
                .withQualifiedName(name)

        if (isItf)
            codeClassBuilder = codeClassBuilder.withImplementations(type)
        else
            codeClassBuilder = codeClassBuilder.withSuperClass(type)

        val codeClass = codeClassBuilder.build()

        val body = codeClass.body.get() as MutableCodeSource


        val properties = this.getProperties(classType) + additionalProperties

        this.genFields(classType, body, properties)
        this.genConstructor(classType, body, properties)
        this.genMethods(classType, body, properties)
        // Gen getProperties & getProperty & hasProperty
        this.genPropertyHolderMethods(classType, body, properties)
        this.check(body)


        val codeSource = CodeAPI.sourceOfParts(codeClass)

        val bytes = BytecodeGenerator().gen(codeSource)[0].bytecode
        val source = lazy {
            PlainSourceGenerator().gen(codeSource)
        }

        val sandstoneClass = EventGenClassLoader.defineClass(codeClass, bytes, source) as SandstoneClass<T>

        if (Debug.EVENT_GEN_DEBUG) {
            ClassSaver.save(Sandstone.sandstonePath, "eventgen", sandstoneClass)
        }

        return sandstoneClass
    }

    private fun check(codeSource: CodeSource) {

        val methodList = mutableListOf<CodeMethod>()

        codeSource.forEach { outer ->
            if (outer is CodeMethod) {
                if (methodList.any {
                    it.name == outer.name
                            && it.returnType.orElse(PredefinedTypes.VOID) == outer.returnType.orElse(PredefinedTypes.VOID)
                            && it.parameters.map { it.requiredType } == outer.parameters.map { it.requiredType }
                })
                    throw IllegalStateException("Duplicated method: ${outer.name}")
                else
                    methodList += outer
            }
        }
    }

    private fun genFields(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {
        properties.forEach {
            val name = it.propertyName

            var modifiers = Modifier.PRIVATE

            if (!it.isMutable()) {
                modifiers = modifiers or Modifier.FINAL
            }

            val codeType: CodeType = Helper.getJavaType(it.type)

            val field = CodeAPI.field(modifiers, codeType, name)

            body += field
        }

        genPropertyField(type, body, properties)
    }

    private fun genPropertyField(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {
        body += CodeAPI.field(Modifier.PRIVATE or Modifier.FINAL,
                propertiesFieldType,
                propertiesFieldName,
                CodeAPI.invokeConstructor(HashMap::class.java))

        body += CodeAPI.field(Modifier.PRIVATE or Modifier.FINAL,
                propertiesFieldType,
                propertiesUnmodName,
                CodeAPI.invokeStatic(Collections::class.java,
                        "unmodifiableMap",
                        CodeAPI.typeSpec(Map::class.java, Map::class.java),
                        CodeAPI.argument(CodeAPI.accessThisField(propertiesFieldType, propertiesFieldName))))
    }

    private fun genConstructor(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {
        val parameters = mutableListOf<CodeParameter>()

        properties.forEach {
            val name = it.propertyName

            val valueType: CodeType = Helper.getJavaType(it.type)

            parameters += CodeParameter(name, valueType, listOf(
                    CodeAPI.visibleAnnotation(Named::class.java, mapOf<String, Any>("value" to name))
            ))
        }

        val constructor = CodeConstructor(listOf(CodeModifier.PUBLIC), parameters, emptyList(), MutableCodeSource())

        body += constructor

        val constructorBody = constructor.body.get() as MutableCodeSource

        properties.forEach {
            val valueType: CodeType = Helper.getJavaType(it.type)

            constructorBody += CodeAPI.setThisField(valueType, it.propertyName, CodeAPI.accessLocalVariable(valueType, it.propertyName))
        }

        genConstructorPropertiesMap(type, constructorBody, properties)

        /*constructorBody += CodeAPI.setThisField(Info::class.java, "info",
                CodeAPI.invokeConstructor(Info::class.java, CodeAPI.argument(Helper.accessThis(), PropertyHolder::class.java)))*/
    }

    private fun genConstructorPropertiesMap(type: Class<*>, constructorBody: MutableCodeSource, properties: List<PropertyInfo>) {
        val accessMap = CodeAPI.accessThisField(propertiesFieldType, propertiesFieldName)

        properties.forEach {
            constructorBody += this.invokePut(accessMap,
                    CodeAPI.argument(Literals.STRING(it.propertyName)),
                    CodeAPI.argument(this.propertyToSProperty(type, it)))
        }

    }

    private fun invokePut(accessMap: CodePart, vararg codeArguments: CodeArgument): CodePart {
        return CodeAPI.invokeInterface(Map::class.java, accessMap, "put", CodeAPI.typeSpec(Any::class.java, Any::class.java, Any::class.java), *codeArguments)
    }

    private fun propertyToSProperty(type: Class<*>, property: PropertyInfo): CodePart {

        val hasGetter = property.hasGetter()
        val hasSetter = property.hasSetter()

        val typeToInvoke =
                if (hasGetter && hasSetter) Helper.getJavaType(GSPropertyImpl::class.java)
                else if (hasGetter) Helper.getJavaType(GetterPropertyImpl::class.java)
                else if (hasSetter) Helper.getJavaType(SetterPropertyImpl::class.java)
                else Helper.getJavaType(PropertyImpl::class.java)

        val arguments = mutableListOf<CodeArgument>(
                CodeAPI.argument(Literals.CLASS(property.type), Class::class.java)
        )

        if (hasGetter) {
            arguments += CodeAPI.argument(this.invokeGetter(type, property), java.util.function.Supplier::class.java)
        }

        if (hasSetter) {
            arguments += CodeAPI.argument(this.invokeSetter(type, property), java.util.function.Consumer::class.java)
        }

        return CodeAPI.invokeConstructor(typeToInvoke, *arguments.toTypedArray())
    }

    private fun invokeGetter(type: Class<*>, property: PropertyInfo): CodePart {
        val propertyType = property.type
        val getterName = property.getterName!!

        val supplierType = Helper.getJavaType(Supplier::class.java)

        val invocation = Helper.invoke(InvokeType.INVOKE_VIRTUAL,
                null as CodeType?,
                Helper.accessThis(),
                MethodSpecImpl(getterName, propertyType, mutableListOf()))

        return CodeAPI.invokeDynamic(InvokeDynamic.invokeDynamicLambda(
                CodeAPI.fullMethodSpec(supplierType, PredefinedTypes.OBJECT, "get"),
                CodeAPI.typeSpec(propertyType)),
                invocation)
    }

    private fun invokeSetter(type: Class<*>, property: PropertyInfo): CodePart {
        val propertyType = Helper.getJavaType(property.type)
        val setterName = property.setterName!!

        val consumerType = Helper.getJavaType(Consumer::class.java)


        //val invokeType = InvokeType.get(Helper.getJavaType(type))

        val invocation: MethodInvocation

        //if (invokeType == InvokeType.INVOKE_VIRTUAL) {
            invocation = CodeAPI.invokeVirtual(setterName, TypeSpec(PredefinedTypes.VOID, propertyType))
        /*} else {
            invocation = CodeAPI.invokeInterface(setterName, TypeSpec(PredefinedTypes.VOID, propertyType))
        }*/

        return CodeAPI.invokeDynamic(InvokeDynamic.invokeDynamicLambda(
                CodeAPI.fullMethodSpec(consumerType, PredefinedTypes.VOID, "accept", PredefinedTypes.OBJECT),
                CodeAPI.typeSpec(PredefinedTypes.VOID, propertyType)),
                invocation)
    }

    private fun genMethods(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {
        properties.forEach {
            val name = it.propertyName

            if (it.hasGetter()) {
                genGetter(type, body, it.getterName!!, name, it.type)
            }

            if (it.isMutable()) {
                genSetter(type, body, it.setterName!!, name, it.type)
            }

        }
    }

    private fun genGetter(type: Class<*>, body: MutableCodeSource, getterName: String, name: String, propertyType: Class<*>?) {
        val fieldType = propertyType?.let { Helper.getJavaType(it) } ?: getPropertyType(type, name)

        val method = CodeMethod(getterName, listOf(CodeModifier.PUBLIC), emptyList(), fieldType, CodeAPI.sourceOfParts(
                CodeAPI.returnThisField(fieldType, name)
        ))

        body += method
    }

    private fun genSetter(type: Class<*>, body: MutableCodeSource, getterName: String, name: String, propertyType: Class<*>?) {
        val fieldType = propertyType?.let { Helper.getJavaType(it) } ?: getPropertyType(type, name)

        val method = CodeMethod(getterName, listOf(CodeModifier.PUBLIC), listOf(CodeAPI.parameter(fieldType, name)), PredefinedTypes.VOID, CodeAPI.sourceOfParts(
                CodeAPI.setThisField(fieldType, name, CodeAPI.accessLocalVariable(fieldType, name))
        ))

        body += method
    }

    private fun genPropertyHolderMethods(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {

        val methodBody = MutableCodeSource()

        val method = CodeAPI.methodBuilder()
                .withModifiers(Modifier.PUBLIC)
                .withName("getProperty")
                .withReturnType(Property::class.java)
                .withParameters(CodeAPI.parameter(Class::class.java, "type"), CodeAPI.parameter(String::class.java, "name"))
                .withAnnotations(CodeAPI.annotation(Override::class.java))
                .withBody(methodBody)
                .build()

        val accessMap = CodeAPI.accessThisField(propertiesFieldType, propertiesFieldName)

        val propertyAccess = CodeAPI.cast(Any::class.java, Property::class.java,
                CodeAPI.invokeInterface(Map::class.java,
                        accessMap,
                        "get",
                        CodeAPI.typeSpec(Any::class.java, Any::class.java),
                        CodeAPI.argument(CodeAPI.accessLocalVariable(String::class.java, "name"))))

        methodBody += CodeAPI.field(Property::class.java, "property", propertyAccess)

        val accessPropertyVar = CodeAPI.accessLocalVariable(Property::class.java, "property")

        val propertyTypeAccess = CodeAPI.invokeInterface(Property::class.java, accessPropertyVar, "getType", CodeAPI.typeSpec(Class::class.java))

        methodBody += CodeAPI.ifBlock(IfExpressionHelper.builder()
                // if propertyType.isAssignableFrom(type) return propertyVar else return null
                .checkTrue(CodeAPI.invokeVirtual(
                        Class::class.java, propertyTypeAccess, "isAssignableFrom", CodeAPI.typeSpec(Boolean::class.javaPrimitiveType, Class::class.java),
                        CodeAPI.argument(CodeAPI.accessLocalVariable(Class::class.java, "type"))
                ))
                .build(),
                CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(
                                Property::class.java,
                                accessPropertyVar
                        )
                ),
                CodeAPI.elseBlock(
                        CodeAPI.returnValue(
                                Property::class.java,
                                Literals.NULL)
                ))

        body += method

        // hasProperty(Class, String):Boolean
        val hasPropertyMethod = CodeAPI.methodBuilder()
                .withModifiers(Modifier.PUBLIC)
                .withName("hasProperty")
                .withReturnType(Boolean::class.javaPrimitiveType)
                .withParameters(CodeAPI.parameter(Class::class.java, "type"), CodeAPI.parameter(String::class.java, "name"))
                .withAnnotations(CodeAPI.annotation(Override::class.java))
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(Boolean::class.javaPrimitiveType,
                                CodeAPI.checkNotNull(CodeAPI.invokeVirtual(
                                        "getProperty",
                                        CodeAPI.typeSpec(Property::class.java, Class::class.java, String::class.java),
                                        CodeAPI.argument(CodeAPI.accessLocalVariable(Class::class.java, "type")),
                                        CodeAPI.argument(CodeAPI.accessLocalVariable(String::class.java, "name"))
                                ))
                        )
                ))
                .build()

        body += hasPropertyMethod

        val getPropertiesMethod = CodeAPI.methodBuilder()
                .withModifiers(Modifier.PUBLIC)
                .withName("getProperties")
                .withReturnType(propertiesFieldType)
                .withAnnotations(CodeAPI.annotation(Override::class.java))
                .withBody(CodeAPI.sourceOfParts(
                        Helper.returnValue(propertiesFieldType, CodeAPI.accessThisField(propertiesFieldType, propertiesUnmodName))
                ))
                .build()

        body += getPropertiesMethod
    }

    private fun hasSetter(type: Class<*>, name: String, propertyType: Class<*>): Boolean {

        val capitalized = name.capitalize()

        return succeed { type.getMethod("set$capitalized", propertyType) }
    }

    private fun hasGetter(type: Class<*>, name: String): Boolean {

        val capitalized = name.capitalize()

        return succeed { type.getMethod("is$capitalized") } || succeed { type.getMethod("get$capitalized") }
    }

    internal fun getProperties(type: Class<*>): List<PropertyInfo> {
        val list = mutableListOf<PropertyInfo>()

        type.methods.forEach { method ->

            val name = method.name

            val isGet = name.startsWith("get")
            val isIs = name.startsWith("is")
            val isSet = name.startsWith("set")

            // Skip PropertyHolder methods
            // We could use method.declaringClass == PropertyHolder::class.java
            // but override methods will return false.
            if(this.hasMethod(PropertyHolder::class.java, name))
                return@forEach

            if (isGet || isIs || isSet) { // hasProperty of PropertyHolder
                // 3 = "get".length & "set".length
                // 2 = "is".length
                val propertyName = (if (isGet || isSet) name.substring(3..name.length - 1) else name.substring(2..name.length - 1))
                        .decapitalize()

                val propertyType = if (isGet || isIs) method.returnType else method.parameterTypes[0]

                if (!list.any { it.propertyName == propertyName }) {
                    val getterName = if (hasGetter(type, propertyName)) "get${propertyName.capitalize()}" else null
                    val setterName = if (hasSetter(type, propertyName, propertyType)) "set${propertyName.capitalize()}" else null

                    list += PropertyInfo(propertyName, getterName, setterName, propertyType)
                }

            } else if (!method.isDefault) {
                throw IllegalArgumentException("The method '$name' is not a property and has no default implementation!")
            }

        }

        return list
    }

    private fun getPropertyType(type: Class<*>, name: String): CodeType {
        val capitalized = name.capitalize()

        val method = succeedReturn { type.getMethod("get$capitalized") }

        if (method != null) {
            return Helper.getJavaType(method.returnType)
        } else {
            throw IllegalArgumentException("Cannot find property with name: $name!")
        }
    }

    private fun hasMethod(klass: Class<*>, name: String): Boolean {
        return klass.declaredMethods.any { it.name == name } || klass.methods.any { it.name == name }
    }
}