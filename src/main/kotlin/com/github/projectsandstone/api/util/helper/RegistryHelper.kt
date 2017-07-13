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
package com.github.projectsandstone.api.util.helper

import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.registry.RegistryEntry
import com.github.projectsandstone.api.util.exception.EntryNotInitializedException
import com.github.projectsandstone.api.util.extension.registry.getEntryGeneric
import java.lang.reflect.Modifier


internal const val defaultModule: String = "minecraft"

/**
 * Initialize registry entries in a class.
 */
fun initializeEntries(clasz: Class<*>) {
    checkNotInitEntries(clasz)

    clasz.declaredFields.forEach {
        if (Modifier.isPublic(it.modifiers)) {
            it.isAccessible = true
            val name = it.name.toLowerCase()
            val type = it.type
            val module = it.getDeclaredAnnotation(Module::class.java)?.value ?: defaultModule
            val id = "$module:$name"

            it.set(null, requireNotNull(Sandstone.getGame().registry.getEntryGeneric(id, type), {
                "Cannot find RegistryEntry! id: $id, module: $module, name: $name, type: $type, location: $clasz, field: $it!"
            }))
        }
    }
}

/**
 * Check if entries is initialized, will throw a [Exception] if isn't initialized.
 */
fun checkEntries(clasz: Class<*>) {
    clasz.declaredFields.forEach {
        if (Modifier.isPublic(it.modifiers)) {
            it.isAccessible = true
            val get = it.get(null) as RegistryEntry
            get.id // Will throw a exception if not initialized.
        }
    }
}

fun checkNotInitEntries(clasz: Class<*>) {
    clasz.declaredFields.forEach {
        if (Modifier.isPublic(it.modifiers)) {
            it.isAccessible = true
            val get = it.get(null) as RegistryEntry
            try {
                get.id
                throw IllegalStateException("Entry $it is already initialized! Make sure that you aren't trying to initialize it again.")
            } catch (ex: Exception) {
                if (ex !is EntryNotInitializedException)
                    throw IllegalStateException("Failed to check entries!", ex)
            }
        }
    }
}

/**
 * Game module, by default, the module is 'minecraft' with or without the annotation.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class Module(val value: String = defaultModule)