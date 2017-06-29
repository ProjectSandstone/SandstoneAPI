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
package com.github.projectsandstone.api.command

import com.github.jonathanxd.kwcommands.command.Command
import com.github.jonathanxd.kwcommands.manager.InformationManager
import com.github.jonathanxd.kwcommands.manager.InstanceProvider
import com.github.projectsandstone.api.entity.living.player.Player

/**
 * Command Manager interface.
 */
interface CommandManager : CommandDispatcher {

    /**
     * Registers [command] for [ownerPlugin] and returns true if [command] was registered with success.
     */
    fun registerCommand(command: Command, ownerPlugin: Any): Boolean

    /**
     * Unregisters [command] registered by [ownerPlugin] and returns true if command was removed with success.
     */
    fun unregisterCommand(command: Command, ownerPlugin: Any): Boolean

    /**
     * Register all commands available via annotations in [any] for [ownerPlugin].
     */
    fun registerInstance(any: Any, ownerPlugin: Any): Boolean

    /**
     * Register all commands available via annotations in [klass] for [ownerPlugin].
     */
    fun <T> registerInstance(klass: Class<T>, instance: T, ownerPlugin: Any): Boolean

    /**
     * Register all commands available via annotations in [klass] and inner classes for [ownerPlugin].
     *
     * @param instanceProvider Provider of type instances.
     */
    fun <T> registerInstance(klass: Class<T>, instanceProvider: InstanceProvider, ownerPlugin: Any): Boolean

    /**
     * Unregister all commands available via annotations in [any] registered by [ownerPlugin] and returns true if all commands
     * was removed.
     */
    fun unregisterInstance(any: Any, ownerPlugin: Any): Boolean

    /**
     * Unregister all commands available via annotations in [klass] registered by [ownerPlugin] and returns true if all commands
     * was removed.
     */
    fun unregisterCommands(klass: Class<*>, ownerPlugin: Any): Boolean

    /**
     * Gets all commands registered by [ownerPlugin].
     */
    fun getCommandsRegisteredBy(ownerPlugin: Any): Set<Command>

    /**
     * Creates an instance of [InformationManager]
     */
    fun createInformationManager(): InformationManager

    /**
     * Creates an instance of [InformationManager] with [player] as registered information.
     */
    fun createInformationManager(player: Player): InformationManager
}