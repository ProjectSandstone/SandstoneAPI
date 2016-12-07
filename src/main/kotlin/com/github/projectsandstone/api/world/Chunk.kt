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
package com.github.projectsandstone.api.world

import com.flowpowered.math.vector.Vector2i
import com.flowpowered.math.vector.Vector3d
import com.flowpowered.math.vector.Vector3i
import com.github.projectsandstone.api.block.BlockState
import com.github.projectsandstone.api.entity.Entity
import com.github.projectsandstone.api.entity.EntityType
import com.github.projectsandstone.api.event.Source
import com.github.projectsandstone.api.text.Text
import com.github.projectsandstone.api.text.channel.MessageReceiver
import com.github.projectsandstone.api.util.extension.flow.math.plus
import com.github.projectsandstone.api.world.extent.Extent

/**
 * A [Chunk] of the [World].
 *
 * @param position Location of the chunk in the extent.
 */
data class Chunk(val extent: Extent,
                 val position: Vector2i) : Extent {

    private val selection: Selection =
            Selection(extent,
                    Vector3d(position.x.toDouble(), 0.0, position.y.toDouble()),
                    Vector3d(position.x + 16.0, 0.0, position.y + 16.0))

    override val loadedChunks: List<Chunk>
        get() = listOf(this)

    override val entities: Collection<Entity>
        get() = this.selection.entities

    override val world: World = this.extent.world

    override fun sendMessage(text: Text) {
        this.entities.forEach {
            if (it is MessageReceiver) {
                it.sendMessage(text)
            }
        }
    }

    override fun getChunk(location: Vector2i): Chunk = this

    override fun getChunks(from: Vector2i, to: Vector2i): Collection<Chunk> = listOf(this)

    override fun getEntities(from: Vector3d, to: Vector3d): Collection<Entity> =
            this.extent.getEntities(this.selection.from + from, this.selection.to + to)

    override fun getBlock(location: Vector3i): BlockState =
            this.extent.getBlock(this.selection.from.toInt() + location)

    override fun getBlocks(from: Vector3i, to: Vector3i): Collection<BlockState> =
            this.extent.getBlocks(this.selection.from.toInt() + from, this.selection.to.toInt() + to)

    override fun setBlock(location: Vector3i, blockState: BlockState, source: Source?) =
            this.extent.setBlock(this.selection.from.toInt() + location, blockState, source)

    override fun setBlocks(from: Vector3i, to: Vector3i, blockState: BlockState, source: Source?) =
            this.extent.setBlocks(this.selection.from.toInt() + from, this.selection.to.toInt() + to, blockState, source)

    override fun setBlocks(from: Vector3i, to: Vector3i, source: Source?, stateSupplier: (BlockState, Vector3i) -> BlockState) =
            this.extent.setBlocks(this.selection.from.toInt() + from, this.selection.to.toInt() + to, source, stateSupplier)

    override fun getLocation(position: Vector3d): Location<Chunk> {
        return Location(this, position)
    }

    override fun select(from: Vector3d, to: Vector3d): Selection {
        return Selection(this.extent, this.selection.from.add(from), this.selection.to.add(to))
    }

    override fun spawnEntity(type: EntityType, location: Location<*>): Entity =
            this.extent.spawnEntity(type, location.plus(selection.from))

    /**
     * Gets the location of the [Chunk] in the [World].
     *
     * @return Location of the [Chunk] in the [World].
     */
    fun getLocation(): Location<Extent> = this.extent.getLocation(position.x, 0, position.y)

    /**
     * Gets the selection of this [Chunk].
     *
     * @return Selection of this [Chunk].
     */
    fun getSelection(): Selection = this.selection

    override fun getWorldLocation(location: Location<Extent>): Location<World> {
        if(location.extent !is Chunk)
            throw IllegalArgumentException("The extent of location '$location' must be a Chunk.")

        val position = location.position

        val x = this.position.x + position.x //offset
        val y = position.y
        val z = this.position.y + position.z //offset

        return Location(this.world, Vector3d(x, y, z))
    }

}