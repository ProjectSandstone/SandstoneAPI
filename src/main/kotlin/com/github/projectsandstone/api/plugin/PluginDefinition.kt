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

import com.github.projectsandstone.api.logging.Logger
import com.github.projectsandstone.api.util.version.Version
import com.google.inject.Injector
import java.nio.file.Path

/**
 * Mutable [PluginContainer], changes of this instance will be reflected in [PluginContainer],
 *
 * These changes is only reflected during instance definition ([Injector.getInstance]).
 */
interface PluginDefinition {
    /**
     * Plugin unique id.
     *
     * *Recommended:* Recommended plugin id pattern is: 'groupId.artifactId', example:
     * `com.mydomain.simpleplugin`, `com.github.myuser.simpleplugin`
     */
    var id: String

    /**
     * Plugin Name.
     *
     * This name will be used to log messages.
     */
    var name: String

    /**
     * Plugin version.
     *
     * There is no rule for version definition.
     */
    var version: Version

    /**
     * Apply version changes
     */
    fun applyVersion(f: (Version) -> Version) {
        this.version = f(this.version)
    }

    /**
     * Plugin description.
     *
     * Short description explaining plugin functionality.
     */
    var description: String?

    /**
     * Authors of plugin
     */
    var authors: Array<String>?

    /**
     * Plugin dependencies.
     */
    val dependencies: Array<DependencyContainer>?

    /**
     * True if this plugin uses platform dependant functions.
     */
    var usePlatformInternals: Boolean

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
}