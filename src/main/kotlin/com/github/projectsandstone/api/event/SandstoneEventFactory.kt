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
package com.github.projectsandstone.api.event

import com.github.jonathanxd.iutils.type.AbstractTypeInfo
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.event.init.*
import com.github.projectsandstone.api.event.service.ChangeServiceProviderEvent
import com.github.projectsandstone.api.service.RegisteredProvider
import com.github.projectsandstone.api.util.extension.internal.gen.create
import com.github.projectsandstone.api.util.internal.gen.event.SandstoneEventGen

object SandstoneEventFactory {

    /**
     * Create implementation of a [Event] of type [eventType].
     *
     * @param eventType Type of event.
     * @param properties Properties (read the Sandstone wiki to learn about Property System).
     * @param T event type
     * @return event instance.
     */
    @JvmStatic
    fun <T : Event> createEvent(eventType: TypeInfo<T>, properties: Map<String, Any>): T {
        return SandstoneEventGen.gen(eventType, properties)
    }

    @JvmStatic
    fun createServerStartingEvent(): ServerStartingEvent {
        return SandstoneEventGen.create()
    }

    @JvmStatic
    fun createServerStartedEvent(): ServerStartedEvent {
        return SandstoneEventGen.create()
    }

    @JvmStatic
    fun createServerStoppedEvent(): ServerStoppedEvent {
        return SandstoneEventGen.create()
    }

    @JvmStatic
    fun createServerStoppingEvent(): ServerStoppingEvent {
        return SandstoneEventGen.create()
    }

    @JvmStatic
    fun createPreInitializationEvent(): PreInitializationEvent {
        return SandstoneEventGen.create()
    }

    @JvmStatic
    fun createInitializationEvent(): InitializationEvent {
        return SandstoneEventGen.create()
    }

    @JvmStatic
    fun createPostInitializationEvent(): PostInitializationEvent {
        return SandstoneEventGen.create()
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T : Any> createChangeServiceProviderEvent(service: TypeInfo<T>,
                                                   oldProvider: RegisteredProvider<T>?,
                                                   newProvider: RegisteredProvider<T>): ChangeServiceProviderEvent<T> {
        return SandstoneEventGen.create(
                TypeInfo.of(ChangeServiceProviderEvent::class.java)
                        .of<Any>(service)
                        .build() as TypeInfo<ChangeServiceProviderEvent<T>>,
                mutableMapOf("service" to service,
                        "oldProvider" to oldProvider,
                        "newProvider" to newProvider)
        )
    }

    @JvmStatic
    fun <T : Any> createChangeServiceProviderEvent(service: Class<T>,
                                                   oldProvider: RegisteredProvider<T>?,
                                                   newProvider: RegisteredProvider<T>)
            : ChangeServiceProviderEvent<T> = this.createChangeServiceProviderEvent(TypeInfo.aEnd(service), oldProvider, newProvider)

    @JvmStatic
    inline fun <reified T : Any> createChangeServiceProviderEvent(oldProvider: RegisteredProvider<T>?,
                                                                  newProvider: RegisteredProvider<T>): ChangeServiceProviderEvent<T> {
        return this.createChangeServiceProviderEvent(object : AbstractTypeInfo<T>() {}, oldProvider, newProvider)
    }
}