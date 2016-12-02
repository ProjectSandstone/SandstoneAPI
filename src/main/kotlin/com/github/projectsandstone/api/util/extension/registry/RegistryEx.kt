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
package com.github.projectsandstone.api.util.extension.registry

import com.github.jonathanxd.codeproxy.CodeProxy
import com.github.jonathanxd.codeproxy.handler.InvocationHandler
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.registry.Registry
import com.github.projectsandstone.api.registry.RegistryEntry
import com.github.projectsandstone.api.util.exception.EntryNotFoundException
import com.github.projectsandstone.api.util.exception.EntryNotInitializedException

private val UNINITIALIZED_HANDLER: InvocationHandler =
        InvocationHandler { any, method, arrayOfAnys, proxyData ->
            throw EntryNotInitializedException("Method invoked on a uninitialized entry!")
        }

inline fun <reified T : Any> uninitializedEntry(): T = uninitializedEntry(T::class.java)

/**
 * Create a uninitialized [RegistryEntry], all methods invoke on this instance will throw [EntryNotInitializedException].
 */
@Suppress("UNCHECKED_CAST")
fun <T : Any> uninitializedEntry(`class`: Class<out T>): T {

    if (`class`.isInterface) {
        return CodeProxy.newProxyInstance(`class`.classLoader, Any::class.java, arrayOf(`class`), UNINITIALIZED_HANDLER) as T
    } else {
        return CodeProxy.newProxyInstance(`class`.classLoader, `class`, UNINITIALIZED_HANDLER)
    }
}

/**
 * Gets a entry by id.
 *
 * @param id Function that returns the id of [RegistryEntry] to find.
 * @param T Entry type.
 * @return Entry
 * @throws EntryNotFoundException if the entry cannot be found.
 */
@Throws(EntryNotFoundException::class)
inline fun <reified T : RegistryEntry> getEntry(id: () -> String): T {
    val entry = Sandstone.game.registry.getRegistryEntry<T>(id())

    return entry ?: throw EntryNotFoundException("Required entry: ${id()} not found!")
}

/**
 * Gets registry entry by [id].
 *
 * @param id Id of registry entry.
 * @param T Type of registry entry.
 * @return Entry or null if cannot found.
 */
inline fun <reified T : RegistryEntry> Registry.getRegistryEntry(id: String): T? {
    return this.getEntry(id, T::class.java)
}

/**
 * Register entry [T] with [id].
 *
 * @param id Id of the entry
 * @param entry Entry instance.
 * @param T Type of entry.
 */
inline fun <reified T : RegistryEntry> Registry.registerEntry(id: String, entry: T) {
    return this.registerEntry(id, entry, T::class.java)
}

/**
 * Gets a entry by [id] and [type].
 *
 * @param id Entry id
 * @param type Entry type
 * @param T Expected entry type.
 */
@Suppress("UNCHECKED_CAST")
fun <T : RegistryEntry> Registry.getEntryGeneric(id: String, type: Class<*>): T? {
    return this.getEntry(id, type as Class<T>)
}