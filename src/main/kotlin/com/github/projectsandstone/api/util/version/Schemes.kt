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
package com.github.projectsandstone.api.util.version

/**
 * Version schemes
 */
object Schemes {
    @JvmStatic
    internal lateinit var semVerScheme_: VersionScheme

    /**
     * Semantic versioning scheme.
     */
    @JvmStatic
    val semVerScheme: VersionScheme
        get() = this.semVerScheme_


    /**
     * Default version scheme, always returns false for [VersionScheme.isCompatible].
     */
    @JvmStatic
    val commonVersionScheme: VersionScheme = CommonVersionScheme

    /**
     * Alphabetic ordered version scheme.
     */
    @JvmStatic
    val alphabeticScheme: VersionScheme = AlphabeticVersionScheme

    internal object CommonVersionScheme : VersionScheme {
        override fun compare(o1: Version?, o2: Version?): Int = -1

        override fun isCompatible(version1: Version, version2: Version): Boolean = false
    }

    internal object AlphabeticVersionScheme : VersionScheme {

        override fun isCompatible(version1: Version, version2: Version): Boolean =
                version1.versionScheme == version2.versionScheme && version1.versionString == version2.versionString

        override fun compare(o1: Version, o2: Version): Int =
                if (o1.versionScheme != o2.versionScheme) -1
                else o1.versionString.compareTo(o2.versionString)

    }

}