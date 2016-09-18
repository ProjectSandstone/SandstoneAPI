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
package com.github.projectsandstone.api.text

import com.github.projectsandstone.api.text.style.TextColor
import com.github.projectsandstone.api.text.style.TextColors
import com.github.projectsandstone.api.text.style.TextFormat
import com.github.projectsandstone.api.text.style.TextFormats
import java.util.*

/**
 * Immutable Text representation
 */
data class Text @JvmOverloads constructor(val color: TextColor = TextColors.RESET,
                                          val format: TextFormat = TextFormats.NORMAL,
                                          val content: String,
                                          val parent: Array<Text>?) {

    /**
     * Add a Text to [parent] text array
     *
     * @param text Text to add
     * @return New Text with [text] in [parent]
     */
    operator fun plus(text: Text): Text {
        return this.copy(parent = if (this.parent == null) arrayOf(text) else this.parent + text)
    }

    /**
     * Remove a [Text] from [parent] text array
     *
     * @param text Text to remove
     * @return This if [text] is not in [parent] array, or a New [Text] without [text] in [parent]
     */
    operator fun minus(text: Text): Text {
        if (this.parent == null)
            return this

        return this.copy(parent = this.parent.filter { it != text }.toTypedArray())
    }

    override fun equals(other: Any?): Boolean {
        if (other is Text) {
            return this.color.equals(other.color)
                    && this.format.equals(other.format)
                    && this.content == other.content
                    && (this.parent == other.parent || Arrays.deepEquals(this.parent, other.parent))

        }

        return super.equals(other)
    }

    override fun hashCode(): Int {

        var result = 1

        result = 31 * result + this.color.hashCode()
        result = 31 * result + this.format.hashCode()
        result = 31 * result + this.content.hashCode()

        if (this.parent != null) {
            result = 31 * result + Arrays.deepHashCode(parent)
        }

        return result
    }
}