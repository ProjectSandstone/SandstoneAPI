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

import com.flowpowered.math.vector.Vector3d
import com.flowpowered.math.vector.Vector3i
import com.github.projectsandstone.api.world.extent.Extent

/**
 * Immutable [Location] representation.
 *
 * A [Location] is a position in a [Extent].
 */
data class Location<E : Extent>(val extent: E, val x: Double, val y: Double, val z: Double) {

    constructor(extent: E, position: Vector3i) : this(extent, position.toDouble())

    constructor(extent: E, position: Vector3d) : this(extent, position.x, position.y, position.z)

    /**
     * Change [Location.extent] and [Location.E] to a new [extent] and [V].
     *
     * **This method doesn't convert the position in extent and doesn't check for limits.**
     *
     * @param V new [Extent] type.
     * @param extent New [Extent].
     * @return new [Location] with a different [Extent].
     */
    fun <V : Extent> changeExtent(extent: V): Location<V> {
        return Location(extent = extent, x = this.x, y = this.y, z = this.z)
    }

    /**
     * Add [x], [y] and [z] to this [Location].
     *
     * @param x Coord X to add.
     * @param y Coord Y to add.
     * @param z Coord Z to add.
     * @return New [Location] with new calculated values.
     */
    fun plus(x: Double, y: Double, z: Double): Location<E> {
        return this.copy(this.extent, x = this.x + x, y = this.y + y, z = this.z + z)
    }

    /**
     * Remove [x], [y] and [z] to this [Location].
     *
     * @param x Coord X to add.
     * @param y Coord Y to add.
     * @param z Coord Z to add.
     * @return New [Location] with new calculated values.
     */
    fun minus(x: Double, y: Double, z: Double): Location<E> {
        return this.copy(extent = this.extent, x = this.x - x, y = this.y - y, z = this.z - z)
    }

    /**
     * Add [x], [y] and [z] of [location] to this [Location].
     *
     * @param location Location with coordinates to add.
     * @return New [Location] with new calculated values.
     */
    operator fun plus(location: Location<*>): Location<E> {
        return this.plus(location.x, location.y, location.z)
    }

    /**
     * Remove [x], [y] and [z] of [location] to this [Location].
     *
     * @param location Location with coordinates to remove.
     * @return New [Location] with new calculated values.
     */
    operator fun minus(location: Location<*>): Location<E> {
        return this.minus(location.x, location.y, location.z)
    }
}