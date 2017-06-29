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
package com.github.projectsandstone.api.util.extension.flow.math

import com.flowpowered.math.vector.*

// Vector N

operator fun VectorNd.plus(vector: VectorNd): VectorNd = this.add(vector)
operator fun VectorNd.minus(vector: VectorNd): VectorNd = this.sub(vector)
operator fun VectorNd.times(vector: VectorNd): VectorNd = this.mul(vector)

operator fun VectorNi.plus(vector: VectorNi): VectorNi = this.add(vector)
operator fun VectorNi.minus(vector: VectorNi): VectorNi = this.sub(vector)
operator fun VectorNi.times(vector: VectorNi): VectorNi = this.mul(vector)

operator fun VectorNf.plus(vector: VectorNf): VectorNf = this.add(vector)
operator fun VectorNf.minus(vector: VectorNf): VectorNf = this.sub(vector)
operator fun VectorNf.times(vector: VectorNf): VectorNf = this.mul(vector)

operator fun VectorNl.plus(vector: VectorNl): VectorNl = this.add(vector)
operator fun VectorNl.minus(vector: VectorNl): VectorNl = this.sub(vector)
operator fun VectorNl.times(vector: VectorNl): VectorNl = this.mul(vector)

// Vector 4

operator fun Vector4d.plus(vector: Vector4d): Vector4d = this.add(vector)
operator fun Vector4d.minus(vector: Vector4d): Vector4d = this.sub(vector)
operator fun Vector4d.times(vector: Vector4d): Vector4d = this.mul(vector)

operator fun Vector4i.plus(vector: Vector4i): Vector4i = this.add(vector)
operator fun Vector4i.minus(vector: Vector4i): Vector4i = this.sub(vector)
operator fun Vector4i.times(vector: Vector4i): Vector4i = this.mul(vector)

operator fun Vector4f.plus(vector: Vector4f): Vector4f = this.add(vector)
operator fun Vector4f.minus(vector: Vector4f): Vector4f = this.sub(vector)
operator fun Vector4f.times(vector: Vector4f): Vector4f = this.mul(vector)

operator fun Vector4l.plus(vector: Vector4l): Vector4l = this.add(vector)
operator fun Vector4l.minus(vector: Vector4l): Vector4l = this.sub(vector)
operator fun Vector4l.times(vector: Vector4l): Vector4l = this.mul(vector)

// Vector 3

operator fun Vector3d.plus(vector: Vector3d): Vector3d = this.add(vector)
operator fun Vector3d.minus(vector: Vector3d): Vector3d = this.sub(vector)
operator fun Vector3d.times(vector: Vector3d): Vector3d = this.mul(vector)

operator fun Vector3i.plus(vector: Vector3i): Vector3i = this.add(vector)
operator fun Vector3i.minus(vector: Vector3i): Vector3i = this.sub(vector)
operator fun Vector3i.times(vector: Vector3i): Vector3i = this.mul(vector)

operator fun Vector3f.plus(vector: Vector3f): Vector3f = this.add(vector)
operator fun Vector3f.minus(vector: Vector3f): Vector3f = this.sub(vector)
operator fun Vector3f.times(vector: Vector3f): Vector3f = this.mul(vector)

operator fun Vector3l.plus(vector: Vector3l): Vector3l = this.add(vector)
operator fun Vector3l.minus(vector: Vector3l): Vector3l = this.sub(vector)
operator fun Vector3l.times(vector: Vector3l): Vector3l = this.mul(vector)

// Vector 2

operator fun Vector2d.plus(vector: Vector2d): Vector2d = this.add(vector)
operator fun Vector2d.minus(vector: Vector2d): Vector2d = this.sub(vector)
operator fun Vector2d.times(vector: Vector2d): Vector2d = this.mul(vector)

operator fun Vector2i.plus(vector: Vector2i): Vector2i = this.add(vector)
operator fun Vector2i.minus(vector: Vector2i): Vector2i = this.sub(vector)
operator fun Vector2i.times(vector: Vector2i): Vector2i = this.mul(vector)

operator fun Vector2f.plus(vector: Vector2f): Vector2f = this.add(vector)
operator fun Vector2f.minus(vector: Vector2f): Vector2f = this.sub(vector)
operator fun Vector2f.times(vector: Vector2f): Vector2f = this.mul(vector)

operator fun Vector2l.plus(vector: Vector2l): Vector2l = this.add(vector)
operator fun Vector2l.minus(vector: Vector2l): Vector2l = this.sub(vector)
operator fun Vector2l.times(vector: Vector2l): Vector2l = this.mul(vector)

