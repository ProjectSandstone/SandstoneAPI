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
package com.github.projectsandstone.api.event

import com.github.jonathanxd.iutils.`object`.Named
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.jonathanxd.iutils.type.TypeUtil
import com.github.projectsandstone.api.Platform
import java.lang.reflect.Method

/**
 * Data Class version of [Listener]
 */
data class ListenerData(
        /**
         * Event type.
         */
        val eventType: TypeInfo<*>,

        /**
         * Ignore this listener if event is cancelled
         */
        val ignoreCancelled: Boolean = false,

        /**
         * Priority of this listener
         */
        val priority: EventPriority = EventPriority.NORMAL,

        /**
         * Run before modifications (this property has no effect in [Platform]s that doesn't support
         * modifications).
         */
        val beforeModifications: Boolean = false,

        /**
         * Named parameters types.
         */
        val namedParameters: List<Named<TypeInfo<*>>>) {

    companion object {
        fun fromMethod(method: Method): ListenerData {

            val listenerAnnotation = method.getDeclaredAnnotation(Listener::class.java)

            val namedParameters = method.parameters.map {
                val typeInfo = TypeUtil.toReference(it.parameterizedType)

                val name: String? = it.getDeclaredAnnotation(com.github.jonathanxd.iutils.annotation.Named::class.java)?.value
                        ?: it.getDeclaredAnnotation(javax.inject.Named::class.java)?.value
                        ?: it.getDeclaredAnnotation(com.google.inject.name.Named::class.java)?.value
                        ?: it.getDeclaredAnnotation(com.github.projectsandstone.api.event.annotation.Name::class.java)?.value

                return@map com.github.jonathanxd.iutils.`object`.Named(name, typeInfo)

            }.toList()



            return ListenerData(eventType = TypeUtil.toReference(method.genericParameterTypes[0]),
                    ignoreCancelled = listenerAnnotation.ignoreCancelled,
                    priority = listenerAnnotation.priority,
                    beforeModifications = listenerAnnotation.beforeModifications,
                    namedParameters = namedParameters)
        }
    }
}

