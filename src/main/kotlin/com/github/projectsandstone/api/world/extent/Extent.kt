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

import com.flowpowered.math.vector.Vector3d
import com.flowpowered.math.vector.Vector3i
import com.github.projectsandstone.api.text.channel.MessageReceiver
import com.github.projectsandstone.api.world.Chunk
import com.github.projectsandstone.api.world.Location
import com.github.projectsandstone.api.world.Selection
import com.github.projectsandstone.api.world.World

/**
 * An [Extent] is a container of game objects (ex: Block, Entities, Tile Entities).
 *
 * Example of [Extent]s: [World], [Chunk] or [Selection].
 */
interface Extent : EntityUniverse, MessageReceiver {

    /**
     * Loaded [Chunk]s in this [Extent] region.
     *
     * Example:
     * If this [Extent] is a chunk, the returned list will contains
     * only current [Chunk]. If this [Extent] is a [World], the returned list will
     * contains all loaded chunks of this [World]. If is a [Selection], returned
     * list will contains all loaded [Chunk]s of this [Selection].
     */
    val loadedChunks: List<Chunk>

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
     * Create a [Selection] from position [from] to position [to].
     *
     * @param from [Selection] from position
     * @param to [Selection] to position
     * @return Selected positions
     */
    fun select(from: Vector3d, to: Vector3d): Selection
}