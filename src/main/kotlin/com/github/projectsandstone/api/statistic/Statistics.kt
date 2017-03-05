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
package com.github.projectsandstone.api.statistic

import com.github.projectsandstone.api.registry.Registry
import com.github.projectsandstone.api.util.extension.registry.uninitializedEntry

/**
 * All known statistics.
 *
 * This is only a list of all statistics present in all versions of minecraft (PE and PC),
 * if you want to get an [Statistic] that aren't listed here, you must to fetch directly from [Registry].
 *
 * **From Sponge Statistics**
 */
object Statistics {

    @JvmField
    val ANIMALS_BRED: Statistic = uninitializedEntry()

    @JvmField
    val ARMOR_CLEANED: Statistic = uninitializedEntry()

    @JvmField
    val BANNER_CLEANED: Statistic = uninitializedEntry()

    @JvmField
    val BEACON_INTERACTION: Statistic = uninitializedEntry()

    @JvmField
    val BOAT_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val BREWINGSTAND_INTERACTION: Statistic = uninitializedEntry()

    @JvmField
    val CAKE_SLICES_EATEN: Statistic = uninitializedEntry()

    @JvmField
    val CAULDRON_FILLED: Statistic = uninitializedEntry()

    @JvmField
    val CAULDRON_USED: Statistic = uninitializedEntry()

    @JvmField
    val CHEST_OPENED: Statistic = uninitializedEntry()

    @JvmField
    val CLIMB_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val CRAFTING_TABLE_INTERACTION: Statistic = uninitializedEntry()

    @JvmField
    val CROUCH_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val DAMAGE_DEALT: Statistic = uninitializedEntry()

    @JvmField
    val DAMAGE_TAKEN: Statistic = uninitializedEntry()

    @JvmField
    val DEATHS: Statistic = uninitializedEntry()

    @JvmField
    val DISPENSER_INSPECTED: Statistic = uninitializedEntry()

    @JvmField
    val DIVE_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val DROPPER_INSPECTED: Statistic = uninitializedEntry()

    @JvmField
    val ENDERCHEST_OPENED: Statistic = uninitializedEntry()

    @JvmField
    val FALL_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val FISH_CAUGHT: Statistic = uninitializedEntry()

    @JvmField
    val FLOWER_POTTED: Statistic = uninitializedEntry()

    @JvmField
    val FLY_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val FURNACE_INTERACTION: Statistic = uninitializedEntry()

    @JvmField
    val HOPPER_INSPECTED: Statistic = uninitializedEntry()

    @JvmField
    val HORSE_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val ITEMS_DROPPED: Statistic = uninitializedEntry()

    @JvmField
    val ITEMS_ENCHANTED: Statistic = uninitializedEntry()

    @JvmField
    val JUMP: Statistic = uninitializedEntry()

    @JvmField
    val JUNK_FISHED: Statistic = uninitializedEntry()

    @JvmField
    val LEAVE_GAME: Statistic = uninitializedEntry()

    @JvmField
    val MINECART_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val MOB_KILLS: Statistic = uninitializedEntry()

    @JvmField
    val NOTEBLOCK_PLAYED: Statistic = uninitializedEntry()

    @JvmField
    val NOTEBLOCK_TUNED: Statistic = uninitializedEntry()

    @JvmField
    val PIG_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val PLAYER_KILLS: Statistic = uninitializedEntry()

    @JvmField
    val RECORD_PLAYED: Statistic = uninitializedEntry()

    @JvmField
    val SPRINT_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val SWIM_DISTANCE: Statistic = uninitializedEntry()

    @JvmField
    val TALKED_TO_VILLAGER: Statistic = uninitializedEntry()

    @JvmField
    val TIME_PLAYED: Statistic = uninitializedEntry()

    @JvmField
    val TIME_SINCE_DEATH: Statistic = uninitializedEntry()

    @JvmField
    val TRADED_WITH_VILLAGER: Statistic = uninitializedEntry()

    @JvmField
    val TRAPPED_CHEST_TRIGGERED: Statistic = uninitializedEntry()

    @JvmField
    val TREASURE_FISHED: Statistic = uninitializedEntry()

    @JvmField
    val WALK_DISTANCE: Statistic = uninitializedEntry()

}