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
 * Plugin definition annotation.
 *
 * @property id Plugin unique id, Recommended plugin id pattern is: 'groupId.artifactId', example:
 * `com.mydomain.simpleplugin`, `com.github.myuser.simpleplugin`
 * @property name Plugin name, used to identify the plugin.
 * @property version Version of the plugin, there is not rule.
 * @property description Plugin description.
 * @property authors Names of plugin authors.
 * @property dependencies Plugin dependencies
 * @property usePlatformInternals Whether the plugin use platform internals or not, if set to `true`, the implementation
 * allows use of platform internals, if set to `false`, implementation may warn about internals usage or fail with exception.
 * @property optional Set this property to true if this plugin is optional, this means that if required [dependencies] are not
 * satisfied, then this plugin will be simple ignored and a info will be logged in console (instead
 * of dependency exception).
 * @property targetPlatformName Used by platform dependent plugins, you should use
 * [com.github.projectsandstone.api.Platform.platformBaseName] to specify which platforms your plugin loads.
 * This is commonly used to enable plugin modules or different implementations for each platform. You can also use
 * `:` prefix to specify the [Full name of platform][com.github.projectsandstone.api.Platform.platformFullName] instead
 * of [Base name][com.github.projectsandstone.api.Platform.platformBaseName].
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Plugin(
        val id: String,
        val name: String = "",
        val version: String,
        val description: String = "",
        val authors: Array<String> = arrayOf(),
        val dependencies: Array<Dependency> = arrayOf(),
        val usePlatformInternals: Boolean = false,
        val optional: Boolean = false,
        val targetPlatformName: Array<String> = emptyArray()
)