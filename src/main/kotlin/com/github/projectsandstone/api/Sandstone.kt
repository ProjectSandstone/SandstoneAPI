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
package com.github.projectsandstone.api

import com.github.projectsandstone.api.event.EventManager
import com.github.projectsandstone.api.logging.Logger
import com.github.projectsandstone.api.logging.LoggerFactory
import com.github.projectsandstone.api.plugin.PluginManager
import com.github.projectsandstone.api.scheduler.Scheduler
import com.github.projectsandstone.api.service.ServiceManager

/**
 * Created by jonathan on 12/08/16.
 */

object Sandstone {

    @JvmStatic
    private lateinit var game_: Game

    @JvmStatic
    private lateinit var logger_: Logger

    @JvmStatic
    private lateinit var loggerFactory_: LoggerFactory

    /**
     * *[Game]*
     */
    @JvmStatic
    val game: Game
        get() = game_

    /**
     * *Sandstone* global logger.
     *
     * Is not recommended to use *Sandstone* logger.
     *
     * if you wan't to log messages use dependency injection to get [Logger] instance.
     */
    @JvmStatic
    val logger: Logger
        get() = logger_

    /**
     * *Sandstone* plugin [Logger] factory.
     *
     * This factory is used to create [Logger] for plugins.
     */
    @JvmStatic
    val loggerFactory: LoggerFactory
        get() = loggerFactory_


    /**
     * *Sandstone* [PluginManager]
     */
    @JvmStatic
    val pluginManager: PluginManager
        get() = this.game.pluginManager

    /**
     * *Sandstone* [Scheduler]
     */
    @JvmStatic
    val scheduler: Scheduler
        get() = this.game.scheduler

    /**
     * *Sandstone* [Platform]
     */
    @JvmStatic
    val platform: Platform
        get() = this.game.platform

    /**
     * *Sandstone* [EventManager]
     */
    @JvmStatic
    val eventManager: EventManager
        get() = this.game.eventManager

    /**
     * *Sandstone* [EventManager]
     */
    @JvmStatic
    val serviceManager: ServiceManager
        get() = this.game.serviceManager

    /**
     * *Sandstone* [Server]
     */
    @JvmStatic
    val server: Server
        get() = this.game.server
}