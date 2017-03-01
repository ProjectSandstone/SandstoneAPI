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
package com.github.projectsandstone.api.plugin

import com.github.projectsandstone.api.util.exception.CircularDependencyException
import com.github.projectsandstone.api.util.exception.DependencyException
import com.github.projectsandstone.api.util.exception.MissingDependencyException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.NotDirectoryException
import java.nio.file.Path

/**
 *
 * Load and manages all plugins.
 *
 * Implementations MUST check dependencies using [dependencyResolver].
 * After dependency resolution, implementation MUST call [pluginLoader] [PluginLoader.load] method.
 *
 * Plugin File loading process:
 *
 * - Call [PluginLoader.loadFile], set state to [PluginState.ABOUT_TO_LOAD], add to a [List] of [PluginContainer].
 * - Loop plugin list, call [queue], [queue] will call [PluginLoader.load].
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
     * Load all plugins found in [classes].
     *
     * @param classes Classes of plugin.
     * @return Loaded [PluginContainer]s, or empty list if cannot load any plugin in directory. (Errors will be logged to console).
     */
    @Throws(MissingDependencyException::class, CircularDependencyException::class)
    fun loadPlugins(classes: Array<String>): List<PluginContainer>

    /**
     * Load plugin of [file].
     *
     * Exceptions is not necessarily throw-ed, this exceptions can be logged.
     *
     * @return Loaded [PluginContainer]s, a plugin file can contains multiple [Plugin] annotations.
     */
    @Throws(SecurityException::class, IOException::class, OutOfMemoryError::class,
            MissingDependencyException::class, CircularDependencyException::class)
    fun loadFile(file: Path): List<PluginContainer>

    /**
     * Load all plugins of [directory].
     *
     * @return Loaded [PluginContainer]s, or empty list if cannot load any plugin in directory. (Errors will be logged to console).
     */
    @Throws(SecurityException::class, IOException::class, NotDirectoryException::class, DependencyException::class)
    fun loadAll(directory: Path): List<PluginContainer> {
        return Files.newDirectoryStream(directory).flatMap {
            this.loadFile(it)
        }.filterNotNull()
    }

    /**
     * Add all plugins found in [classes] to plugin loading queue.
     *
     * @param classes Classes of plugin.
     * @return Loaded [PluginContainer]s, or empty list if cannot load any plugin in directory. (Errors will be logged to console).
     */
    fun queue(classes: Array<String>): List<PluginContainer>

    /**
     * Add plugin of [file] to `plugin loading queue`.
     *
     * Exceptions is not necessarily throw-ed, this exceptions can be logged.
     *
     * @return Loaded [PluginContainer]s, a plugin file can contains multiple [Plugin] annotations.
     */
    @Throws(SecurityException::class, IOException::class)
    fun queueFile(file: Path): List<PluginContainer>

    /**
     * Add all plugins of [directory] to `plugin loading queue`.
     *
     * @return Loaded [PluginContainer]s, or empty list if cannot load any plugin in directory. (Errors will be logged to console).
     */
    @Throws(SecurityException::class, IOException::class, NotDirectoryException::class)
    fun queueAll(directory: Path): List<PluginContainer> {
        return Files.newDirectoryStream(directory).flatMap {
            this.queueFile(it)
        }.filterNotNull()
    }

    /**
     * Load all plugins in `plugin loading queue`.
     *
     * @throws DependencyException
     * @return True if any plugin was loaded successfully.
     */
    @Throws(DependencyException::class)
    fun loadAllPlugins(): Boolean

    /**
     * Gets a plugin that failed to load.
     *
     * @param id Id of plugin
     * @return The [PluginContainer] of plugin that failed to load, or null if plugin isn't loaded.
     */
    fun getFailedPlugin(id: String): PluginContainer?

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
