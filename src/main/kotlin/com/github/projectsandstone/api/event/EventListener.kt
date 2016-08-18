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

import com.github.projectsandstone.api.plugin.PluginContainer


/**
 * Listen for an [Event].
 *
 * When [Event] is dispatched in [EventManager], [EventListener.onEvent] is called in its order.
 * (see [EventPriority]).
 */
interface EventListener<in T : Event> : Comparable<EventListener<*>> {

    /**
     * Called when the [event] is dispatched in [EventManager].
     *
     * @param event Event
     * @param pluginContainer Plugin that dispatched [Event] in [EventManager].
     */
    fun onEvent(event: T, pluginContainer: PluginContainer)

    /**
     * If true, this [EventListener] will be called after Modifications Event Listener (like Forge
     * modifications).
     */
    fun isBeforeModifications() = false

    /**
     * Priority of event, this priority will be used to sort [EventListener] in list.
     */
    fun getPriority() = EventPriority.NORMAL

    /**
     * Ignore if event is cancelled.
     */
    fun ignoreCancelled() = false

    override fun compareTo(other: EventListener<*>): Int {
        val compare = this.getPriority().compareTo(other.getPriority())

        return if(compare == 0) -1 else compare
    }
}