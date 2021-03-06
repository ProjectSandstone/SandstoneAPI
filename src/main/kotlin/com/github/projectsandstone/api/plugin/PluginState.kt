/*
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
package com.github.projectsandstone.api.plugin

/**
 * State of [PluginContainer].
 */
enum class PluginState {

    /**
     * Plugin is waiting to be loaded.
     *
     * This is the first state of plugin, defined when [PluginLoader] loads the plugin file.
     */
    ABOUT_TO_LOAD,

    /**
     * Plugin is loading.
     *
     * Defined when [PluginLoader] start loading classes in [PluginContainer.file], before dependency resolution.
     */
    LOADING,

    /**
     * Plugin is loaded.
     *
     * Defined when [PluginLoader] finish the loading process, after dependency resolution.
     */
    LOADED,

    /**
     * Plugin loading failed.
     *
     * Defined when [PluginLoader] throws any exception during the [LOADING] phase.
     *
     * All other plugins that depends on this plugin (as required dependency) will fail to load.
     */
    FAILED

}