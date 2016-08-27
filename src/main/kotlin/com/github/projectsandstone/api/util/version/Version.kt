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
package com.github.projectsandstone.api.util.version


data class Version(val versionString: String, val versionScheme: VersionScheme) : Comparable<Version> {
    /**
     * Version String

    val versionString: String


     * Version scheme

    val versionScheme: VersionScheme*/

    /**
     * Returns true if this [Version] is compatible with [version]
     *
     * @param version Version to check
     * @return True if this [Version] is compatible with [version]
     */
    fun isCompatibleWith(version: Version): Boolean = this.versionScheme.isCompatible(this, version)

    /**
     * Returns True if this [Version] is newer than [version]
     *
     * @param version Version to compare
     * @return True if this [Version] is newer than [version]
     */
    fun isNewerThan(version: Version) = this.compareTo(version) > 0

    /**
     * Returns True if this [Version] is older than [version]
     *
     * @param version Version to compare
     * @return True if this [Version] is older than [version]
     */
    fun isOlderThan(version: Version) = this.compareTo(version) < 0

    /**
     * Returns True if this [Version] is same as [version]
     *
     * @param version Version to compare
     * @return True if this [Version] is same as [version]
     */
    fun isSame(version: Version) = this.compareTo(version) == 0

    /**
     * Change scheme of this version
     *
     * @param versionScheme new version scheme.
     * @return new [Version] with changed [VersionScheme]
     */
    fun changeScheme(versionScheme: VersionScheme) = copy(versionScheme = versionScheme)

    override fun compareTo(other: Version): Int {
        return this.versionScheme.compare(this, other)
    }
}