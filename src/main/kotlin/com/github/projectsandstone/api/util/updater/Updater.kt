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
import java.nio.file.Path
import java.util.function.Consumer

/**
 * Created by jonathan on 27/08/16.
 */
interface Updater<R : Release<*>, out S : Searcher<R>> {
    /**
     * File to update
     */
    val file: Path

    /**
     * Current version
     */
    val currentVersion: Version

    /**
     * [Searcher] to be used to lookup for [Release]s.
     */
    val searcher: S

    /**
     * [UpdateApplier] class
     */
    val updateApplier: Class<UpdateApplier>

    /**
     * Check version updates (async)
     * @param c Consumer of update result.
     */
    fun checkUpdates(c: Consumer<UpdateQueryResult<R>>)

    /**
     * [checkUpdates] and download updates async (if available). These updates will be stored at updates directory.
     * When Sandstone start the initialization process, it will copy current version to a backup directory,
     * and then apply new updates. To delete backup versions, call [updateSuccess]. To rollback to
     * older version, call [updateFailed].
     */
    fun update()

    /**
     * Mark update as successfully.
     *
     * Delete current backup updates (and downloaded files).
     */
    fun updateSuccess()

    /**
     * Mark update as failed.
     *
     * A warning message will be shown in console.
     *
     * In the next *Sandstone* initialization, current version will be deleted and older version will
     * be restored from backup directory.
     */
    fun updateFailed()
}