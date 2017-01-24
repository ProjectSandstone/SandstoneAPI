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
package com.github.projectsandstone.api.util.extension.internal.gen

import com.github.jonathanxd.iutils.type.ConcreteTypeInfo
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.util.internal.gen.event.SandstoneEventGen

inline fun <reified T : Any> SandstoneEventGen.create(typeInfo: TypeInfo<T>, properties: Map<String, Any?>) = this.gen(typeInfo, properties)

inline fun <reified T : Any> SandstoneEventGen.create(typeInfo: TypeInfo<T>) = this.gen(typeInfo, mutableMapOf<String, Any?>())

inline fun <reified T : Any> SandstoneEventGen.create(properties: Map<String, Any?>): T = this.gen(object : ConcreteTypeInfo<T>() {}, properties)

inline fun <reified T : Any> SandstoneEventGen.create(): T = this.gen(object : ConcreteTypeInfo<T>() {}, mutableMapOf<String, Any?>())

