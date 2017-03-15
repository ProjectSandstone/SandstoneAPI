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
package com.github.projectsandstone.api.platform

import com.github.jonathanxd.adapterhelper.AdapterBase

/**
 * Converts a object of Sandstone to platform object.
 *
 * This is a interface to *AdapterHelper* API.
 */
interface PlatformObjectConverter {

    /**
     * Converts [sandstoneObject] to a platform dependent version of this object.
     *
     * @return Platform version of this object, or null if this object cannot be converted.
     */
    fun <T> convertObject(sandstoneObject: T): Any? where T : Any, T : AdapterBase<*>

    /**
     * Converts [sandstoneObject] to a platform dependent version of this object.
     *
     * @param expectedType Platform type that are expected to return.
     * @return Platform version of this object, or null if this object cannot be converted.
     */
    fun <T, U : Any> convertObject(sandstoneObject: T, expectedType: Class<U>): U? where T : Any, T : AdapterBase<*>

    /**
     * Returns true if [objectType] can be converted to a platform type.
     */
    fun isConvertible(objectType: Class<*>): Boolean

    /**
     * Returns true if [objectType] can be converted to [platformType].
     */
    fun isConvertible(objectType: Class<*>, platformType: Class<*>): Boolean
}