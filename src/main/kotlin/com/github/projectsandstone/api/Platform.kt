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
package com.github.projectsandstone.api

import com.github.projectsandstone.api.feature.Feature
import com.github.projectsandstone.api.feature.FeatureManager
import com.github.projectsandstone.api.platform.PlatformObjectConverter
import com.github.projectsandstone.api.util.version.Version

/**
 * Represents a Platform.
 */
interface Platform {

    /**
     * Platform name.
     *
     * CraftBukkit, SpongeForge, SpongeVanilla, Spigot, etc.
     */
    val platformName: String

    /**
     * Platform base name.
     *
     * Bukkit-based (like Spigot, CraftBukkit, PaperSpigot): Bukkit
     *
     * Sponge-based (like SpongeForge and SpongeVanilla): Sponge
     *
     * Nukkit-base (like Nukkit?): Nukkit
     *
     */
    val platformBaseName: String

    /**
     * Platform version.
     *
     * *Note:* property value represents *PLATFORM VERSION* not *MINECRAFT VERSION*
     */
    val platformVersion: Version

    /**
     * Platform full name representation, the difference between the [platformFullName] and [platformName] is that
     * [platformName] returns a user-friendly name and [platformFullName] returns a full name retrieved directly from platform
     * methods.
     */
    val platformFullName: String

    /**
     * Minecraft version, not necessarily numeric representation.
     */
    val minecraftVersion: Version

    /**
     * Platform object converter.
     */
    val platformObjectConverter: PlatformObjectConverter

    /**
     * Feature manager of Platform.
     */
    val featureManager: FeatureManager

    /**
     * Returns true if [class name][name] is a class of platform.
     */
    fun isInternalClass(name: String?): Boolean

    /**
     * Gets feature of [type] [T].
     */
    fun <T: Feature<T>> getFeature(type: Class<T>): Feature<T>

}