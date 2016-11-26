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
package com.github.projectsandstone.api.util.extension.flow.math

import com.flowpowered.math.vector.*

// Vector 2

internal class Vector2iIterator(val start: Vector2i, endInclusive: Vector2i) : Iterator<Vector2i> {

    private var x: Int = start.x - 1
    private var y: Int = start.y

    private val endX: Int = endInclusive.x
    private val endY: Int = endInclusive.y

    override fun hasNext() = hasNextX() || hasNextY()

    fun hasNextX() = x + 1 <= endX
    fun hasNextY() = y + 1 <= endY

    override fun next(): Vector2i {

        if (this.hasNextX()) {
            ++x
        } else if (this.hasNextY()) {
            x = start.x
            ++y
        } else {
            throw NoSuchElementException()
        }

        return Vector2i(x, y)
    }

}

internal class Vector2lIterator(val start: Vector2l, endInclusive: Vector2l) : Iterator<Vector2l> {

    private var x: Long = start.x - 1
    private var y: Long = start.y

    private val endX: Long = endInclusive.x
    private val endY: Long = endInclusive.y

    override fun hasNext() = hasNextX() || hasNextY()

    fun hasNextX() = x + 1 <= endX
    fun hasNextY() = y + 1 <= endY

    override fun next(): Vector2l {

        if (hasNextX()) {
            ++x
        } else if (hasNextY()) {
            x = start.x
            ++y
        } else {
            throw NoSuchElementException()
        }

        return Vector2l(x, y)
    }

}

// Vector 3

internal class Vector3iIterator(val start: Vector3i, endInclusive: Vector3i) : Iterator<Vector3i> {

    private var x: Int = start.x - 1
    private var y: Int = start.y
    private var z: Int = start.z

    private val endX: Int = endInclusive.x
    private val endY: Int = endInclusive.y
    private val endZ: Int = endInclusive.z

    override fun hasNext() = hasNextX() || hasNextY() || hasNextZ()

    fun hasNextX() = x + 1 <= endX
    fun hasNextY() = y + 1 <= endY
    fun hasNextZ() = z + 1 <= endZ

    override fun next(): Vector3i {

        if (hasNextX()) {
            ++x
        } else if (hasNextY()) {
            x = start.x
            ++y
        } else if (hasNextZ()) {
            x = start.x
            y = start.y
            ++z
        } else {
            throw NoSuchElementException()
        }

        return Vector3i(x, y, z)
    }

}

internal class Vector3lIterator(val start: Vector3l, endInclusive: Vector3l) : Iterator<Vector3l> {

    private var x: Long = start.x - 1
    private var y: Long = start.y
    private var z: Long = start.z

    private val endX: Long = endInclusive.x
    private val endY: Long = endInclusive.y
    private val endZ: Long = endInclusive.z

    override fun hasNext() = hasNextX() || hasNextY() || hasNextZ()

    fun hasNextX() = x + 1 <= endX
    fun hasNextY() = y + 1 <= endY
    fun hasNextZ() = z + 1 <= endZ

    override fun next(): Vector3l {

        if (hasNextX()) {
            ++x
        } else if (hasNextY()) {
            x = start.x
            ++y
        } else if (hasNextZ()) {
            x = start.x
            y = start.y
            ++z
        } else {
            throw NoSuchElementException()
        }

        return Vector3l(x, y, z)
    }

}

// Vector 4

internal class Vector4iIterator(val start: Vector4i, endInclusive: Vector4i) : Iterator<Vector4i> {

    private var x: Int = start.x - 1
    private var y: Int = start.y
    private var z: Int = start.z
    private var w: Int = start.w

    private val endX: Int = endInclusive.x
    private val endY: Int = endInclusive.y
    private val endZ: Int = endInclusive.z
    private val endW: Int = endInclusive.w

    override fun hasNext() = hasNextX() || hasNextY() || hasNextZ() || hasNextW()

    fun hasNextX() = x + 1 <= endX
    fun hasNextY() = y + 1 <= endY
    fun hasNextZ() = z + 1 <= endZ
    fun hasNextW() = w + 1 <= endW

    override fun next(): Vector4i {

        if (hasNextX()) {
            ++x
        } else if (hasNextY()) {
            x = start.x
            ++y
        } else if (hasNextZ()) {
            x = start.x
            y = start.y
            ++z
        } else if (hasNextW()) {
            x = start.x
            y = start.y
            z = start.z
            ++w
        } else {
            throw NoSuchElementException()
        }

        return Vector4i(x, y, z, w)
    }

}

internal class Vector4lIterator(val start: Vector4l, endInclusive: Vector4l) : Iterator<Vector4l> {

    private var x: Long = start.x - 1
    private var y: Long = start.y
    private var z: Long = start.z
    private var w: Long = start.w

    private val endX: Long = endInclusive.x
    private val endY: Long = endInclusive.y
    private val endZ: Long = endInclusive.z
    private val endW: Long = endInclusive.w

    override fun hasNext() = hasNextX() || hasNextY() || hasNextZ() || hasNextW()

    fun hasNextX() = x + 1 <= endX
    fun hasNextY() = y + 1 <= endY
    fun hasNextZ() = z + 1 <= endZ
    fun hasNextW() = w + 1 <= endW

    override fun next(): Vector4l {

        if (hasNextX()) {
            ++x
        } else if (hasNextY()) {
            x = start.x
            ++y
        } else if (hasNextZ()) {
            x = start.x
            y = start.y
            ++z
        } else if (hasNextW()) {
            x = start.x
            y = start.y
            z = start.z
            ++w
        } else {
            throw NoSuchElementException()
        }

        return Vector4l(x, y, z, w)
    }

}