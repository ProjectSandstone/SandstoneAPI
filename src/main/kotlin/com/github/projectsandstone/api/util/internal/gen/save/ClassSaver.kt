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
package com.github.projectsandstone.api.util.internal.gen.save

import com.github.projectsandstone.api.util.internal.gen.SandstoneClass
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

object ClassSaver {

    /**
     * Save a class
     */
    fun save(sandstonePath: Path, moduleName: String, sandstoneClass: SandstoneClass<*>) {

        val className: String = sandstoneClass.javaClass.canonicalName
        val classBytes: ByteArray = sandstoneClass.bytes
        val source: String = sandstoneClass.source.value

        val sourceBytes = source.toByteArray(Charset.forName("UTF-8"))

        val saveClass = "$moduleName/class/${className.replace('.', '/')}.class"
        val saveJava = "$moduleName/java/${className.replace('.', '/')}.java"

        val resolvedClass = sandstonePath.resolve(saveClass)
        val resolvedJava = sandstonePath.resolve(saveJava)

        try {
            Files.deleteIfExists(resolvedClass)
            Files.deleteIfExists(resolvedJava)
        } catch (ignored: Exception) {}

        Files.createDirectories(resolvedClass.parent)
        Files.createDirectories(resolvedJava.parent)

        Files.write(resolvedClass, classBytes, StandardOpenOption.CREATE)
        Files.write(resolvedJava, sourceBytes, StandardOpenOption.CREATE)
    }
}