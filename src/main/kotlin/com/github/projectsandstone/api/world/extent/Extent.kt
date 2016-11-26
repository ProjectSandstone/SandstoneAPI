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
package com.github.projectsandstone.api.world.extent

import com.flowpowered.math.vector.Vector2i
import com.flowpowered.math.vector.Vector3d
import com.flowpowered.math.vector.Vector3i
import com.github.projectsandstone.api.block.BlockState
import com.github.projectsandstone.api.event.Source
import com.github.projectsandstone.api.text.channel.MessageReceiver
import com.github.projectsandstone.api.util.extension.flow.math.rangeTo
import com.github.projectsandstone.api.world.Chunk
import com.github.projectsandstone.api.world.Location
import com.github.projectsandstone.api.world.Selection
import com.github.projectsandstone.api.world.World

/**
 * An [Extent] is a container of game objects (ex: Block, Entities, Tile Entities).
 *
 * Example of [Extent]s: [World], [Chunk]
 */
interface Extent : EntityUniverse, MessageReceiver {

    /**
     * Loaded [Chunk]s in this [Extent] region.
     *
     * Example:
     * If this [Extent] is a chunk, the returned list will contains
     * only current [Chunk]. If this [Extent] is a [World], the returned list will
     * contains all loaded chunks of this [World].
     */
    val loadedChunks: List<Chunk>

    /**
     * Gets a chunk in the [location]
     *
     * @param location Location
     * @return Chunk in the [location]
     */
    fun getChunk(location: Vector2i): Chunk = Chunk(this, location)

    /**
     * Gets all [Chunk]s inside the region.
     *
     * @param from From location
     * @param to To Location.
     * @return all [Chunk]s inside the region.
     */
    fun getChunks(from: Vector2i, to: Vector2i): Collection<Chunk> {

        val chunks = mutableListOf<Chunk>()

        val min = from.min(to)
        val max = from.max(to)

        for(vector in min..max) {
            chunks += this.getChunk(vector)
        }

        return chunks
    }

    /**
     * Get a [Location] in this [Extent].
     *
     * @param position Coordinate in this Extent
     * @return The [Location] in this [Extent]
     */
    fun getLocation(position: Vector3d): Location<Extent>

    /**
     * Get a [Location] in this [Extent].
     *
     * @param position Coordinate in this Extent
     * @return The [Location] in this [Extent]
     */
    fun getLocation(position: Vector3i): Location<Extent> = this.getLocation(position.toDouble())

    /**
     * Get a [Location] in this [Extent].
     *
     * @param x Coord X in this [Extent]
     * @param y Coord Y in this [Extent]
     * @param z Coord Z in this [Extent]
     * @return The [Location] in this [Extent]
     */
    fun getLocation(x: Double, y: Double, z: Double): Location<Extent> = this.getLocation(Vector3d(x, y, z))

    /**
     * Get a [Location] in this [Extent].
     *
     * @param x Coord X in this [Extent]
     * @param y Coord Y in this [Extent]
     * @param z Coord Z in this [Extent]
     * @return The [Location] in this [Extent]
     */
    fun getLocation(x: Int, y: Int, z: Int): Location<Extent> = this.getLocation(Vector3i(x, y, z))

    /**
     * Gets the [BlockState] in [location]
     *
     * @param location Location
     * @return BlockState in location
     */
    fun getBlock(location: Vector3i): BlockState

    /**
     * Gets all [BlockState]s inside the region.
     *
     * @param from From location.
     * @param to To location.
     * @return [BlockState]s inside the region.
     */
    fun getBlocks(from: Vector3i, to: Vector3i): Collection<BlockState> {
        val blocks = mutableListOf<BlockState>()

        val min = from.min(to)
        val max = from.max(to)

        for(vector in min..max) {
            blocks += this.getBlock(vector)
        }

        return blocks
    }

    /**
     * Sets the [BlockState] in [location].
     *
     * @param location Location
     * @param blockState Block State
     */
    fun setBlock(location: Vector3i, blockState: BlockState) = this.setBlock(location, blockState, null)

    /**
     * Sets the [BlockState] in [location].
     *
     * @param location Location
     * @param blockState Block State
     * @param source Source of the block change.
     */
    fun setBlock(location: Vector3i, blockState: BlockState, source: Source?)

    /**
     * Sets all blocks in selected location to [blockState].
     *
     * @param from From location
     * @param to To location.
     * @param blockState Block State
     */
    fun setBlocks(from: Vector3i, to: Vector3i, blockState: BlockState) = this.setBlocks(from, to, blockState, null)

    /**
     * Sets all blocks in selected location to [blockState].
     *
     * @param from From location
     * @param to To location.
     * @param blockState Block State
     * @param source Source of the block change.
     */
    fun setBlocks(from: Vector3i, to: Vector3i, blockState: BlockState, source: Source?) {

        val min = from.min(to)
        val max = from.max(to)

        for(vector in min..max) {
            this.setBlock(vector, blockState, source)
        }
    }

    /**
     * Sets all blocks in selected location to [BlockState] supplied by [stateSupplier]
     * for each [Vector3i].
     *
     * @param from From location
     * @param to To location.
     * @param stateSupplier [BlockState] supplier.
     */
    fun setBlocks(from: Vector3i, to: Vector3i, stateSupplier: (BlockState, Vector3i) -> BlockState) =
            this.setBlocks(from, to, null, stateSupplier)

    /**
     * Sets all blocks in selected location to [BlockState] supplied by [stateSupplier]
     * for each [Vector3i].
     *
     * @param from From location
     * @param to To location.
     * @param stateSupplier [BlockState] supplier.
     * @param source Source of the block change.
     */
    fun setBlocks(from: Vector3i, to: Vector3i, source: Source?, stateSupplier: (BlockState, Vector3i) -> BlockState) {
        val min = from.min(to)
        val max = from.max(to)

        for(vector in min..max) {
            this.setBlock(vector, stateSupplier(this.getBlock(vector), vector), source)
        }
    }

    /**
     * Selects a region from location [from] to location [to].
     *
     * @param from From location
     * @param to To location
     * @return Selection [from] to [to].
     */
    fun select(from: Vector3d, to: Vector3d): Selection = Selection(this, from, to)
}