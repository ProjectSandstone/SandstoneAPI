/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2017 Sandstone <https://github.com/ProjectSandstone/>
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
operator fun Vector2i.rangeTo(vector2i: Vector2i): Vector2iRange =
        Vector2iRange(this, vector2i)

operator fun Vector2l.rangeTo(vector2l: Vector2l): Vector2lRange =
        Vector2lRange(this, vector2l)

// Vector 3

operator fun Vector3i.rangeTo(vector3i: Vector3i): Vector3iRange =
        Vector3iRange(this, vector3i)

operator fun Vector3l.rangeTo(vector3l: Vector3l): Vector3lRange =
        Vector3lRange(this, vector3l)

// Vector 4

operator fun Vector4i.rangeTo(vector4i: Vector4i): Vector4iRange =
        Vector4iRange(this, vector4i)

operator fun Vector4l.rangeTo(vector4l: Vector4l): Vector4lRange =
        Vector4lRange(this, vector4l)

// Vector2

class Vector2iRange(override val start: Vector2i, override val endInclusive: Vector2i) : ClosedRange<Vector2i>, Iterable<Vector2i> {
    override fun iterator(): Iterator<Vector2i> {
        return Vector2iIterator(start, endInclusive)
    }

    override fun contains(value: Vector2i): Boolean {
        return super.contains(value)
    }

    override fun isEmpty(): Boolean {
        return super.isEmpty()
    }
}

class Vector2lRange(override val start: Vector2l, override val endInclusive: Vector2l) : ClosedRange<Vector2l>, Iterable<Vector2l> {
    override fun iterator(): Iterator<Vector2l> {
        return Vector2lIterator(start, endInclusive)
    }

    override fun contains(value: Vector2l): Boolean {
        return super.contains(value)
    }

    override fun isEmpty(): Boolean {
        return super.isEmpty()
    }
}

// Vector 3

class Vector3iRange(override val start: Vector3i, override val endInclusive: Vector3i) : ClosedRange<Vector3i>, Iterable<Vector3i> {
    override fun iterator(): Iterator<Vector3i> {
        return Vector3iIterator(start, endInclusive)
    }

    override fun contains(value: Vector3i): Boolean {
        return super.contains(value)
    }

    override fun isEmpty(): Boolean {
        return super.isEmpty()
    }
}

class Vector3lRange(override val start: Vector3l, override val endInclusive: Vector3l) : ClosedRange<Vector3l>, Iterable<Vector3l> {
    override fun iterator(): Iterator<Vector3l> {
        return Vector3lIterator(start, endInclusive)
    }

    override fun contains(value: Vector3l): Boolean {
        return super.contains(value)
    }

    override fun isEmpty(): Boolean {
        return super.isEmpty()
    }
}

// Vector 4

class Vector4iRange(override val start: Vector4i, override val endInclusive: Vector4i) : ClosedRange<Vector4i>, Iterable<Vector4i> {
    override fun iterator(): Iterator<Vector4i> {
        return Vector4iIterator(start, endInclusive)
    }

    override fun contains(value: Vector4i): Boolean {
        return super.contains(value)
    }

    override fun isEmpty(): Boolean {
        return super.isEmpty()
    }
}

class Vector4lRange(override val start: Vector4l, override val endInclusive: Vector4l) : ClosedRange<Vector4l>, Iterable<Vector4l> {
    override fun iterator(): Iterator<Vector4l> {
        return Vector4lIterator(start, endInclusive)
    }

    override fun contains(value: Vector4l): Boolean {
        return super.contains(value)
    }

    override fun isEmpty(): Boolean {
        return super.isEmpty()
    }
}
