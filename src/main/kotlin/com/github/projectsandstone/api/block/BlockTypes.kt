/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2017 Sandstone <https://github.com/ProjectSandstone/>
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
package com.github.projectsandstone.api.block

import com.github.projectsandstone.api.registry.Registry
import com.github.projectsandstone.api.util.extension.registry.uninitializedEntry

/**
 * All known block types.
 *
 * This is only a list of all blocks present in all versions of minecraft (PE and PC),
 * if you wan't to get an [BlockType] that aren't listed here, you must to fetch directly from [Registry].
 */
object BlockTypes {

    @JvmField
    val AIR: BlockType = uninitializedEntry()

    @JvmField
    val STONE: BlockType = uninitializedEntry()

    @JvmField
    val GRASS_BLOCK: BlockType = uninitializedEntry()

    @JvmField
    val DIRT: BlockType = uninitializedEntry()

    @JvmField
    val COBBLESTONE: BlockType = uninitializedEntry()

    @JvmField
    val WOOD_PLANKS: BlockType = uninitializedEntry()

    @JvmField
    val SAPLING: BlockType = uninitializedEntry()

    @JvmField
    val BEDROCK: BlockType = uninitializedEntry()

    @JvmField
    val WATER: BlockType = uninitializedEntry()

    @JvmField
    val STATIONARY_WATER: BlockType = uninitializedEntry()

    @JvmField
    val LAVA: BlockType = uninitializedEntry()

    @JvmField
    val STATIONARY_LAVA: BlockType = uninitializedEntry()

    @JvmField
    val SAND: BlockType = uninitializedEntry()

    @JvmField
    val GRAVEL: BlockType = uninitializedEntry()

    @JvmField
    val GOLD_ORE: BlockType = uninitializedEntry()

    @JvmField
    val IRON_ORE: BlockType = uninitializedEntry()

    @JvmField
    val COAL_ORE: BlockType = uninitializedEntry()

    @JvmField
    val WOOD: BlockType = uninitializedEntry()

    @JvmField
    val LEAVES: BlockType = uninitializedEntry()

    @JvmField
    val SPONGE: BlockType = uninitializedEntry()

    @JvmField
    val GLASS: BlockType = uninitializedEntry()

    @JvmField
    val LAPIS_LAZULI_ORE: BlockType = uninitializedEntry()

    @JvmField
    val LAPIS_LAZULI_BLOCK: BlockType = uninitializedEntry()

    @JvmField
    val DISPENSER: BlockType = uninitializedEntry()

    @JvmField
    val SANDSTONE: BlockType = uninitializedEntry()

    @JvmField
    val NOTE_BLOCK: BlockType = uninitializedEntry()

    @JvmField
    val BED: BlockType = uninitializedEntry()

    @JvmField
    val POWERED_RAIL: BlockType = uninitializedEntry()

    @JvmField
    val DETECTOR_RAIL: BlockType = uninitializedEntry()

    @JvmField
    val STICKY_PISTON: BlockType = uninitializedEntry()

    @JvmField
    val COBWEB: BlockType = uninitializedEntry()

    @JvmField
    val TALL_GRASS: BlockType = uninitializedEntry()

    @JvmField
    val DEAD_BUSH: BlockType = uninitializedEntry()

    @JvmField
    val PISTON_HEAD: BlockType = uninitializedEntry()

    @JvmField
    val WOOL: BlockType = uninitializedEntry()

    @JvmField
    val YELLOW_FLOWER: BlockType = uninitializedEntry()

    @JvmField
    val RED_FLOWER: BlockType = uninitializedEntry()

    @JvmField
    val BROWN_MUSHROOM: BlockType = uninitializedEntry()

    @JvmField
    val RED_MUSHROOM: BlockType = uninitializedEntry()

    @JvmField
    val BLOCK_OF_GOLD: BlockType = uninitializedEntry()

    @JvmField
    val BLOCK_OF_IRON: BlockType = uninitializedEntry()

    @JvmField
    val DOUBLE_STONE_SLAB: BlockType = uninitializedEntry()

    @JvmField
    val STONE_SLAB: BlockType = uninitializedEntry()

    @JvmField
    val BRICKS: BlockType = uninitializedEntry()

    @JvmField
    val TNT: BlockType = uninitializedEntry()

    @JvmField
    val BOOKSHELF: BlockType = uninitializedEntry()

    @JvmField
    val MOSS_STONE: BlockType = uninitializedEntry()

    @JvmField
    val OBSIDIAN: BlockType = uninitializedEntry()

    @JvmField
    val TORCH: BlockType = uninitializedEntry()

    @JvmField
    val FIRE: BlockType = uninitializedEntry()

    @JvmField
    val MOB_SPAWNER: BlockType = uninitializedEntry()

    @JvmField
    val OAK_WOOD_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val CHEST: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_WIRE: BlockType = uninitializedEntry()

    @JvmField
    val DIAMOND_ORE: BlockType = uninitializedEntry()

    @JvmField
    val BLOCK_OF_DIAMOND: BlockType = uninitializedEntry()

    @JvmField
    val CRAFTING_TABLE: BlockType = uninitializedEntry()

    @JvmField
    val CROPS: BlockType = uninitializedEntry()

    @JvmField
    val FARMLAND: BlockType = uninitializedEntry()

    @JvmField
    val FURNACE: BlockType = uninitializedEntry()

    @JvmField
    val BURNING_FURNACE: BlockType = uninitializedEntry()

    @JvmField
    val SIGN: BlockType = uninitializedEntry()

    @JvmField
    val WOODEN_DOOR: BlockType = uninitializedEntry()

    @JvmField
    val LADDER: BlockType = uninitializedEntry()

    @JvmField
    val RAIL: BlockType = uninitializedEntry()

    @JvmField
    val COBBLESTONE_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val WALL_SIGN: BlockType = uninitializedEntry()

    @JvmField
    val LEVER: BlockType = uninitializedEntry()

    @JvmField
    val STONE_PRESSURE_PLATE: BlockType = uninitializedEntry()

    @JvmField
    val WOODEN_PRESSURE_PLATE: BlockType = uninitializedEntry()

    @JvmField
    val IRON_DOOR: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_ORE: BlockType = uninitializedEntry()

    @JvmField
    val GLOWING_REDSTONE_ORE: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_TORCH: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_TORCH_ACTIVE: BlockType = uninitializedEntry()

    @JvmField
    val STONE_BUTTON: BlockType = uninitializedEntry()

    @JvmField
    val SNOW_LAYER: BlockType = uninitializedEntry()

    @JvmField
    val ICE: BlockType = uninitializedEntry()

    @JvmField
    val SNOW: BlockType = uninitializedEntry()

    @JvmField
    val CACTUS: BlockType = uninitializedEntry()

    @JvmField
    val CLAY: BlockType = uninitializedEntry()

    @JvmField
    val SUGAR_CANE: BlockType = uninitializedEntry()

    @JvmField
    val FENCE: BlockType = uninitializedEntry()

    @JvmField
    val PUMPKIN: BlockType = uninitializedEntry()

    @JvmField
    val NETHERRACK: BlockType = uninitializedEntry()

    @JvmField
    val SOUL_SAND: BlockType = uninitializedEntry()

    @JvmField
    val GLOWSTONE: BlockType = uninitializedEntry()

    @JvmField
    val PORTAL: BlockType = uninitializedEntry()

    @JvmField
    val JACK_O_LANTERN: BlockType = uninitializedEntry()

    @JvmField
    val CAKE: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_REPEATER: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_REPEATER_ACTIVE: BlockType = uninitializedEntry()

    @JvmField
    val TRAPDOOR: BlockType = uninitializedEntry()

    @JvmField
    val MONSTER_EGG: BlockType = uninitializedEntry()

    @JvmField
    val STONE_BRICK: BlockType = uninitializedEntry()

    @JvmField
    val IRON_BARS: BlockType = uninitializedEntry()

    @JvmField
    val GLASS_PANE: BlockType = uninitializedEntry()

    @JvmField
    val MELON: BlockType = uninitializedEntry()

    @JvmField
    val PUMPKIN_SEEDS: BlockType = uninitializedEntry()

    @JvmField
    val MELON_SEEDS: BlockType = uninitializedEntry()

    @JvmField
    val VINES: BlockType = uninitializedEntry()

    @JvmField
    val FENCE_GATE: BlockType = uninitializedEntry()

    @JvmField
    val BRICK_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val STONE_BRICK_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val MYCELIUM: BlockType = uninitializedEntry()

    @JvmField
    val LILY_PAD: BlockType = uninitializedEntry()

    @JvmField
    val NETHER_BRICK: BlockType = uninitializedEntry()

    @JvmField
    val NETHER_BRICK_FENCE: BlockType = uninitializedEntry()

    @JvmField
    val NETHER_BRICK_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val NETHER_WART: BlockType = uninitializedEntry()

    @JvmField
    val ENCHANTMENT_TABLE: BlockType = uninitializedEntry()

    @JvmField
    val BREWING_STAND: BlockType = uninitializedEntry()

    @JvmField
    val CAULDRON: BlockType = uninitializedEntry()

    @JvmField
    val END_PORTAL_FRAME: BlockType = uninitializedEntry()

    @JvmField
    val END_STONE: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_LAMP: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_LAMP_ACTIVE: BlockType = uninitializedEntry()

    @JvmField
    val DROPPER: BlockType = uninitializedEntry()

    @JvmField
    val ACTIVATOR_RAIL: BlockType = uninitializedEntry()

    @JvmField
    val COCOA: BlockType = uninitializedEntry()

    @JvmField
    val SANDSTONE_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val EMERALD_ORE: BlockType = uninitializedEntry()

    @JvmField
    val TRIPWIRE_HOOK: BlockType = uninitializedEntry()

    @JvmField
    val TRIPWIRE: BlockType = uninitializedEntry()

    @JvmField
    val BLOCK_OF_EMERALD: BlockType = uninitializedEntry()

    @JvmField
    val SPRUCE_WOOD_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val BIRCH_WOOD_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val JUNGLE_WOOD_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val COBBLESTONR_WALL: BlockType = uninitializedEntry()

    @JvmField
    val FLOWER_POT: BlockType = uninitializedEntry()

    @JvmField
    val CARROTS: BlockType = uninitializedEntry()

    @JvmField
    val POTATO: BlockType = uninitializedEntry()

    @JvmField
    val WOODEN_BUTTON: BlockType = uninitializedEntry()

    @JvmField
    val MOB_HEAD: BlockType = uninitializedEntry()

    @JvmField
    val ANVIL: BlockType = uninitializedEntry()

    @JvmField
    val TRAPPED_CHEST: BlockType = uninitializedEntry()

    @JvmField
    val WEIGHTED_PRESSURE_PLATE_LIGHT: BlockType = uninitializedEntry()

    @JvmField
    val WEIGHTED_PRESSURE_PLATE_HEAVY: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_COMPARATOR: BlockType = uninitializedEntry()

    @JvmField
    val REDSTONE_COMPARATOR_POWERED: BlockType = uninitializedEntry()

    @JvmField
    val DAYLIGHT_SENSOR: BlockType = uninitializedEntry()

    @JvmField
    val BLOCK_OF_REDSTONE: BlockType = uninitializedEntry()

    @JvmField
    val NETHER_QUARTZ_ORE: BlockType = uninitializedEntry()

    @JvmField
    val HOPPER: BlockType = uninitializedEntry()

    @JvmField
    val BLOCK_OF_QUARTZ: BlockType = uninitializedEntry()

    @JvmField
    val QUARTZ_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val WOODEN_DOUBLE_SLAB: BlockType = uninitializedEntry()

    @JvmField
    val WOODEN_SLAB: BlockType = uninitializedEntry()

    @JvmField
    val STAINED_CLAY: BlockType = uninitializedEntry()

    @JvmField
    val ACACIA_LEAVES: BlockType = uninitializedEntry()

    @JvmField
    val ACACIA_WOOD: BlockType = uninitializedEntry()

    @JvmField
    val ACACIA_WOOD_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val DARK_OAK_WOOD_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val SLIME_BLOCK: BlockType = uninitializedEntry()

    @JvmField
    val IRON_TRAPDOOR: BlockType = uninitializedEntry()

    @JvmField
    val HEY_BALE: BlockType = uninitializedEntry()

    @JvmField
    val CARPET: BlockType = uninitializedEntry()

    @JvmField
    val HARDENED_CLAY: BlockType = uninitializedEntry()

    @JvmField
    val BLOCK_OF_COAL: BlockType = uninitializedEntry()

    @JvmField
    val PACKED_ICE: BlockType = uninitializedEntry()

    @JvmField
    val SUNFLOWER: BlockType = uninitializedEntry()

    @JvmField
    val INVERTED_DAYLIGHT_SENSOR: BlockType = uninitializedEntry()

    @JvmField
    val RED_SANDSTONE: BlockType = uninitializedEntry()

    @JvmField
    val RED_SANDSTONE_STAIRS: BlockType = uninitializedEntry()

    @JvmField
    val DOUBLE_RED_SANDSTONE_SLAB: BlockType = uninitializedEntry()

    @JvmField
    val RED_SANDSTONE_SLAB: BlockType = uninitializedEntry()

    @JvmField
    val SPRUCE_FENCE_GATE: BlockType = uninitializedEntry()

    @JvmField
    val BIRCH_FENCE_GATE: BlockType = uninitializedEntry()

    @JvmField
    val JUNGLE_FENCE_GATE: BlockType = uninitializedEntry()

    @JvmField
    val DARK_OAK_FENCE_GATE: BlockType = uninitializedEntry()

    @JvmField
    val ACACIA_FENCE_GATE: BlockType = uninitializedEntry()

    @JvmField
    val SPRUCE_DOOR: BlockType = uninitializedEntry()

    @JvmField
    val BIRCH_DOOR: BlockType = uninitializedEntry()

    @JvmField
    val JUNGLE_GOOR: BlockType = uninitializedEntry()

    @JvmField
    val ACACIA_DOOR: BlockType = uninitializedEntry()

    @JvmField
    val DARK_OAK_DOOR: BlockType = uninitializedEntry()

    @JvmField
    val GRASS_PATH: BlockType = uninitializedEntry()

    @JvmField
    val ITEM_FRAME: BlockType = uninitializedEntry()

    @JvmField
    val PODZOL: BlockType = uninitializedEntry()

    @JvmField
    val BEETROOT: BlockType = uninitializedEntry()
}