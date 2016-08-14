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
package com.github.projectsandstone.api.service

/**
 * Manage all [RegisteredProvider]s, services must be registered only during the initialization.
 *
 * You can get a service using method [provide] or [watch]
 */
interface ServiceManager {

    /**
     * Set provider of [service], is highly recommended to register services during initialization.
     *
     * @param plugin Plugin that provide this service
     * @param service Service class
     * @param instance Service instance (aka service implementation)
     */
    fun <T : Any> setProvider(plugin: Any, service: Class<T>, instance: T)

    /**
     * Gets provider for service class [service].
     *
     * @param service Service class
     * @return provider for service class [service], if not found, return null.
     */
    fun <T : Any> provide(service: Class<T>): T?

    /**
     * Watch all provider registration.
     *
     * @param function Watcher function, take an [RegisteredProvider] and return [Boolean],
     *                  if returns false, the registration watcher listener will be unregistered.
     */
    fun <T : Any> watch(function: (RegisteredProvider<T>) -> Boolean)

    /**
     * Watch all provider registration that matches the [predicate].
     *
     * @param function Watcher function, take an [RegisteredProvider] and return [Boolean],
     *                  if returns false, the registration watcher listener will be unregistered.
     */
    fun <T : Any> watch(predicate: (Any, Class<T>, T) -> Boolean, function: (RegisteredProvider<T>) -> Boolean)

    /**
     * Gets the [RegisteredProvider] of the [service].
     *
     * @param service Service class of provider.
     * @return [RegisteredProvider], or null if service is not registered
     */
    fun <T : Any> getRegisteredProvider(service: Class<T>): RegisteredProvider<T>?
}