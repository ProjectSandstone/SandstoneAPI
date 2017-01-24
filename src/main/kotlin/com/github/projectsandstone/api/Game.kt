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
package com.github.projectsandstone.api

import com.github.projectsandstone.api.event.EventManager
import com.github.projectsandstone.api.plugin.PluginManager
import com.github.projectsandstone.api.registry.Registry
import com.github.projectsandstone.api.scheduler.Scheduler
import com.github.projectsandstone.api.service.ServiceManager
import com.github.projectsandstone.api.util.edition.GameEdition
import java.nio.file.Path

/**
 * Created by jonathan on 12/08/16.
 */
interface Game {

    /**
     * Implementing platform
     */
    val platform: Platform

    /**
     * Game Server
     */
    val server: Server

    /**
     * *Sandstone* plugin manager.
     */
    val pluginManager: PluginManager

    /**
     * *Sandstone* service manager.
     */
    val serviceManager: ServiceManager

    /**
     * *Sandstone* event manager.
     */
    val eventManager: EventManager

    /**
     * *Sandstone* Scheduler
     */
    val scheduler: Scheduler

    /**
     * Root Game Path
     */
    val gamePath: Path

    /**
     * Game save path
     */
    val savePath: Path

    /**
     * Game registry
     */
    val registry: Registry

    /**
     * Game edition
     */
    val edition: GameEdition
}