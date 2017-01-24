/**
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
package com.github.projectsandstone.api.service

/**
 * Manage all [RegisteredProvider]s, services must be registered only during the post-initialization.
 *
 * You can get a service using method [provide] or [watch]
 */
interface ServiceManager {

    /**
     * Set provider of [service], is highly recommended to register services during post-initialization.
     *
     * This provider will be defined in *Sandstone Service Manager* and *Platform Service Manager*.
     *
     * @param plugin Plugin that provide this service
     * @param service Service class
     * @param instance Service instance (aka service implementation)
     */
    fun <T : Any> setProvider(plugin: Any, service: Class<T>, instance: T)

    /**
     * Set provider of [service], is highly recommended to register services during post-initialization.
     *
     * This provider will be defined in *Sandstone Service Manager* and *Platform Service Manager*.
     *
     * @param plugin Plugin that provide this service
     * @param service Service class
     * @param instance Service instance (aka service implementation)
     */
    operator fun <T : Any> set(plugin: Any, service: Class<T>, instance: T) =
            this.setProvider(plugin, service, instance)

    /**
     * Gets provider for service class [service].
     *
     * This method lookup for provider in *Sandstone Service Manager* and in *Platform Service manager*
     *
     * @param service Service class
     * @return provider for service class [service], if not found, return null.
     */
    fun <T : Any> provide(service: Class<T>): T?

    /**
     * Gets provider for service class [service].
     *
     * This method lookup for provider in *Sandstone Service Manager* and in *Platform Service manager*
     *
     * @param service Service class
     * @return provider for service class [service], if not found, return null.
     */
    operator fun <T : Any> get(service: Class<T>): T? = provide(service)

    /**
     * Provide the service lazily.
     *
     * This method lookup for provider in *Sandstone Service Manager* and in *Platform Service manager*
     *
     * @param service Service class
     * @return Lazy provider of [service]
     */
    fun <T : Any> provideLazy(service: Class<T>): Lazy<T?> = lazy {
        this.provide(service)
    }

    /**
     * Provide the service proxied.
     *
     * This method lookup for provider in *Sandstone Service Manager* and in *Platform Service manager*
     *
     * The proxy provider will always call [provide] in all operations of service,
     * but it has a few set of limitations:
     *
     * - Fields is not proxied.
     *
     * - Only proxy accessible members
     *
     * - Only proxy classes with public constructors.
     *
     * @param service Service class
     * @return Proxy provider of service.
     */
    fun <T : Any> provideProxy(service: Class<T>): T

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
     * This method get only providers of *Sandstone Service Manager*.
     *
     * @param service Service class of provider.
     * @return [RegisteredProvider], or null if service is not registered
     */
    fun <T : Any> getRegisteredProvider(service: Class<T>): RegisteredProvider<T>?
}