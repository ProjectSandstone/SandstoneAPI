/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2017 Sandstone <https://github.com/ProjectSandstone/>
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
package com.github.projectsandstone.api.util.internal

import com.github.jonathanxd.iutils.description.Description
import com.github.jonathanxd.iutils.description.DescriptionUtil
import com.github.projectsandstone.api.event.annotation.Name
import com.github.projectsandstone.api.event.property.Property
import java.lang.reflect.Method
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.jvmErasure

val propertyHolderSignatures: List<Description> =
        Property::class.java.methods.map {
            DescriptionUtil.from(it)
        }

val KFunction<*>.parameterNames: List<String>
    get() = this.valueParameters.map {
        it.findAnnotation<Name>()?.value ?: it.name ?: throw IllegalStateException("Cannot determine name of parameter $it")
    }

fun getImplementation(klass: KClass<*>, kfunc: KFunction<*>): Pair<Class<*>, Method>? {
    val paramTypes = kfunc.parameters.map { it.type.jvmErasure.java }

    klass.java.classes.filter { it.simpleName == "DefaultImpls" }.forEach { type ->
        val found = type.methods.find {
            it.name == kfunc.name
                    && it.parameters.map { it.type } == paramTypes
                    && it.returnType == kfunc.returnType.jvmErasure.java
        }

        found?.let {
            return Pair(type, it)
        }
    }

    return null
}