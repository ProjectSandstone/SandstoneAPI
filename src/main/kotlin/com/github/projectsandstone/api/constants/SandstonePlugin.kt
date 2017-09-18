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
package com.github.projectsandstone.api.constants

import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.plugin.*
import com.github.projectsandstone.api.util.version.Version
import org.slf4j.Logger
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Represents the SandstonePlugin
 */
object SandstonePlugin : PluginContainer {

    override val id: String
        get() = Constants.SANDSTONE_PLUGIN_ID

    override val version: Version
        get() = Constants.SANDSTONE_PLUGIN_VERSION

    override val description: String?
        get() = Constants.SANDSTONE_PLUGIN_DESCRIPTION

    override val authors: List<String>
        get() = Constants.AUTHORS

    override val dependencies: List<DependencyContainer>
        get() = emptyList()

    override val dependenciesState: List<DependencyState>
        get() = emptyList()

    override val usePlatformInternals: Boolean
        get() = true

    override val optional: Boolean
        get() = false

    override val file: Path?
        get() = try {
            Paths.get(this::class.java.protectionDomain.codeSource.location.toURI())
        } catch (throwable: Throwable) {
            null
        }

    override val instance: Any?
        get() = Sandstone

    override val name: String
        get() = super.name

    override val state: PluginState
        get() = PluginState.LOADED

    override val logger: Logger
        get() = Sandstone.logger

    override val targetPlatformNames: List<String>
        get() = emptyList()

    override val classLoader: PluginClassLoader = SandstonePluginClassLoader
    override val mainClass: String = "com.github.projectsandstone.api.Sandstone"

    private object SandstonePluginClassLoader : PluginClassLoader {

        override val file: Path?
            get() = SandstonePlugin.file

        override val useInternal: Boolean
            get() = true

        override val classes: List<String>
            get() = emptyList()

        override val isInitialized: Boolean
            get() = true

        override val pluginContainers: List<PluginContainer> = listOf(SandstonePlugin)

    }

}