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
package com.github.projectsandstone.api.event.plugin

import com.github.projectsandstone.api.plugin.PluginContainer

/**
 * Fired when a plugin fails to be loaded.
 */
interface PluginLoadFailedEvent : PluginEvent {

    /**
     * Reason of failure.
     */
    val reason: Reason

    /**
     * Reason of failure.
     */
    sealed class Reason {

        /**
         * Failed because of dependency resolution failed, because of a missing dependency or
         * because an dependency plugin failed to load.
         *
         * Use [PluginContainer.dependenciesState] to get dependencies state.
         */
        object DependencyResolutionFailed : Reason()

        /**
         * Failed because an exception occurred during initialization.
         *
         * @param exception Exception occurred during initialization.
         */
        data class Exception(val exception: Throwable) : Reason()

    }

}