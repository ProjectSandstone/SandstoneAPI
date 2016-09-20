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
package com.github.projectsandstone.api.util.type

import com.github.jonathanxd.iutils.`object`.TypeInfo
import com.github.jonathanxd.iutils.reflection.Invokable
import com.github.jonathanxd.iutils.reflection.Invokables
import com.github.projectsandstone.api.event.annotation.TypeRef
import java.lang.reflect.AnnotatedElement
import java.lang.reflect.Modifier
import java.util.*

object TypeInfer {
    fun inferTypes(o: Any): Map<String, TypeInfo<*>> {

        val map = HashMap<String, TypeInfo<*>>()

        for (field in o.javaClass.fields) {
            if (Modifier.isStatic(field.modifiers))
                continue

            analyzeAnnotations(map, o, field, field.type, Invokables.fromField<Any>(field))
        }

        for (method in o.javaClass.methods) {
            if (Modifier.isStatic(method.modifiers))
                continue

            analyzeAnnotations(map, o, method, method.returnType, Invokables.fromMethod<Any>(method))
        }

        return map
    }

    private fun analyzeAnnotations(map: MutableMap<String, TypeInfo<*>>, instance: Any, annotatedElement: AnnotatedElement, type: Class<*>, invokable: Invokable<Any>) {

        val annotation = annotatedElement.getAnnotation(TypeRef::class.java)

        if (annotation != null) {
            val typeInfo: TypeInfo<*>

            if (TypeInfo::class.java.isAssignableFrom(type)) {
                typeInfo = invokable.invoke(instance) as TypeInfo<*>
            } else if (Class::class.java.isAssignableFrom(type)) {
                typeInfo = TypeInfo.aEnd(invokable.invoke(instance) as Class<*>)
            } else {
                throw IllegalArgumentException("Type of element: '$annotatedElement' must be Class<?> of iutils.TypeInfo")
            }

            val typeName = annotation.value

            if (map.containsKey(typeName))
                throw IllegalArgumentException("Duplicated definition of type: '$typeName'. Element: '$annotatedElement'!")

            map.put(typeName, typeInfo)
        }
    }
}