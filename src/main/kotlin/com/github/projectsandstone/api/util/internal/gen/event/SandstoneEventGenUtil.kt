/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2017 Sandstone <https://github.com/ProjectSandstone/>
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

import com.github.jonathanxd.codeapi.*
import com.github.jonathanxd.codeapi.base.MethodDeclaration
import com.github.jonathanxd.codeapi.base.MethodInvocation
import com.github.jonathanxd.codeapi.base.impl.ConstructorDeclarationImpl
import com.github.jonathanxd.codeapi.builder.MethodDeclarationBuilder
import com.github.jonathanxd.codeapi.bytecode.VISIT_LINES
import com.github.jonathanxd.codeapi.bytecode.VisitLineType
import com.github.jonathanxd.codeapi.bytecode.gen.BytecodeGenerator
import com.github.jonathanxd.codeapi.common.*
import com.github.jonathanxd.codeapi.conversions.toCodeArgument
import com.github.jonathanxd.codeapi.factory.field
import com.github.jonathanxd.codeapi.factory.variable
import com.github.jonathanxd.codeapi.generic.GenericSignature
import com.github.jonathanxd.codeapi.helper.IfExpressionHelper
import com.github.jonathanxd.codeapi.literal.Literals
import com.github.jonathanxd.codeapi.type.CodeType
import com.github.jonathanxd.codeapi.type.Generic
import com.github.jonathanxd.codeapi.util.Alias
import com.github.jonathanxd.codeapi.util.codeType
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.event.annotation.Name
import com.github.projectsandstone.api.event.property.*
import com.github.projectsandstone.api.util.extension.codeapi.plusAssign
import com.github.projectsandstone.api.util.internal.Debug
import com.github.projectsandstone.api.util.internal.gen.SandstoneClass
import com.github.projectsandstone.api.util.internal.gen.genericFromTypeInfo
import com.github.projectsandstone.api.util.internal.gen.save.ClassSaver
import com.github.projectsandstone.api.util.internal.getImplementation
import com.github.projectsandstone.api.util.internal.parameterNames
import com.github.projectsandstone.api.util.succeed
import com.github.projectsandstone.api.util.succeedReturn
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.jvmErasure

object SandstoneEventGenUtil {

    val propertiesFieldName = "#properties"
    val propertiesUnmodName = "immutable#properties"
    val propertiesFieldType = Generic.type(CodeAPI.getJavaType(Map::class.java))
            .of(Types.STRING)
            .of(CodeAPI.getJavaType(com.github.projectsandstone.api.event.property.Property::class.java))

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
                .withModifiers(CodeModifier.PUBLIC)
                .withQualifiedName(name)

        if (isItf)
            codeClassBuilder = codeClassBuilder.withImplementations(type)
        else
            codeClassBuilder = codeClassBuilder.withSuperClass(type)

        val codeClass = codeClassBuilder.build()

        val body = codeClass.body as MutableCodeSource


        val properties = this.getProperties(classType) + additionalProperties

        this.genFields(classType, body, properties)
        this.genConstructor(classType, body, properties)
        this.genMethods(classType, body, properties)
        // Gen getProperties & getProperty & hasProperty
        this.genPropertyHolderMethods(classType, body, properties)
        this.check(body)


        val codeSource = CodeAPI.sourceOfParts(codeClass)

        val generator = BytecodeGenerator()

        generator.options.set(VISIT_LINES, VisitLineType.FOLLOW_CODE_SOURCE)

        val bytecodeClass = generator.gen(codeSource)[0]

        val bytes = bytecodeClass.bytecode
        val disassembled = lazy { bytecodeClass.disassembledCode }

        val sandstoneClass = EventGenClassLoader.defineClass(codeClass, bytes, disassembled) as SandstoneClass<T>

        if (Debug.EVENT_GEN_DEBUG) {
            ClassSaver.save(Sandstone.sandstonePath, "eventgen", sandstoneClass)
        }

        return sandstoneClass
    }

    private fun check(codeSource: CodeSource) {

        val methodList = mutableListOf<MethodDeclaration>()

        codeSource.forEach { outer ->
            if (outer is MethodDeclaration) {
                if (methodList.any {
                    it.name == outer.name
                            && it.returnType == outer.returnType
                            && it.parameters.map { it.type } == outer.parameters.map { it.type }
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

            val modifiers = EnumSet.of(CodeModifier.PRIVATE)

            if (!it.isMutable()) {
                modifiers.add(CodeModifier.FINAL)
            }

            val codeType: CodeType = CodeAPI.getJavaType(it.type)

            val field = field(modifiers, codeType, name)

            body += field
        }

        genPropertyField(type, body, properties)
    }

    private fun genPropertyField(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {
        body += field(EnumSet.of(CodeModifier.PRIVATE, CodeModifier.FINAL),
                propertiesFieldType,
                propertiesFieldName,
                CodeAPI.invokeConstructor(HashMap::class.java))

        body += field(EnumSet.of(CodeModifier.PRIVATE, CodeModifier.FINAL),
                propertiesFieldType,
                propertiesUnmodName,
                CodeAPI.invokeStatic(Collections::class.java,
                        "unmodifiableMap",
                        CodeAPI.typeSpec(Map::class.java, Map::class.java),
                        listOf(CodeAPI.accessThisField(propertiesFieldType, propertiesFieldName))))
    }

    private fun genConstructor(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {
        val parameters = mutableListOf<CodeParameter>()

        properties.forEach {
            val name = it.propertyName

            val valueType: CodeType = CodeAPI.getJavaType(it.type)

            parameters += CodeParameter(
                    name = name,
                    type = valueType,
                    annotations = listOf(CodeAPI.visibleAnnotation(Name::class.java, mapOf<String, Any>("value" to name)))
            )
        }

        val constructor = ConstructorDeclarationImpl(
                modifiers = EnumSet.of(CodeModifier.PUBLIC),
                parameters = parameters,
                annotations = emptyList(),
                body = MutableCodeSource(),
                genericSignature = GenericSignature.empty())

        body += constructor

        val constructorBody = constructor.body as MutableCodeSource

        properties.forEach {
            val valueType: CodeType = CodeAPI.getJavaType(it.type)

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
                    Literals.STRING(it.propertyName),
                    this.propertyToSProperty(type, it))
        }

    }

    private fun invokePut(accessMap: CodePart, vararg arguments: CodePart): CodePart {
        return CodeAPI.invokeInterface(Map::class.java, accessMap, "put", CodeAPI.typeSpec(Any::class.java, Any::class.java, Any::class.java), listOf(*arguments))
    }

    private fun propertyToSProperty(type: Class<*>, property: PropertyInfo): CodePart {

        val hasGetter = property.hasGetter()
        val hasSetter = property.hasSetter()

        val typeToInvoke =
                if (hasGetter && hasSetter) CodeAPI.getJavaType(GSPropertyImpl::class.java)
                else if (hasGetter) CodeAPI.getJavaType(GetterPropertyImpl::class.java)
                else if (hasSetter) CodeAPI.getJavaType(SetterPropertyImpl::class.java)
                else CodeAPI.getJavaType(PropertyImpl::class.java)

        val arguments = mutableListOf<CodePart>(
                Literals.CLASS(property.type)
        )

        val argumentTypes = mutableListOf<CodeType>(
                Types.CLASS
        )

        if (hasGetter) {
            arguments += this.invokeGetter(type, property)
            argumentTypes += CodeAPI.getJavaType(Supplier::class.java)
        }

        if (hasSetter) {
            arguments += this.invokeSetter(type, property)
            argumentTypes += CodeAPI.getJavaType(Consumer::class.java)
        }

        val typeSpec = TypeSpec(Types.VOID, argumentTypes)

        return CodeAPI.invokeConstructor(typeToInvoke, typeSpec, arguments)
    }

    private fun invokeGetter(type: Class<*>, property: PropertyInfo): CodePart {
        val propertyType = property.type
        val getterName = property.getterName!!

        val supplierType = CodeAPI.getJavaType(Supplier::class.java)

        val invocation = CodeAPI.invoke(InvokeType.INVOKE_VIRTUAL,
                Alias.THIS,
                CodeAPI.accessThis(),
                getterName,
                CodeAPI.typeSpec(propertyType),
                mutableListOf()
        )

        return CodeAPI.invokeDynamic(
                InvokeDynamic.LambdaMethodReference(
                        methodTypeSpec = MethodTypeSpec(supplierType, "get", CodeAPI.typeSpec(Types.OBJECT)),
                        expectedTypes = CodeAPI.typeSpec(propertyType)
                ),
                invocation
        )
    }

    private fun invokeSetter(type: Class<*>, property: PropertyInfo): CodePart {
        val propertyType = CodeAPI.getJavaType(property.type)
        val setterName = property.setterName!!

        val consumerType = CodeAPI.getJavaType(Consumer::class.java)


        //val invokeType = InvokeType.get(Helper.getJavaType(type))

        val invocation: MethodInvocation

        //if (invokeType == InvokeType.INVOKE_VIRTUAL) {
        invocation = CodeAPI.invokeVirtual(Alias.THIS, CodeAPI.accessThis(), setterName,
                TypeSpec(Types.VOID, listOf(propertyType)),
                emptyList()
        )
        /*} else {
            invocation = CodeAPI.invokeInterface(Alias.THIS, CodeAPI.accessThis(), setterName, TypeSpec(PredefinedTypes.VOID, listOf(propertyType)), emptyList())
        }*/

        return CodeAPI.invokeDynamic(
                InvokeDynamic.LambdaMethodReference(
                        methodTypeSpec = MethodTypeSpec(consumerType, "accept", CodeAPI.constructorTypeSpec(Types.OBJECT)),
                        expectedTypes = CodeAPI.constructorTypeSpec(propertyType)
                ),
                invocation
        )
    }

    private fun genMethods(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {

        genDefaultMethodsImpl(body)

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
        val fieldType = propertyType?.let { CodeAPI.getJavaType(it) } ?: getPropertyType(type, name)

        val method = MethodDeclarationBuilder.builder()
                .withModifiers(EnumSet.of(CodeModifier.PUBLIC))
                .withReturnType(fieldType)
                .withName(getterName)
                .withBody(CodeAPI.sourceOfParts(CodeAPI.returnThisField(fieldType, name)))
                .build()

        body += method
    }

    private fun genSetter(type: Class<*>, body: MutableCodeSource, setterName: String, name: String, propertyType: Class<*>?) {
        val fieldType = propertyType?.let { CodeAPI.getJavaType(it) } ?: getPropertyType(type, name)

        val method = MethodDeclarationBuilder.builder()
                .withModifiers(EnumSet.of(CodeModifier.PUBLIC))
                .withReturnType(Types.VOID)
                .withParameters(CodeAPI.parameter(fieldType, name))
                .withName(setterName)
                .withBody(CodeAPI.sourceOfParts(CodeAPI.setThisField(fieldType, name, CodeAPI.accessLocalVariable(fieldType, name))))
                .build()

        body += method
    }

    private fun genPropertyHolderMethods(type: Class<*>, body: MutableCodeSource, properties: List<PropertyInfo>) {

        val methodBody = MutableCodeSource()

        val method = CodeAPI.methodBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withName("getProperty")
                .withReturnType(Property::class.java.codeType)
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
                        listOf(CodeAPI.accessLocalVariable(String::class.java, "name"))))

        methodBody += variable(Property::class.java.codeType, "property", propertyAccess)

        val accessPropertyVar = CodeAPI.accessLocalVariable(Property::class.java, "property")

        val propertyTypeAccess = CodeAPI.invokeInterface(Property::class.java.codeType, accessPropertyVar, "getType", CodeAPI.typeSpec(Class::class.java), emptyList())

        methodBody += CodeAPI.ifStatement(IfExpressionHelper.builder()
                // Check if property is not null
                .checkNotNull(accessPropertyVar)
                // if propertyType.isAssignableFrom(type) return propertyVar else return null
                .andTrue(CodeAPI.invokeVirtual(
                        Class::class.java, propertyTypeAccess, "isAssignableFrom", CodeAPI.typeSpec(Boolean::class.javaPrimitiveType, Class::class.java),
                        listOf(CodeAPI.accessLocalVariable(Class::class.java, "type"))
                ))
                .build(),
                CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(
                                Property::class.java,
                                accessPropertyVar
                        )
                ),
                CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(
                                Property::class.java,
                                Literals.NULL)
                ))

        body += method

        // hasProperty(Class, String):Boolean
        val hasPropertyMethod = CodeAPI.methodBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withName("hasProperty")
                .withReturnType(Types.BOOLEAN)
                .withParameters(CodeAPI.parameter(Class::class.java, "type"), CodeAPI.parameter(String::class.java, "name"))
                .withAnnotations(CodeAPI.annotation(Override::class.java))
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(Types.BOOLEAN,
                                CodeAPI.checkNotNull(CodeAPI.invokeVirtual(
                                        "getProperty",
                                        CodeAPI.typeSpec(Property::class.java, Class::class.java, String::class.java),
                                        listOf(
                                                CodeAPI.accessLocalVariable(Class::class.java, "type"),
                                                CodeAPI.accessLocalVariable(String::class.java, "name")
                                        )
                                ))
                        )
                ))
                .build()

        body += hasPropertyMethod

        val getPropertiesMethod = CodeAPI.methodBuilder()
                .withModifiers(CodeModifier.PUBLIC)
                .withName("getProperties")
                .withReturnType(propertiesFieldType)
                .withAnnotations(CodeAPI.annotation(Override::class.java))
                .withBody(CodeAPI.sourceOfParts(
                        CodeAPI.returnValue(propertiesFieldType, CodeAPI.accessThisField(propertiesFieldType, propertiesUnmodName))
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
            if (this.hasMethod(PropertyHolder::class.java, name))
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
            return CodeAPI.getJavaType(method.returnType)
        } else {
            throw IllegalArgumentException("Cannot find property with name: $name!")
        }
    }

    internal fun genDefaultMethodsImpl(body: MutableCodeSource) {
        val funcs = PropertyHolder::class.memberFunctions.map { base ->
            getImplementation(PropertyHolder::class, base)?.let { Pair(base, it) }
        }.filterNotNull()

        funcs.forEach {
            val base = it.first
            val delegateClass = it.second.first
            val delegate = it.second.second

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
                if (base.returnType.jvmErasure.java == Void.TYPE)
                    it
                else
                    CodeAPI.returnValue(base.returnType.jvmErasure.codeType, it)
            }

            body.add(MethodDeclarationBuilder.builder()
                    .withAnnotations(CodeAPI.overrideAnnotation())
                    .withModifiers(CodeModifier.PUBLIC, CodeModifier.BRIDGE)
                    .withName(base.name)
                    .withReturnType(base.returnType.jvmErasure.codeType)
                    .withParameters(parameters)
                    .withBody(CodeAPI.sourceOfParts(invoke))
                    .build()
            )

        }
    }


    private fun hasMethod(klass: Class<*>, name: String): Boolean {
        return klass.declaredMethods.any { it.name == name } || klass.methods.any { it.name == name }
    }
}