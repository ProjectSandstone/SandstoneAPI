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
package test

import com.github.projectsandstone.api.event.SandstoneEventFactory
import com.github.projectsandstone.eventsys.event.Event
import com.github.projectsandstone.eventsys.gen.event.CommonEventGenerator
import com.github.projectsandstone.eventsys.impl.CommonLogger
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner
import org.junit.Test
import java.lang.StringBuilder
import java.lang.reflect.Modifier

/**
 * Test used to validate event factory generation.
 */
class FactoryTest {

    @Test
    fun testFactory() {
        CommonEventGenerator(CommonLogger()).createFactory(SandstoneEventFactory::class.java)

        val implemented = SandstoneEventFactory::class.java.declaredMethods
                .filter { !Modifier.isStatic(it.modifiers) }
                .map { it.returnType }
                .toSet()

        val missing = mutableListOf<Class<*>>()
        FastClasspathScanner("com.github.projectsandstone.api.event")
                .matchSubinterfacesOf(Event::class.java, {
                    if (!excludedEvents.contains(it) && !implemented.contains(it))
                        missing.add(it)
                }).scan()

        if (missing.isNotEmpty()) {
            println("Factory function missing for following events: ")
            missing.map { it.name() }.split(2).forEach {
                println(it.joinToString())
            }
            throw IllegalStateException()
        }

    }

    private fun Class<*>.name(): String {
        val builder = StringBuilder()
        var current = this

        while (true) {
            builder.insert(0, current.simpleName)

            if (current.declaringClass != null) {
                current = current.declaringClass; builder.insert(0, '.')
            } else break
        }

        return builder.toString()
    }

    private fun <T> List<T>.split(n: Int): List<List<T>> {
        val list = (0..this.size step n).map { this.subList(it, minOf(it + n, this.size)) }

        return list
    }

    companion object {
        val excludedEvents = setOf<Class<*>>()
    }
}