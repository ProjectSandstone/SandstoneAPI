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
package com.github.projectsandstone.api.command

/**
 * This support escape character `\` and will not split strings inside double quotes (`"`) or single quotes (`'`).
 */
fun String.toCommandStringList(): List<String> {
    val chars = this.toCharArray()
    val builder = StringBuilder()
    val commands = mutableListOf<String>()
    var lastIsEscape = false
    var quotation = false
    var singleQuotation = false

    for (char in chars) {
        if(lastIsEscape) {
            builder.append(char)
        } else if(char == '\\') { // \ is the escape char
            lastIsEscape = true
        } else if(char == '"' && !singleQuotation) {
            quotation = !quotation
        } else if(char == '\'' && !quotation) {
            singleQuotation = !singleQuotation
        } else if(char.isWhitespace() && !quotation && !singleQuotation) {
            commands += builder.toString()
            builder.setLength(0)
        } else {
            builder.append(char)
        }
    }

    if(builder.isNotEmpty())
        commands += builder.toString(); builder.setLength(0)

    return commands
}