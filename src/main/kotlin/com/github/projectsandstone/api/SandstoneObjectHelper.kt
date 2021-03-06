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
package com.github.projectsandstone.api

import com.github.jonathanxd.kwcommands.printer.Printer
import com.github.projectsandstone.api.text.channel.MessageReceiver
import org.slf4j.Logger

/**
 * Misc utilities and object helper.
 */
interface SandstoneObjectHelper {

    /**
     * Creates a list which is a view of mapped elements of [from]. Changes in this list throws exceptions,
     * but changes in [from] is reflected in this list.
     */
    fun <U, T> createLiveList(from: List<U>, mapper: (U) -> T): List<T>

    /**
     * Creates a set which is a view of mapped elements of [from]. Changes in this set throws exceptions,
     * but changes in [from] is reflected in this set.
     */
    fun <U, T> createLiveSet(from: Set<U>, mapper: (U) -> T): Set<T>

    /**
     * Creates a collection which is a view of mapped elements of [from]. Changes in this collection throws exceptions,
     * but changes in [from] is reflected in this collection.
     */
    fun <U, T> createLiveCollection(from: Collection<U>, mapper: (U) -> T): Collection<T>

    /**
     * Creates or returns (if cached) a [printer][Printer] backed to [receiver].
     */
    fun createPrinter(receiver: MessageReceiver): Printer

    /**
     * Creates or returns (if cached) a [printer][Printer] backed to [logger].
     */
    fun createPrinter(logger: Logger): Printer
}