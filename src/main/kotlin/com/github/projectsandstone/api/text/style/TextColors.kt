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
package com.github.projectsandstone.api.text.style

import com.github.projectsandstone.api.util.style.Color


/**
 * Constant text colors
 */
object TextColors {

    @JvmStatic
    val NORMAL = TextColor("reset", 255, 255, 255)

    @JvmStatic
    val LIGHT_GRAY = TextColor("light_gray", 192, 192, 192)

    @JvmStatic
    val DARK_GRAY = TextColor("dark_gray", 64, 64, 64)

    @JvmStatic
    val MAGENTA = TextColor("magenta", 255, 0, 255)

    @JvmStatic
    val ORANGE = TextColor("orange", 255, 200, 0)

    @JvmStatic
    val YELLOW = TextColor("yellow", 255, 255, 0)

    @JvmStatic
    val WHITE = TextColor("white", 255, 255, 255)

    @JvmStatic
    val RESET = TextColor("reset", 255, 255, 255)

    @JvmStatic
    val GRAY = TextColor("gray", 128, 128, 128)

    @JvmStatic
    val PINK = TextColor("pink", 255, 175, 175)

    @JvmStatic
    val CYAN = TextColor("cyan", 0, 255, 255)

    @JvmStatic
    val GREEN = TextColor("green", 0, 255, 0)

    @JvmStatic
    val BLUE = TextColor("blue", 0, 0, 255)

    @JvmStatic
    val BLACK = TextColor("black", 0, 0, 0)

    @JvmStatic
    val RED = TextColor("red", 255, 0, 0)


    private inline fun TextColor(name: String, red: Int, green: Int, blue: Int): TextColor {
        return TextColor(name, Color(red, green, blue))
    }
}

