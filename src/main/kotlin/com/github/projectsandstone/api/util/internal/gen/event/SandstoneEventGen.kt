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

import com.github.jonathanxd.iutils.map.WeakValueHashMap
import com.github.jonathanxd.iutils.type.Primitive
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.event.Cancellable
import com.github.projectsandstone.api.event.annotation.Name
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

/**
 * Event class factory.
 */
object SandstoneEventGen {
    private val lookup = MethodHandles.publicLookup()
    private val cached: MutableMap<GeneratedEventImpl, Class<*>> = WeakValueHashMap()

    @Suppress("UNCHECKED_CAST")
    @JvmOverloads
    fun <T> gen(type: TypeInfo<T>, properties_: Map<String, Any?>, additionalProperties: List<PropertyInfo> = emptyList()): T {
        this.checkProperties(type, properties_, additionalProperties)

        val class_: Class<T>

        val generated = GeneratedEventImpl(type, properties_.keys)

        val properties = properties_.toMutableMap()

        if (Cancellable::class.java.isAssignableFrom(type.aClass) && !properties.contains("isCancelled")) {
            properties += "isCancelled" to false
        }

        if (this.cached.containsKey(generated)) {
            class_ = cached[generated]!! as Class<T>
        } else {
            class_ = SandstoneEventGenUtil.genImplementation(type, additionalProperties).javaClass

            this.cached += generated to class_
        }

        return this.factory(class_, properties)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> factory(generated: Class<T>, properties: Map<String, Any?>): T {
        val parameters = mutableListOf<Any?>()

        val constructor = generated.constructors[0]

        constructor.parameters.forEach { parameter ->
            val named = parameter.getDeclaredAnnotation(Name::class.java)
                    ?: throw IllegalStateException("Missing parameter name in constructor of '$generated'.")

            val propertyValue = if (properties.containsKey(named.value)) properties[named.value] else
                throw IllegalArgumentException("Missing property '$named' in provided properties map ($properties)")

            val propertyType = propertyValue?.javaClass

            if (propertyType != null && !castCheck(propertyType, parameter.type)) { // parameter.type.isAssignableFrom(propertyType)
                throw ClassCastException("Cannot cast property: '${named.value}' of type '$propertyType' to expected type '${parameter.type}'.")
            }

            parameters += propertyValue
        }

        return constructor.newInstance(*parameters.toTypedArray()) as T
    }

    private fun checkProperties(type: TypeInfo<*>, properties: Map<String, Any?>, additionalProperties: List<PropertyInfo>) {
        val typeProperties = SandstoneEventGenUtil.getProperties(type.aClass) + additionalProperties

        val missing = typeProperties.filter {
            if (!properties.containsKey(it.propertyName))
                return@filter true
            else {
                val property = properties[it.propertyName]

                if (property != null && !castCheck(property.javaClass, it.type)) {
                    throw ClassCastException("Cannot cast provided property '$property' of type '${property.javaClass}' to expected type '${it.type}'")
                }
            }

            return@filter false
        }

        if (missing.isNotEmpty())
            throw IllegalArgumentException("Missing ${if (missing.size == 1) "property" else "properties"}: '$missing' in provided properties map ($properties)")
    }

    /**
     * Checks if class [from] can be casted to [to].
     */
    private fun castCheck(from: Class<*>, to: Class<*>): Boolean =
            to.isAssignableFrom(from)
                    || from.isPrimitive && !to.isPrimitive
                    || !from.isPrimitive && to.isPrimitive

    private fun propertiesToType(anyList: List<Any>): MethodType {
        val classes = mutableListOf<Class<*>>()

        anyList.forEach { value ->
            val type: Class<*>

            if (value.javaClass.isPrimitive) {
                type = Primitive.unbox(value.javaClass)
            } else {
                type = value.javaClass
            }

            classes += type
        }

        return MethodType.methodType(Void.TYPE, classes)
    }
}