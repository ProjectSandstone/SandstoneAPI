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
package com.github.projectsandstone.api.entity

import com.github.projectsandstone.api.util.extension.registry.uninitializedEntry
import com.github.projectsandstone.api.registry.Registry

/**
 * All known entity types.
 *
 * This is only a list of all entities present in all versions of minecraft (PE and PC),
 * if you wan't to get an [EntityType] that aren't listed here, you must to fetch directly from [Registry].
 */
object EntityTypes {

    @JvmField
    val BAT: EntityType = uninitializedEntry()

    @JvmField
    val BLAZE: EntityType = uninitializedEntry()

    @JvmField
    val CHICKEN: EntityType = uninitializedEntry()

    @JvmField
    val COW: EntityType = uninitializedEntry()

    @JvmField
    val CREEPER: EntityType = uninitializedEntry()

    @JvmField
    val ENDERMAN: EntityType = uninitializedEntry()

    //@JvmField
    //val ENDERMITE: EntityType = uninitializedEntry()

    @JvmField
    val GHAST: EntityType = uninitializedEntry()

    //@JvmField
    //val GIANT: EntityType = uninitializedEntry()

    //@JvmField
    //val GUARDIAN: EntityType = uninitializedEntry()

    @JvmField
    val HORSE: EntityType = uninitializedEntry()

    @JvmField
    val MAGMA_CUBE: EntityType = uninitializedEntry()

    @JvmField
    val MUSHROOM: EntityType = uninitializedEntry()

    @JvmField
    val OCELOT: EntityType = uninitializedEntry()

    @JvmField
    val PIG: EntityType = uninitializedEntry()

    @JvmField
    val PLAYER: EntityType = uninitializedEntry()

    //@JvmField
    //val POLAR_BEAR: EntityType = uninitializedEntry()

    @JvmField
    val RABBIT: EntityType = uninitializedEntry()

    @JvmField
    val SHEEP: EntityType = uninitializedEntry()

    @JvmField
    val SILVERFISH: EntityType = uninitializedEntry()

    @JvmField
    val SKELETON: EntityType = uninitializedEntry()

    @JvmField
    val SLIME: EntityType = uninitializedEntry()

    @JvmField
    val SPIDER: EntityType = uninitializedEntry()

    @JvmField
    val SQUID: EntityType = uninitializedEntry()

    @JvmField
    val VILLAGER: EntityType = uninitializedEntry()

    @JvmField
    val WITCH: EntityType = uninitializedEntry()

    @JvmField
    val WITHER: EntityType = uninitializedEntry()

    @JvmField
    val WOLF: EntityType = uninitializedEntry()

    @JvmField
    val ZOMBIE: EntityType = uninitializedEntry()

    @JvmField
    val ZOMBIE_PIGMAN: EntityType = uninitializedEntry()
}