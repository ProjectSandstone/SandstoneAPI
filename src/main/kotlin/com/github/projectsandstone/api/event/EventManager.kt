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
import com.github.projectsandstone.api.plugin.PluginContainer

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
        this.registerListener(plugin, TypeInfo.aEnd(eventType), eventListener)
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
     * @param listener Listener instance to be used to create a [ClassEventListener].
     */
    fun registerListeners(plugin: Any, listener: Any)

    /**
     * Dispatch an [Event] to all [EventListener]s that listen to the [event].
     *
     * @param event [Event] to dispatch do listeners.
     * @param pluginContainer Plugin that created this event.
     */
    @Throws(Throwable::class)
    fun <T : Event> dispatch(event: T, pluginContainer: PluginContainer)

    /**
     * Gets listeners of a specific event.
     *
     * @param eventType Type of event.
     * @return Listeners of event ([eventType])
     */
    fun <T : Event> getListeners(eventType: TypeInfo<T>): Set<EventListener<T>>

    /**
     * Gets all listeners of events
     */
    fun getListeners(): Set<EventListener<*>>
}