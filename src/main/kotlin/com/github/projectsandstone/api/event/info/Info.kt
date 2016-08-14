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
package com.github.projectsandstone.api.event.info

import com.github.jonathanxd.iutils.`object`.Named
import com.github.jonathanxd.iutils.`object`.TypeInfo
import com.github.projectsandstone.api.event.MethodEventListener
import java.util.*

/**
 * Hold information about event, [Info] is used by [MethodEventListener] to apply arguments.
 *
 * For example, if event is PlayerDeathEvent, player and cause will be provided as info, more
 * complex operations like filtering will be possible.
 */
open class Info(parent: List<Info>) {

    /**
     * Mutable Map with all info
     */
    internal val map_: MutableMap<Named<TypeInfo<*>>, Any> = mutableMapOf()

    /**
     * Immutable Map with all info
     */
    val map: Map<Named<TypeInfo<*>>, Any> = Collections.unmodifiableMap(map_)

    /**
     * Parent info
     */
    val parentInfo: MutableList<Info> = parent.toMutableList()

    /**
     * Set info value
     *
     * @param type Type of info value
     * @param value Info value
     */
    fun <T : Any> set(type: TypeInfo<T>, value: T) {
        this.set(null, type, value)
    }

    /**
     * Set info value
     *
     * @param type Type of info value
     * @param value Info value
     */
    fun <T : Any> set(type: Class<T>, value: T) {
        this.set(TypeInfo.aEnd(type), value)
    }

    /**
     * Set named info value
     *
     * @param name Name of information
     * @param type Type of info value
     * @param value Info value
     */
    fun <T : Any> set(name: String?, type: TypeInfo<T>, value: T) {
        this.map_.put(Named(name, type), value)
    }

    /**
     * Set named info value
     *
     * @param name Name of information
     * @param type Type of info value
     * @param value Info Value
     */
    fun <T : Any> set(name: String?, type: Class<T>, value: T) {
        this.set(name, TypeInfo.aEnd(type), value)
    }

    /**
     * Get information value
     *
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(type: TypeInfo<T>): T? {
        return this.get(null, type)
    }

    /**
     * Get information value
     *
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(type: Class<T>): T? {
        return this.get(TypeInfo.aEnd(type))
    }

    /**
     * Get named info value
     *
     * @param name Name of information, if null, check information type without check name equality.
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(name: String?, type: TypeInfo<T>): T? {
        return this.map_[Named<TypeInfo<*>>(name, type)] as T?
    }

    /**
     * Get named info value
     *
     * @param name Name of information, if null, check information type without check name equality.
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(name: String?, type: Class<T>): T? {
        return this.get(name, TypeInfo.aEnd(type))
    }

    /**
     * Find first information with [type]
     *
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> first(type: TypeInfo<T>): T? {
        return this.find(type).getOrNull(0)
    }

    /**
     * Find first information with [type]
     *
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> first(type: Class<T>): T? {
        return this.first(TypeInfo.aEnd(type))
    }

    /**
     * Find first information with [name] and [type].
     *
     * @param name Name of information, if null, check information type without check name equality.
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> first(name: String?, type: TypeInfo<T>): T? {
        return this.find(name, type).getOrNull(0)
    }

    /**
     * Find first information with [name] and [type].
     *
     * @param name Name of information, if null, check information type without check name equality.
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> first(name: String?, type: Class<T>): T? {
        return this.first(name, TypeInfo.aEnd(type))
    }

    /**
     * Find first all information with [type].
     *
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> find(type: Class<T>): List<T> {
        return this.find(TypeInfo.aEnd(type))
    }

    /**
     * Find first all information with [type].
     *
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> find(type: TypeInfo<T>): List<T> {
        return this.find(null, type)
    }

    /**
     * Find all information with [name] and [type].
     *
     * @param name Name of information, if null, check information type without check name equality.
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> find(name: String?, type: Class<T>): List<T> {
        return this.find(name, TypeInfo.aEnd(type))
    }

    /**
     * Find all information with [name] and [type].
     *
     * @param name Name of information, if null, check information type without check name equality.
     * @param type Type of info value
     * @return info value if found, null otherwise.
     */
    fun <T : Any> find(name: String?, type: TypeInfo<T>): List<T> {

        val found = mutableListOf<T>()
        val value = this.get(name, type)

        if (value != null)
            found.add(value)

        for (info in parentInfo) {
            val find = info.find(name, type)

            found.addAll(find)
        }

        return found
    }

}