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
package com.github.projectsandstone.api.text.style

/**
 * Format of Text
 *
 * @param bold Is bold
 * @param italic Is italic
 * @param underline Has underlined
 * @param strikeThrough Has strikeThrough
 * @param obfuscated Is obfuscated
 */
data class TextFormat @JvmOverloads constructor(val bold: Boolean = false,
                                                val italic: Boolean = false,
                                                val underline: Boolean = false,
                                                val strikeThrough: Boolean = false,
                                                val obfuscated: Boolean = false) {

    /**
     * Mix this [TextFormat] with another [TextFormat]
     *
     * @param textFormat Another [TextFormat] to mix
     * @return Mixed [TextFormat]
     */
    infix fun and(textFormat: TextFormat) = this.plus(textFormat)

    /**
     * Mix this [TextFormat] with another [TextFormat]
     *
     * @param textFormat Another [TextFormat] to mix
     * @return Mixed [TextFormat]
     */
    operator fun plus(textFormat: TextFormat): TextFormat {
        return this.copy(this.bold or textFormat.bold,
                this.italic or textFormat.italic,
                this.underline or textFormat.underline,
                this.strikeThrough or textFormat.strikeThrough,
                this.obfuscated or textFormat.obfuscated)
    }

}