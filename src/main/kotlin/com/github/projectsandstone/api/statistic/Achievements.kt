/**
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
package com.github.projectsandstone.api.statistic

import com.github.projectsandstone.api.registry.Registry
import com.github.projectsandstone.api.util.extension.registry.uninitializedEntry

/**
 * All known achievements.
 *
 * This is only a list of all achievements present in all versions of minecraft (PE and PC),
 * if you want to get an [Achievement] that aren't listed here, you must to fetch directly from [Registry].
 */
object Achievements {

    @JvmField
    val ACQUIRE_IRON: Achievement = uninitializedEntry()

    @JvmField
    val BAKE_CAKE: Achievement = uninitializedEntry()

    @JvmField
    val BOOKCASE: Achievement = uninitializedEntry()

    @JvmField
    val BREED_COW: Achievement = uninitializedEntry()

    @JvmField
    val BREW_POTION: Achievement = uninitializedEntry()

    @JvmField
    val BUILD_BETTER_PICKAXE: Achievement = uninitializedEntry()

    @JvmField
    val BUILD_FURNACE: Achievement = uninitializedEntry()

    @JvmField
    val BUILD_HOE: Achievement = uninitializedEntry()

    @JvmField
    val BUILD_PICKAXE: Achievement = uninitializedEntry()

    @JvmField
    val BUILD_SWORD: Achievement = uninitializedEntry()

    @JvmField
    val BUILD_WORKBENCH: Achievement = uninitializedEntry()

    @JvmField
    val COOK_FISH: Achievement = uninitializedEntry()

    @JvmField
    val DIAMONDS_TO_YOU: Achievement = uninitializedEntry()

    @JvmField
    val ENCHANTMENTS: Achievement = uninitializedEntry()

    @JvmField
    val END_PORTAL: Achievement = uninitializedEntry()

    @JvmField
    val EXPLORE_ALL_BIOMES: Achievement = uninitializedEntry()

    @JvmField
    val FLY_PIG: Achievement = uninitializedEntry()

    @JvmField
    val FULL_BEACON: Achievement = uninitializedEntry()

    @JvmField
    val GET_BLAZE_ROD: Achievement = uninitializedEntry()

    @JvmField
    val GET_DIAMONDS: Achievement = uninitializedEntry()

    @JvmField
    val GHAST_RETURN: Achievement = uninitializedEntry()

    @JvmField
    val KILL_COW: Achievement = uninitializedEntry()

    @JvmField
    val KILL_ENEMY: Achievement = uninitializedEntry()

    @JvmField
    val KILL_WHITER: Achievement = uninitializedEntry()

    @JvmField
    val MAKE_BREAD: Achievement = uninitializedEntry()

    @JvmField
    val MINE_WOOD: Achievement = uninitializedEntry()

    @JvmField
    val NETHER_PORTAL: Achievement = uninitializedEntry()

    @JvmField
    val ON_A_RAIL: Achievement = uninitializedEntry()

    @JvmField
    val OPEN_INVENTORY: Achievement = uninitializedEntry()

    @JvmField
    val OVERKILL: Achievement = uninitializedEntry()

    @JvmField
    val OVERPOWERED: Achievement = uninitializedEntry()

    @JvmField
    val SNIPE_SKELETON: Achievement = uninitializedEntry()

    @JvmField
    val SPAWN_WHITER: Achievement = uninitializedEntry()

    @JvmField
    val THE_END: Achievement = uninitializedEntry()


}