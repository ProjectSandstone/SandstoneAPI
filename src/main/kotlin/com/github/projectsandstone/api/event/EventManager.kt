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

import com.github.jonathanxd.iutils.`object`.TypeInfo
import com.github.projectsandstone.api.event.annotation.TypeRef
import com.github.projectsandstone.api.plugin.PluginContainer
import com.github.projectsandstone.api.util.type.TypeInfer
import java.lang.reflect.Method

/**
 * Created by jonathan on 13/08/16.
 */
/**
 * Event manager.
 *
 * [EventManager] register
 */
interface EventManager {

    /**
     * Register a [EventListener] for a [Event].
     *
     * If you wan't to register an instance as [EventListener] use [registerListeners].
     *
     * @param plugin Plugin of the [eventListener]
     * @param eventType Type of event
     * @param eventListener Listener of the event.
     * @see [registerListeners]
     */
    fun <T : Event> registerListener(plugin: Any, eventType: Class<T>, eventListener: EventListener<T>) {
        this.registerListener(plugin, TypeInfer.inferTypeInfoAs<T>(eventType), eventListener)
    }

    /**
     * Register a [EventListener] for a [Event].
     *
     * If you wan't to register an instance as [EventListener] use [registerListeners].
     *
     * @param plugin Plugin of the [eventListener]
     * @param eventType Type of event
     * @param eventListener Listener of the event.
     * @see [registerListeners]
     */
    fun <T : Event> registerListener(plugin: Any, eventType: TypeInfo<T>, eventListener: EventListener<T>)

    /**
     * Register all event listeners inside [listener] instance.
     *
     * Listener methods must be annotated with [Listener]
     *
     * @param plugin Plugin of the [listener]
     * @param listener Listener instance to be used to create a [MethodEventListener].
     */
    fun registerListeners(plugin: Any, listener: Any)

    /**
     * Register [method] as [EventListener] with priority: [eventPriority]
     *
     * @param plugin Plugin of the [method]
     * @param instance Instance used to invoke method.
     * @param method Method to register
     * @param eventPriority Priority of [EventListener]
     * @param ignoreCancelled True if this listener will be ignored if event is cancelled.
     * @param isBeforeModifications True if this listener runs before game modifications.
     */
    fun registerMethodListener(plugin: Any,
                               instance: Any?,
                               method: Method,
                               eventPriority: EventPriority = EventPriority.NORMAL,
                               ignoreCancelled: Boolean = false,
                               isBeforeModifications: Boolean = false)

    /**
     * Dispatch an [Event] to all [EventListener]s that listen to the [event].
     *
     * @param event [Event] to dispatch do listeners.
     * @param pluginContainer Plugin that created this event.
     * @param isBeforeModifications True if this dispatch is occurring before modifications,
     * false otherwise (default: false).
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatch(event: T, pluginContainer: PluginContainer, isBeforeModifications: Boolean) {
        this.dispatch(event, TypeInfer.inferTypeInfoAs<T>(event.javaClass), pluginContainer, isBeforeModifications)
    }

    /**
     * Dispatch an [Event] of type [eventType] to all [EventListener]s that listen to the [event].
     *
     * This method is used when you need to dispatch a generic event that doesn't provide [TypeRef]
     *
     * @param event [Event] to dispatch do listeners.
     * @param eventType Type (generic) of [Event]
     * @param pluginContainer Plugin that created this event.
     * @param isBeforeModifications True if this dispatch is occurring before modifications,
     * false otherwise (default: false).
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatch(event: T, eventType: TypeInfo<T>, pluginContainer: PluginContainer, isBeforeModifications: Boolean)

    /**
     * Dispatch an [Event] to all [EventListener]s that listen to the [event].
     *
     * @param event [Event] to dispatch do listeners.
     * @param pluginContainer Plugin that created this event.
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatch(event: T, pluginContainer: PluginContainer) {
        this.dispatch(event, pluginContainer, true)
        this.dispatch(event, pluginContainer, false)
    }

    /**
     * Dispatch an [Event] of type [eventType] to all [EventListener]s that listen to the [event].
     *
     * [Event] will be dispatched as before and after modifications.
     *
     * @param event [Event] to dispatch do listeners.
     * @param eventType Type (generic) of [Event]
     * @param pluginContainer Plugin that created this event.
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatch(event: T, eventType: TypeInfo<T>, pluginContainer: PluginContainer) {
        this.dispatch(event, eventType, pluginContainer, true)
        this.dispatch(event, eventType, pluginContainer, false)
    }


    //////////// Async

    /**
     * Dispatch an [Event] to all [EventListener]s that listen to the [event].
     *
     * Asynchronous dispatch
     *
     * @param event [Event] to dispatch do listeners.
     * @param pluginContainer Plugin that created this event.
     * @param isBeforeModifications True if this dispatch is occurring before modifications,
     * false otherwise (default: false).
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatchAsync(event: T, pluginContainer: PluginContainer, isBeforeModifications: Boolean) {
        this.dispatchAsync(event, TypeInfer.inferTypeInfoAs<T>(event.javaClass), pluginContainer, isBeforeModifications)
    }

    /**
     * Dispatch an [Event] of type [eventType] to all [EventListener]s that listen to the [event].
     *
     * Asynchronous dispatch
     *
     * @param event [Event] to dispatch do listeners.
     * @param eventType Type (generic) of [Event]
     * @param pluginContainer Plugin that created this event.
     * @param isBeforeModifications True if this dispatch is occurring before modifications,
     * false otherwise (default: false).
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatchAsync(event: T, eventType: TypeInfo<T>, pluginContainer: PluginContainer, isBeforeModifications: Boolean)

    /**
     * Dispatch an [Event] to all [EventListener]s that listen to the [event].
     *
     * Asynchronous dispatch
     *
     * @param event [Event] to dispatch do listeners.
     * @param pluginContainer Plugin that created this event.
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatchAsync(event: T, pluginContainer: PluginContainer) {
        this.dispatchAsync(event, pluginContainer, false)
        this.dispatchAsync(event, pluginContainer, true)
    }

    /**
     * Dispatch an [Event] of type [eventType] to all [EventListener]s that listen to the [event].
     *
     * Asynchronous dispatch
     *
     * [Event] will be dispatched as before and after modifications.
     *
     * @param event [Event] to dispatch do listeners.
     * @param eventType Type (generic) of [Event]
     * @param pluginContainer Plugin that created this event.
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatchAsync(event: T, eventType: TypeInfo<T>, pluginContainer: PluginContainer) {
        this.dispatchAsync(event, eventType, pluginContainer, false)
        this.dispatchAsync(event, eventType, pluginContainer, true)
    }

    //////////// /Async

    /**
     * Gets listeners of a specific event.
     *
     * @param eventType Type of event.
     * @return Listeners of event ([eventType])
     */
    fun <T : Event> getListeners(eventType: TypeInfo<T>): Set<Pair<TypeInfo<T>, EventListener<T>>>

    /**
     * Gets all listeners of events
     */
    fun getListeners(): Set<Pair<TypeInfo<*>, EventListener<*>>>
}