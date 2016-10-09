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
package com.github.projectsandstone.api.registry

/**
 * Registry manager.
 */
interface Registry {

    /**
     * Gets registry entry by [id].
     *
     * @param id Id of registry entry.
     * @return Entry or null if cannot found.
     */
    fun getEntry(id: String): RegistryEntry?

    /**
     * Gets registry entry by [id].
     *
     * @param id Id of registry entry.
     * @param type Type of registry entry. (bound is not limited to [RegistryEntry], but the [Registry] will not contains values that isn't [RegistryEntry])
     * @return Entry or null if cannot found.
     */
    fun <T : RegistryEntry> getEntry(id: String, type: Class<out T>): T?

    /**
     * Gets all elements of type [type]
     *
     * @param type Type of registry. (bound is not limited to [RegistryEntry], but the [Registry] will not contains values that isn't [RegistryEntry]).
     * @return a list of all types of [type].
     */
    fun <T : RegistryEntry> getAll(type: Class<out T>): List<T>
}