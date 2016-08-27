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
package com.github.projectsandstone.api.util.updater.asset

import com.github.projectsandstone.api.util.updater.Uploader
import com.github.projectsandstone.api.util.updater.Release
import java.net.URL
import java.time.LocalDateTime

/**
 * Representation of an Asset of a [Release].
 */
interface Asset<out ID> {

    /**
     * Asset URL (Asset Web Page)
     */
    val url: URL

    /**
     * Asset download url (direct link)
     */
    val downloadUrl: URL

    /**
     * Asset ID
     */
    val id: ID

    /**
     * Asset name (Version)
     */
    val name: String

    /**
     * Asset label (description)
     */
    val label: String?

    /**
     * Asset state
     */
    val state: AssetState

    /**
     * Content-type (application/zip for example)
     */
    val contentType: String

    /**
     * Size of Asset
     */
    val size: Long

    /**
     * Downloads of asset
     */
    val downloads: Long

    /**
     * Date of asset creation
     */
    val creationDate: LocalDateTime

    /**
     * Date of asset update
     */
    val updatedDate: LocalDateTime

    /**
     * Uploader of asset
     */
    val uploader: Uploader<*>
}