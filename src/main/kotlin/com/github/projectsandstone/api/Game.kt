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
package com.github.projectsandstone.api

import com.github.koresframework.eventsys.event.EventListenerRegistry
import com.github.koresframework.eventsys.event.EventManager
import com.github.koresframework.eventsys.gen.event.EventGenerator
import com.github.projectsandstone.api.command.CommandManager
import com.github.projectsandstone.api.plugin.PluginManager
import com.github.projectsandstone.api.registry.Registry
import com.github.projectsandstone.api.scheduler.Scheduler
import com.github.projectsandstone.api.service.ServiceManager
import com.github.projectsandstone.api.util.edition.GameEdition
import java.nio.file.Path

/**
 * Represents a [Game] instance.
 */
interface Game {

    /**
     * Platform where Sandstone is running
     */
    val platform: Platform

    /**
     * Sandstone object factory.
     */
    val objectFactory: SandstoneObjectFactory

    /**
     * Sandstone object helper.
     */
    val objectHelper: SandstoneObjectHelper

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
     * *Sandstone* event class generator.
     */
    val eventGenerator: EventGenerator

    /**
     * *Sandstone* event listener registry.
     */
    val eventListenerRegistry: EventListenerRegistry

    /**
     * *Sandstone* Command manager
     */
    val commandManager: CommandManager

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