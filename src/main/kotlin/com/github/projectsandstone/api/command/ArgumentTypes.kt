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
package com.github.projectsandstone.api.command

import com.github.jonathanxd.kwcommands.argument.ArgumentType
import com.github.jonathanxd.kwcommands.command.Command
import com.github.jonathanxd.kwcommands.parser.SingleInput
import com.github.projectsandstone.api.block.BlockType
import com.github.projectsandstone.api.entity.EntityType
import com.github.projectsandstone.api.entity.living.player.Player
import com.github.projectsandstone.api.item.ItemType
import com.github.projectsandstone.api.plugin.PluginContainer

/**
 * Provider of argument types
 */
interface ArgumentTypes {
    /**
     * [Player] argument type.
     */
    fun player(): ArgumentType<SingleInput, Player>

    /**
     * [PluginContainer] argument type.
     */
    fun plugin(): ArgumentType<SingleInput, PluginContainer>

    /**
     * [Command] argument type.
     */
    fun command(): ArgumentType<SingleInput, Command>

    /**
     * [EntityType] argument type.
     */
    fun entityType(): ArgumentType<SingleInput, EntityType>

    /**
     * [ItemType] argument type.
     */
    fun itemType(): ArgumentType<SingleInput, ItemType>

    /**
     * [BlockType] argument type.
     */
    fun blockType(): ArgumentType<SingleInput, BlockType>
}