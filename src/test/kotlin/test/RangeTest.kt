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

import com.github.projectsandstone.api.util.extension.sponge.math.rangeTo
import org.junit.Assert
import org.junit.Test
import org.spongepowered.math.vector.Vector2i

class RangeTest {
    @Test
    fun test() {
        val strs = mutableListOf<String>()
        val strs2 = mutableListOf<String>()

        val vec = Vector2i(2, 3)
        val vec2 = Vector2i(9, 7)

        for (i in vec..vec2) {
            println(i)
            strs += i.toString()
        }

        println("======================================")

        for (b in 3..7) {
            for (a in 2..9) {
                println("($a, $b)")

                strs2 += "($a, $b)"
            }
        }

        Assert.assertTrue(strs.containsAll(strs2))

        println("======================================")

        val vectA = Vector2i(4, 5)
        val vectB = Vector2i(1, 9)

        println("min ${vectA.min(vectB)}")
        println("max ${vectA.max(vectB)}")
    }
}
