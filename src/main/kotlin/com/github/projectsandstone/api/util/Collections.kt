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
package com.github.projectsandstone.api.util

import com.github.jonathanxd.iutils.iterator.IteratorUtil


class ListCollection<E>(val collection: Collection<E>) : List<E> {

    override val size: Int
        get() = this.collection.size

    override fun contains(element: E): Boolean = this.collection.contains(element)

    override fun containsAll(elements: Collection<E>): Boolean = this.collection.containsAll(elements)

    override fun get(index: Int): E = TODO("not implemented")

    override fun indexOf(element: E): Int = TODO("not implemented")

    override fun isEmpty(): Boolean = this.collection.isEmpty()

    override fun iterator(): Iterator<E> = this.collection.iterator()

    override fun lastIndexOf(element: E): Int = TODO("not implemented")

    override fun listIterator(): ListIterator<E> = IteratorUtil.listIterator(this.iterator())

    override fun listIterator(index: Int): ListIterator<E> = IteratorUtil.listIterator(this.iterator()).also {
        var i = 0
        while (i < index) {
            it.next()
            ++i
        }
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<E> = TODO("not implemented")
}