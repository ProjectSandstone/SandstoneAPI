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
package com.github.projectsandstone.api.util

import com.github.jonathanxd.codeapi.helper.Helper
import com.github.jonathanxd.codeapi.types.CodeType
import com.github.jonathanxd.codeapi.types.Generic
import com.github.jonathanxd.iutils.type.TypeInfo
import kotlin.jvm.internal.ClassBasedDeclarationContainer
import kotlin.reflect.KClass

/**
 * CodeAPI CodeType of current type
 */
val <T> Class<T>.codeType: CodeType
    get() = this.toType()

/**
 * CodeAPI CodeType of current type
 */
val <T : Any> KClass<T>.codeType: CodeType
    get() = this.toType()


/**
 * Convert current java class to CodeAPI type.
 */
fun <T> Class<T>.toType(): CodeType = Helper.getJavaType(this)

/**
 * Convert current kotlin class to CodeAPI type.
 */
fun <T : Any> KClass<T>.toType(): CodeType = (this as ClassBasedDeclarationContainer).jClass.toType()

/**
 * Convert type info to CodeAPI [Generic].
 */
fun <T> TypeInfo<T>.toGeneric(): Generic {
    val aClass = this.aClass
    val related = this.related

    var generic = Generic.type(aClass.toType())

    if (related.size > 0) {
        generic = generic.of(*related.map { it.toGeneric() }.toTypedArray())
    }

    return generic
}