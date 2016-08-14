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

/**
 * Plugin definition annotation.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Plugin(
        /**
         * Plugin unique id.
         *
         * *Recommended:* Recommended plugin id pattern is: 'groupId.artifactId', example:
         * `com.mydomain.simpleplugin`, `com.github.myuser.simpleplugin`
         */
        val id: String,

        /**
         * Plugin Name.
         *
         * This name will be used to log messages.
         */
        val name: String = "",

        /**
         * Plugin version.
         *
         * There is no rule for version definition.
         */
        val version: String,

        /**
         * Plugin description.
         *
         * Short description explaining plugin functionality.
         */
        val description: String = "",

        /**
         * Authors of plugin
         */
        val authors: Array<String> = arrayOf(),

        /**
         * Plugin dependencies.
         */
        val dependencies: Array<Dependency> = arrayOf(),

        /**
         * Set this property to true if this plugin uses platform dependant functions.
         *
         * If you set this property to true, *Sandstone* will not warn about internal class usages.
         */
        val usePlatformInternals: Boolean = false
)