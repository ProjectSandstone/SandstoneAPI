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
package com.github.projectsandstone.api.util.updater

import com.github.projectsandstone.api.util.version.Version
import java.net.URL

/**
 * [Release] searcher
 */
interface Searcher<out R : Release<*>> {

    /**
     * Project [Release]s API Url.
     */
    val url: URL

    /**
     * Find latest [Release] compatible with current minecraft and api version.
     * @return [UpdateQueryResult.success] holding latest compatible [Release],
     * [UpdateQueryResult.failed] if cannot contact server, or [UpdateQueryResult.upToDate] if
     * latest version is equals to current version.
     */
    fun findLatest(currentVersion: Version): UpdateQueryResult<R>

    /**
     * Find latest unstable (pre-release) [Release] compatible with current minecraft and api version.
     *
     * @return [UpdateQueryResult.success] holding latest unstable compatible [Release],
     * [UpdateQueryResult.failed] if cannot contact server, or [UpdateQueryResult.upToDate] if
     * latest version is equals to current version.
     */
    fun findLatestUnstable(currentVersion: Version): UpdateQueryResult<R>

    /**
     * Find all [Release]s
     * @return Query holding all [Release]s, or [UpdateQueryResult.failed] if cannot contact servers.
     */
    fun findAll(): UpdateQueryResult<List<R>>

    /**
     * Find specific [Release] by name.
     *
     * @return Query holding specific [Release], or [UpdateQueryResult.failed] if cannot contact
     * servers or [UpdateQueryResult.success] holding null [R] if cannot find version.
     */
    fun find(name: String): UpdateQueryResult<R>
}