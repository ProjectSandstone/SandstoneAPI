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

import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.item.ItemType

/**
 * Item Stack, all [ItemStacks][ItemStack] should be a copy of the original stack.
 */
interface ItemStack {

    /**
     * Item
     */
    val item: ItemType

    /**
     * Quantity
     */
    var quantity: Int

    /**
     * Max stack quantity
     */
    val maxStackQuantity: Int

    /**
     * Creates a copy of current [ItemStack].
     */
    fun copy(item: ItemType = this.item, quantity: Int = this.quantity): ItemStack

    companion object Factory {
        fun of(item: ItemType, quantity: Int) = Sandstone.getObjectFactory().createItemStack(item, quantity)
    }

}