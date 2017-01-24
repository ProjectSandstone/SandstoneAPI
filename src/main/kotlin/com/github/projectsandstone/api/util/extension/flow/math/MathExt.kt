/**
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
package com.github.projectsandstone.api.util.extension.flow.math

import com.flowpowered.math.vector.*

// Vector 2

fun Vector2d.min(vector2d: Vector2d): Vector2d {

    val x = this.x
    val y = this.y

    val otherX = vector2d.x
    val otherY = vector2d.y

    return Vector2d(Math.min(x, otherX), Math.min(y, otherY))
}

fun Vector2d.max(vector2d: Vector2d): Vector2d {

    val x = this.x
    val y = this.y

    val otherX = vector2d.x
    val otherY = vector2d.y

    return Vector2d(Math.max(x, otherX), Math.max(y, otherY))
}

fun Vector2f.min(vector2f: Vector2f): Vector2f {

    val x = this.x
    val y = this.y

    val otherX = vector2f.x
    val otherY = vector2f.y

    return Vector2f(Math.min(x, otherX), Math.min(y, otherY))
}

fun Vector2f.max(vector2f: Vector2f): Vector2f {

    val x = this.x
    val y = this.y

    val otherX = vector2f.x
    val otherY = vector2f.y

    return Vector2f(Math.max(x, otherX), Math.max(y, otherY))
}

fun Vector2i.min(vector2i: Vector2i): Vector2i {

    val x = this.x
    val y = this.y

    val otherX = vector2i.x
    val otherY = vector2i.y

    return Vector2i(Math.min(x, otherX), Math.min(y, otherY))
}

fun Vector2i.max(vector2i: Vector2i): Vector2i {

    val x = this.x
    val y = this.y

    val otherX = vector2i.x
    val otherY = vector2i.y

    return Vector2i(Math.max(x, otherX), Math.max(y, otherY))
}

fun Vector2l.min(vector2l: Vector2l): Vector2l {

    val x = this.x
    val y = this.y

    val otherX = vector2l.x
    val otherY = vector2l.y

    return Vector2l(Math.min(x, otherX), Math.min(y, otherY))
}

fun Vector2l.max(vector2l: Vector2l): Vector2l {

    val x = this.x
    val y = this.y

    val otherX = vector2l.x
    val otherY = vector2l.y

    return Vector2l(Math.max(x, otherX), Math.max(y, otherY))
}

// Vector 3

fun Vector3d.min(vector3d: Vector3d): Vector3d {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3d.x
    val otherY = vector3d.y
    val otherZ = vector3d.z

    return Vector3d(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ))
}

fun Vector3d.max(vector3d: Vector3d): Vector3d {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3d.x
    val otherY = vector3d.y
    val otherZ = vector3d.z

    return Vector3d(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ))
}

fun Vector3f.min(vector3f: Vector3f): Vector3f {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3f.x
    val otherY = vector3f.y
    val otherZ = vector3f.z

    return Vector3f(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ))
}

fun Vector3f.max(vector3f: Vector3f): Vector3f {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3f.x
    val otherY = vector3f.y
    val otherZ = vector3f.z

    return Vector3f(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ))
}

fun Vector3i.min(vector3i: Vector3i): Vector3i {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3i.x
    val otherY = vector3i.y
    val otherZ = vector3i.z

    return Vector3i(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ))
}

fun Vector3i.max(vector3i: Vector3i): Vector3i {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3i.x
    val otherY = vector3i.y
    val otherZ = vector3i.z

    return Vector3i(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ))
}

fun Vector3l.min(vector3l: Vector3l): Vector3l {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3l.x
    val otherY = vector3l.y
    val otherZ = vector3l.z

    return Vector3l(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ))
}

fun Vector3l.max(vector3l: Vector3l): Vector3l {

    val x = this.x
    val y = this.y
    val z = this.z

    val otherX = vector3l.x
    val otherY = vector3l.y
    val otherZ = vector3l.z

    return Vector3l(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ))
}


// Vector 4

fun Vector4d.min(vector4d: Vector4d): Vector4d {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4d.x
    val otherY = vector4d.y
    val otherZ = vector4d.z
    val otherW = vector4d.w

    return Vector4d(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ), Math.min(w, otherW))
}

fun Vector4d.max(vector4d: Vector4d): Vector4d {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4d.x
    val otherY = vector4d.y
    val otherZ = vector4d.z
    val otherW = vector4d.w

    return Vector4d(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ), Math.max(w, otherW))
}

fun Vector4f.min(vector4f: Vector4f): Vector4f {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4f.x
    val otherY = vector4f.y
    val otherZ = vector4f.z
    val otherW = vector4f.w

    return Vector4f(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ), Math.min(w, otherW))
}

fun Vector4f.max(vector4f: Vector4f): Vector4f {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4f.x
    val otherY = vector4f.y
    val otherZ = vector4f.z
    val otherW = vector4f.w

    return Vector4f(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ), Math.max(w, otherW))
}


fun Vector4i.min(vector4i: Vector4i): Vector4i {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4i.x
    val otherY = vector4i.y
    val otherZ = vector4i.z
    val otherW = vector4i.w

    return Vector4i(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ), Math.min(w, otherW))
}

fun Vector4i.max(vector4i: Vector4i): Vector4i {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4i.x
    val otherY = vector4i.y
    val otherZ = vector4i.z
    val otherW = vector4i.w

    return Vector4i(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ), Math.max(w, otherW))
}

fun Vector4l.min(vector4l: Vector4l): Vector4l {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4l.x
    val otherY = vector4l.y
    val otherZ = vector4l.z
    val otherW = vector4l.w

    return Vector4l(Math.min(x, otherX), Math.min(y, otherY), Math.min(z, otherZ), Math.min(w, otherW))
}

fun Vector4l.max(vector4l: Vector4l): Vector4l {

    val x = this.x
    val y = this.y
    val z = this.z
    val w = this.w

    val otherX = vector4l.x
    val otherY = vector4l.y
    val otherZ = vector4l.z
    val otherW = vector4l.w

    return Vector4l(Math.max(x, otherX), Math.max(y, otherY), Math.max(z, otherZ), Math.max(w, otherW))
}


// Vector N

fun VectorNd.min(vectorNd: VectorNd): VectorNd {

    check(this.size() == vectorNd.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.min(value, vectorNd[index])
    }.toDoubleArray()

    return VectorNd(*values)
}

fun VectorNd.max(vectorNd: VectorNd): VectorNd {

    check(this.size() == vectorNd.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.max(value, vectorNd[index])
    }.toDoubleArray()

    return VectorNd(*values)
}

fun VectorNf.min(vectorNf: VectorNf): VectorNf {

    check(this.size() == vectorNf.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.min(value, vectorNf[index])
    }.toFloatArray()

    return VectorNf(*values)
}

fun VectorNf.max(vectorNf: VectorNf): VectorNf {

    check(this.size() == vectorNf.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.max(value, vectorNf[index])
    }.toFloatArray()

    return VectorNf(*values)
}

fun VectorNi.min(vectorNi: VectorNi): VectorNi {

    check(this.size() == vectorNi.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.min(value, vectorNi[index])
    }.toIntArray()

    return VectorNi(*values)
}

fun VectorNi.max(vectorNi: VectorNi): VectorNi {

    check(this.size() == vectorNi.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.max(value, vectorNi[index])
    }.toIntArray()

    return VectorNi(*values)
}

fun VectorNl.min(vectorNl: VectorNl): VectorNl {

    check(this.size() == vectorNl.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.min(value, vectorNl[index])
    }.toLongArray()

    return VectorNl(*values)
}

fun VectorNl.max(vectorNl: VectorNl): VectorNl {

    check(this.size() == vectorNl.size(), { "Vectors must have same size." })

    val values = this.toArray().mapIndexed { index, value ->
        Math.max(value, vectorNl[index])
    }.toLongArray()

    return VectorNl(*values)
}