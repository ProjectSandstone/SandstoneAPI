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

import com.github.jonathanxd.iutils.`object`.Primitive
import com.github.jonathanxd.iutils.map.WeakValueHashMap
import com.github.projectsandstone.api.event.annotation.Named
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

/**
 * Event class factory.
 */
object SandstoneEventGen {
    private val lookup = MethodHandles.publicLookup()
    private val cached: MutableMap<Class<*>, Class<*>> = WeakValueHashMap()

    @Suppress("UNCHECKED_CAST")
    fun <T> gen(type: Class<T>, properties: Map<String, Any?>): T {
        val class_: Class<T>

        if (this.cached.containsKey(type)) {
            class_ = cached[type]!! as Class<T>
        } else {
            class_ = SandstoneEventGenUtil.genImplementation(type).javaClass

            this.cached += type to class_
        }

        return this.factory(class_, properties)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> factory(generated: Class<T>, properties: Map<String, Any?>): T {
        val parameters = mutableListOf<Any?>()

        val constructor = generated.constructors[0]

        constructor.parameters.forEach { parameter ->
            val named = parameter.getDeclaredAnnotation(Named::class.java)
                    ?: throw IllegalStateException("Missing parameter name in constructor of '$generated'.")

            val propertyValue = if (properties.containsKey(named.value)) properties[named.value] else
                throw IllegalArgumentException("Missing property '$named' in provided properties map ($properties)")

            val propertyType = propertyValue?.javaClass

            if (propertyType != null && !parameter.type.isAssignableFrom(propertyType)) {
                throw ClassCastException("Cannot cast property: '${named.value}' of type '$propertyType' to expected type '${parameter.type}'.")
            }

            parameters += propertyValue
        }

        return constructor.newInstance(*parameters.toTypedArray()) as T
    }

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