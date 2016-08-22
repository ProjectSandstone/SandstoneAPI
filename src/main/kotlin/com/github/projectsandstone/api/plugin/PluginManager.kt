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

import com.github.projectsandstone.api.util.exception.CircularDependencyException
import com.github.projectsandstone.api.util.exception.DependencyException
import com.github.projectsandstone.api.util.exception.MissingDependencyException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.NotDirectoryException
import java.nio.file.Path

/**
 * Created by jonathan on 12/08/16.
 */
/**
 * Implementations MUST check dependencies using [dependencyResolver].
 * After dependency resolution, implementation MUST call [pluginLoader] [PluginLoader.load] method.
 *
 * Plugin File loading process: Call [PluginLoader.loadFile], set state to
 * [PluginState.ABOUT_TO_LOAD], add to a [List] of [PluginContainer].
 *
 * Loop plugin list, call [loadPlugin], [loadPlugin] set plugin state to [PluginState.LOADING],
 * check dependencies using [DependencyResolver.checkDependencies], and finally call [PluginLoader.load]
 *
 */
interface PluginManager {

    /**
     * Dependency resolver
     */
    val dependencyResolver: DependencyResolver

    /**
     * Plugin loader
     */
    val pluginLoader: PluginLoader

    /**
     * Add a [PluginContainer] to pluginSet.
     *
     * @throws MissingDependencyException
     * @return True if [pluginContainer] was loaded successfully, else otherwise. (Errors will be logged to console).
     */
    fun loadPlugin(pluginContainer: PluginContainer): Boolean

    /**
     * Load uninitialized plugins from pluginSet.
     *
     * @throws MissingDependencyException
     * @throws CircularDependencyException
     */
    @Throws(MissingDependencyException::class, CircularDependencyException::class)
    fun loadPlugins()

    /**
     * Load plugin from file specified in [file]
     *
     * Exceptions is not necessarily throw-ed, this exceptions can be logged.
     *
     * @return Loaded [PluginContainer]s, a plugin file can contains multiple [Plugin] annotations.
     */
    @Throws(SecurityException::class, IOException::class, OutOfMemoryError::class,
            MissingDependencyException::class, CircularDependencyException::class)
    fun loadPlugin(file: Path): List<PluginContainer>

    /**
     * Load all plugins in [directory].
     *
     * @return Loaded [PluginContainer]s, or empty list if cannot load any plugin in directory. (Errors will be logged to console).
     */
    @Throws(SecurityException::class, IOException::class, NotDirectoryException::class, DependencyException::class)
    fun loadPlugins(directory: Path): List<PluginContainer> {
        return Files.newDirectoryStream(directory).flatMap {
            this.loadPlugin(it)
        }.filterNotNull()
    }

    /**
     * Get the plugin, if found, return the [PluginContainer] of plugin, otherwise return null
     *
     * @param id Id of plugin
     * @return The [PluginContainer] of plugin, or null if plugin isn't loaded.
     */
    fun getPlugin(id: String): PluginContainer?

    /**
     * Gets the [PluginContainer] from the instance.
     *
     * @param instance Plugin instance
     * @return [PluginContainer] if found, null otherwise
     */
    fun getPlugin(instance: Any): PluginContainer? {
        return this.getPlugins().find { it.instance != null && it.instance == instance }
    }

    /**
     * Gets a required [PluginContainer] from the instance.
     *
     * @param instance Plugin Instance
     * @throws IllegalArgumentException If the [instance] is not a plugin instance
     * @return [PluginContainer] of the plugin instance
     */
    fun getRequiredPlugin(instance: Any): PluginContainer {
        val plugin = this.getPlugin(instance = instance) ?: throw IllegalArgumentException("The provided instance is not a plugin!")

        return plugin
    }

    /**
     * Get first plugin matching [predicate].
     *
     * @param predicate Plugin predicate
     * @return First found [PluginContainer], or null if no plugin was found.
     */
    fun getPlugin(predicate: (PluginContainer) -> Boolean): PluginContainer? {
        return this.getPlugins().find(predicate)
    }

    /**
     * Get all plugins matching [predicate].
     *
     * @param predicate Plugin predicate
     * @return All plugins that matched [predicate], or empty list if no plugin matches the predicate.
     */
    fun getPlugins(predicate: (PluginContainer) -> Boolean): List<PluginContainer> {
        return this.getPlugins().filter(predicate)
    }

    /**
     * Get all loaded plugins.
     *
     * @return [Set] containing all loaded plugins.
     */
    fun getPlugins(): Set<PluginContainer>
}
