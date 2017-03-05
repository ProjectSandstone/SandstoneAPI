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
 * *Sandstone* plugin dependency.
 *
 * *Important:* Only *Sandstone* plugin dependency is supported.
 */
@Retention(AnnotationRetention.RUNTIME)
annotation class Dependency(
        /**
         * Id of dependency plugin.
         */
        val id: String,

        /**
         * Version of dependency (*Regex*).
         *
         * *Note:* if this property is not set, all dependency versions will be accepted.
         *
         * You can use [PluginManager] to check dependency versions.
         */
        val version: String = ".*",

        /**
         * Incompatible versions (*Regex*).
         */
        val incompatibleVersions: String = "",

        /**
         * True if the dependency is required, false otherwise. *Default value is true*.
         *
         * The server may or may not start, the behavior depends on platform. If the server starts,
         * this plugin will not be loaded, and all other plugins that depends on this plugin (as required dependency) will
         * not be loaded.
         */
        val isRequired: Boolean = true
)