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
package com.github.projectsandstone.api.plugin

import com.github.projectsandstone.api.util.exception.PluginLoadException
import java.nio.file.Path

/**
 * Created by jonathan on 13/08/16.
 */
/**
 * These interfaces methods is called by [PluginManager],
 * all plugins MUST only be loaded AFTER dependency resolution.
 *
 * The implementation of PluginLoader must only parse plugin annotation in files ([loadFile])
 * and do plugin load tasks ([load]), like: Class Loading, Instantiation, Plugin Registry,
 * Logger Registry, etc.
 */
interface PluginLoader {

    /**
     * Plugin Manager instance
     */
    val pluginManager: PluginManager

    /**
     * Load plugin [file].
     *
     * @param file Plugin file
     * @return [PluginContainer] created from [Plugin] annotations found in [file].
     */
    @Throws(PluginLoadException::class)
    fun loadFile(file: Path): List<PluginContainer>

    /**
     * Load plugin classes
     *
     * @param classes Plugin classes
     * @return [PluginContainer] create from [Plugin] annotations found in [classes]
     */
    @Throws(PluginLoadException::class)
    fun loadClasses(classes: Array<String>): List<PluginContainer>

    /**
     * Load [plugin] classes. Must be called AFTER dependency resolution.
     *
     * @param plugin Plugin to load classes.
     */
    @Throws(PluginLoadException::class)
    fun load(plugin: PluginContainer)

}