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
package com.github.projectsandstone.api.text.style

import com.github.projectsandstone.api.util.style.Color


/**
 * Constant text colors.
 */
object TextColors {

    @JvmField
    val NORMAL = TextColor("reset", 255, 255, 255)

    @JvmField
    val BLACK = TextColor("black", 0, 0, 0)

    @JvmField
    val DARK_BLUE = TextColor("dark_blue", 0, 0, 170)

    @JvmField
    val DARK_GREEN = TextColor("dark_green", 0, 170, 0)

    @JvmField
    val DARK_AQUA = TextColor("dark_aqua", 0, 170, 170)

    @JvmField
    val DARK_RED = TextColor("dark_red", 170, 0, 0)

    @JvmField
    val DARK_PURPLE = TextColor("dark_purple", 170, 0, 170)

    @JvmField
    val GOLD = TextColor("gold", 255, 170, 0)

    @JvmField
    val GRAY = TextColor("gray", 170, 170, 170)

    @JvmField
    val DARK_GRAY = TextColor("dark_gray", 85, 85, 85)

    @JvmField
    val BLUE = TextColor("blue", 85, 85, 255)

    @JvmField
    val GREEN = TextColor("green", 85, 255, 85)

    @JvmField
    val AQUA = TextColor("aqua", 85, 255, 255)

    @JvmField
    val RED = TextColor("red", 255, 85, 85)

    @JvmField
    val LIGHT_PURPLE = TextColor("light_purple", 255, 85, 255)

    @JvmField
    val YELLOW = TextColor("yellow", 255, 255, 85)

    @JvmField
    val WHITE = TextColor("white", 255, 255, 255)

    @Suppress("NOTHING_TO_INLINE")
    private inline fun TextColor(name: String, red: Int, green: Int, blue: Int): TextColor {
        return TextColor(name, Color(red, green, blue))
    }
}

