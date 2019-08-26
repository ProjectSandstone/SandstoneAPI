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
package com.github.projectsandstone.api.inventory

import com.github.projectsandstone.api.Named
import com.github.projectsandstone.api.item.ItemType
import java.util.stream.Stream

/**
 * Base interface of inventories.
 */
interface Inventory : Iterable<Inventory>, Named {

    /**
     * Inventory size.
     */
    val size: Int

    /**
     * Number of stacks that this inventory can hold.
     */
    val capacity: Int

    /**
     * Returns true if the inventory is empty.
     */
    val isEmpty: Boolean

    /**
     * Returns the parent inventory (or `this` if there is no parent inventory).
     */
    val parent: Inventory

    /**
     * Gets all [ItemStacks][ItemStack] that holds item of type [itemType].
     */
    operator fun get(itemType: ItemType): List<ItemStack>

    /**
     * Get and remove first available [ItemStack].
     */
    fun poll(): ItemStack?

    /**
     * Get first available [ItemStack].
     */
    fun peek(): ItemStack?

    /**
     * Get and remove all [ItemStacks][ItemStack] available in this inventory and child inventories.
     */
    fun pollAll(): List<ItemStack>

    /**
     * Get all [ItemStacks][ItemStack] available in this inventory and child inventories.
     */
    fun peekAll(): List<ItemStack>

    /**
     * Offers a [itemStack] to inventory.
     *
     * @return A transaction holding added and rejected stacks.
     */
    fun offer(itemStack: ItemStack): TransactionResult<ItemStack>

    /**
     * Offers [itemStacks] to inventory.
     *
     * @return A transaction holding added and rejected stacks.
     */
    fun offerAll(itemStacks: List<ItemStack>): TransactionResult<List<ItemStack>>

    /**
     * Take [quantity] [ItemStacks][ItemStack] that holds item of type [itemType].
     *
     * [ItemStacks][ItemStack] will not be removed if the operation fails.
     *
     * @return List of all found [ItemStacks][ItemStack].
     */
    fun take(quantity: Int, itemType: ItemType): OperationResult<List<ItemStack>>

    /**
     * Query a sub inventory, example: Hotbar, Equipment.
     */
    fun <T : Inventory> query(vararg type: Class<*>): T?

    /**
     * Returns true if this inventory contains [stack].
     */
    fun contains(stack: ItemStack): Boolean

    /**
     * Returns true if this inventory contains a stack of an item of type [itemType].
     */
    fun contains(itemType: ItemType): Boolean

    /**
     * Remove all items of this inventory and child inventories.
     */
    fun clear()

    /**
     * Creates a stream for this inventory.
     */
    fun stream(): Stream<Inventory>

}
