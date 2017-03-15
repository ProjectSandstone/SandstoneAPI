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

import com.flowpowered.math.vector.Vector3d
import com.flowpowered.math.vector.Vector3i
import com.github.projectsandstone.api.block.BlockState
import com.github.projectsandstone.api.util.extension.flow.math.minus
import com.github.projectsandstone.api.util.extension.flow.math.plus
import com.github.projectsandstone.api.world.extent.Extent
import java.lang.ref.WeakReference

/**
 * Immutable [Location] representation.
 *
 * A [Location] is a position in a [Extent].
 */
data class Location<out E : Extent>(val extent_: WeakReference<out E>, val position: Vector3d) {

    constructor(extent: E, position: Vector3d) : this(WeakReference(extent), position.toDouble())
    constructor(extent: E, position: Vector3i) : this(extent, position.toDouble())

    constructor(extent: E, x: Double, y: Double, z: Double) : this(extent, Vector3d(x, y, z))

    /**
     * Gets the extent
     */
    val extent: E
        get() = this.extent_.get() ?: throw IllegalStateException("Extent is not loaded.")

    /**
     * Gets the extent or null if the [extent] is not loaded
     */
    val extentOrNull: E?
        get() = this.extent_.get()

    /**
     * Returns true if [extent_] is loaded.
     */
    fun isLoaded(): Boolean = this.extent_.get() != null

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
        return Location(extent = extent, position = this.position)
    }

    /**
     * Add [x], [y] and [z] to this [Location.position].
     *
     * @param x Coord X to add.
     * @param y Coord Y to add.
     * @param z Coord Z to add.
     * @return New [Location] with new calculated values.
     */
    fun plus(x: Double, y: Double, z: Double): Location<E> {
        return this.copy(this.extent_, position = this.position.add(x, y, z))
    }

    /**
     * Add [position] to this [Location.position].
     *
     * @param position Position to increment
     * @return New [Location] with new calculated values.
     */
    operator fun plus(position: Vector3d): Location<E> {
        return this.copy(this.extent_, this.position + position)
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
        return this.copy(extent_ = this.extent_, position = this.position.sub(x, y, z))
    }

    /**
     * Add [position] from this [Location.position].
     *
     * @param position Position to increment
     * @return New [Location] with new calculated values.
     */
    operator fun minus(position: Vector3d): Location<E> {
        return this.copy(extent_ = this.extent_, position = this.position - position)
    }


    /**
     * Add [Location.position] of [location] to this [Location.position].
     *
     * @param location Location with coordinates to add.
     * @return New [Location] with new calculated values.
     */
    operator fun plus(location: Location<*>): Location<E> {
        return this.plus(location.position)
    }

    /**
     * Subtract [Location.position] of [location] from this [Location.position].
     *
     * @param location Location with coordinates to subtract.
     * @return New [Location] with new calculated values.
     */
    operator fun minus(location: Location<*>): Location<E> {
        return this.minus(location.position)
    }

    /**
     * Gets the block in current location.
     *
     * @return Return the block in current location.
     */
    fun getBlock(): BlockState = this.extent.getBlock(this.position.toInt())

    /**
     * Gets the world location.
     *
     * Calls [Extent.getWorldLocation].
     *
     * @return World location of this location.
     */
    fun getWorldLocation(): Location<World> = this.extent.getWorldLocation(this)
}