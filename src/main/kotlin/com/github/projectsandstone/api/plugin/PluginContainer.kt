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

import com.github.projectsandstone.api.Platform
import com.github.projectsandstone.api.util.version.Version
import org.slf4j.Logger
import java.nio.file.Path

/**
 * Plugin information: Instance, Id, Name, Version, Description, etc.
 */
interface PluginContainer {

    /**
     * Plugin unique id.
     *
     * *Recommended:* Recommended plugin id pattern is: 'groupId.artifactId', example:
     * `com.mydomain.simpleplugin`, `com.github.myuser.simpleplugin`
     */
    val id: String

    /**
     * Plugin Name.
     *
     * This name will be used to log messages.
     */
    val name: String
        get() = id

    /**
     * Plugin version.
     *
     * There is no rule for version definition.
     */
    val version: Version

    /**
     * Plugin description.
     *
     * Short description explaining plugin functionality.
     */
    val description: String?

    /**
     * Authors of plugin
     */
    val authors: List<String>

    /**
     * Plugin dependencies.
     */
    val dependencies: List<DependencyContainer>

    /**
     * State of dependencies of this [PluginContainer].
     */
    val dependenciesState: List<DependencyState>

    /**
     * True if this plugin uses platform dependant functions.
     */
    val usePlatformInternals: Boolean

    /**
     * True if this plugin is optional.
     *
     * @see Plugin.optional
     */
    val optional: Boolean

    /**
     * Plugin file if present, null otherwise.
     */
    val file: Path?

    /**
     * Plugin instance if present, null otherwise.
     */
    val instance: Any?

    /**
     * Plugin logger.
     */
    val logger: Logger

    /**
     * Plugin state.
     */
    val state: PluginState

    /**
     * Plugin class loader.
     */
    val classLoader: PluginClassLoader

    /**
     * Main class of this plugin.
     */
    val mainClass: String

    /**
     * Platforms which this plugins enables.
     *
     * @see Plugin.targetPlatformNames
     */
    val targetPlatformNames: List<String>

    /**
     * Which platform this plugin belongs to. Sandstone plugins always belongs to `Sandstone Platform`.
     */
    val platform: Platform
}