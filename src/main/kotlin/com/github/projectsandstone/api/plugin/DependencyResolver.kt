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

import com.github.projectsandstone.api.util.exception.DependencyException

/**
 * Dependency resolver, implementation MUST organize plugins based on dependency hierarchy.
 */
interface DependencyResolver {

    /**
     * Plugin Manager instance
     */
    val pluginManager: PluginManager

    /**
     * Gets a list of all dependency states of [pluginContainer].
     *
     * @return List of all dependency states of [pluginContainer].
     */
    fun getDependenciesState(pluginContainer: PluginContainer): List<DependencyState>

    /**
     * Resolve dependencies of [pluginContainer].
     */
    @Throws(DependencyException::class)
    fun checkDependencies(pluginContainer: PluginContainer)

}