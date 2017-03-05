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
package com.github.projectsandstone.api.world

import com.flowpowered.math.vector.Vector2i
import com.flowpowered.math.vector.Vector3d
import com.flowpowered.math.vector.Vector3i
import com.github.projectsandstone.api.block.BlockState
import com.github.projectsandstone.api.entity.Entity
import com.github.projectsandstone.api.event.Source
import com.github.projectsandstone.api.world.extent.Extent

/**
 * Helper class to work with Selections.
 */
data class Selection(val extent: Extent,
                     val from: Vector3d,
                     val to: Vector3d) {

    /**
     * All chunks inside this selection.
     */
    val chunks: Collection<Chunk>
        get() = this.extent.getChunks(
                from = Vector2i(this.from.x.toInt(), this.from.z.toInt()),
                to = Vector2i(this.to.x.toInt(), this.to.z.toInt()))

    /**
     * All entities inside this selection.
     */
    val entities: Collection<Entity>
        get() = this.extent.getEntities(this.from, this.to)

    /**
     * All blocks inside this selection.
     */
    val blocks: Collection<BlockState>
        get() = this.extent.getBlocks(this.from.toInt(), this.to.toInt())

    /**
     * Set all block states inside this selection to [blockState].
     */
    fun setBlocks(blockState: BlockState) =
            this.extent.setBlocks(this.from.toInt(), this.to.toInt(), blockState)

    /**
     * Set all block states inside this selection to [blockState] and provide the [source] cause of change.
     */
    fun setBlocks(blockState: BlockState, source: Source?) =
            this.extent.setBlocks(this.from.toInt(), this.to.toInt(), blockState, source)

    /**
     * Set all block states inside this selection to block states provided by [stateProvider].
     */
    fun setBlocks(stateProvider: (blockState: BlockState, position: Vector3i) -> BlockState) =
            this.extent.setBlocks(this.from.toInt(), this.to.toInt(), stateProvider)


    /**
     * Set all block states inside this selection to block states provided by [stateProvider] and provide the [source] cause of change.
     */
    fun setBlocks(source: Source?, stateProvider: (blockState: BlockState, position: Vector3i) -> BlockState) =
            this.extent.setBlocks(this.from.toInt(), this.to.toInt(), source, stateProvider)

    /**
     * Adds [from] and [to] to [Selection.from] and [Selection.to] and return a new instance of [Selection] with these values.
     */
    fun plus(from: Vector3d, to: Vector3d): Selection {
        return this.copy(this.extent, this.from.add(from), this.to.add(to))
    }

    /**
     * Subtract [from] and [to] from [Selection.from] and [Selection.to] and return a new instance of [Selection] with these values.
     */
    fun minus(from: Vector3d, to: Vector3d): Selection {
        return this.copy(this.extent, this.from.sub(from), this.to.sub(to))
    }

}