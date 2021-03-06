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
import com.github.jonathanxd.kwcommands.information.InformationProviders
import com.github.jonathanxd.kwcommands.manager.InstanceProvider
import com.github.projectsandstone.api.annotation.PluginInstance
import com.github.projectsandstone.api.entity.living.player.Player

/**
 * Command Manager interface.
 */
interface CommandManager : CommandDispatcher {

    /**
     * Gets argument types.
     */
    val argumentTypes: ArgumentTypes

    /**
     * All registered commands
     */
    val commands: Set<RegisteredCommand>

    /**
     * Registers [command] for [ownerPlugin] and returns true if [command] was registered with success.
     */
    fun registerCommand(command: Command, @PluginInstance ownerPlugin: Any): Boolean

    /**
     * Unregisters [command] registered by [ownerPlugin] and returns true if command was removed with success.
     */
    fun unregisterCommand(command: Command, @PluginInstance ownerPlugin: Any): Boolean

    /**
     * Register all commands available via annotations in [any] for [ownerPlugin].
     */
    fun registerInstance(any: Any, @PluginInstance ownerPlugin: Any): Boolean

    /**
     * Register all commands available via annotations in [klass] for [ownerPlugin].
     */
    fun <T: Any> registerInstance(klass: Class<T>, instance: T, @PluginInstance ownerPlugin: Any): Boolean

    /**
     * Register all commands available via annotations in [klass] and inner classes for [ownerPlugin].
     *
     * @param instanceProvider Provider of type instances.
     */
    fun <T: Any> registerInstance(klass: Class<T>, instanceProvider: InstanceProvider, @PluginInstance ownerPlugin: Any): Boolean

    /**
     * Unregister all commands available via annotations in [any] registered by [ownerPlugin] and returns true if all commands
     * was removed.
     */
    fun unregisterInstance(any: Any, @PluginInstance ownerPlugin: Any): Boolean

    /**
     * Unregister all commands available via annotations in [klass] registered by [ownerPlugin] and returns true if all commands
     * was removed.
     */
    fun unregisterCommands(klass: Class<*>, @PluginInstance ownerPlugin: Any): Boolean

    /**
     * Gets command by [name].
     */
    fun getCommand(name: String): Command? =
            this.getCommand(name, null)

    /**
     * Gets command by [name] and [owner]. If [owner] is null, the command
     * will be only matched by [name].
     */
    fun getCommand(name: String, owner: Any?): Command?

    /**
     * Gets sub command with [name] of [parent command][parent].
     */
    fun getSubCommand(parent: Command, name: String): Command?

    /**
     * Gets all commands registered by [ownerPlugin].
     */
    fun getCommandsRegisteredBy(@PluginInstance ownerPlugin: Any): Set<Command>

    /**
     * Creates an instance of [InformationProviders]
     */
    fun createInformationProviders(): InformationProviders

    /**
     * Creates an instance of [InformationProviders] with [pluginInstance] and some elements of
     * [pluginInstance] as registered static information.
     */
    fun createInformationProviders(@PluginInstance pluginInstance: Any): InformationProviders

    /**
     * Creates an instance of [InformationProviders] with [player],
     * [pluginInstance] and some elements of [pluginInstance] as registered static information.
     */
    fun createInformationProviders(@PluginInstance pluginInstance: Any, player: Player): InformationProviders

    /**
     * Creates an instance of [InformationProviders] with [player] as registered static information.
     */
    fun createInformationProviders(player: Player): InformationProviders

    /**
     * Prints [command] help for [source].
     */
    fun printCommandHelp(source: CommandSource, command: String)

    /**
     * Gets [command] completion suggestions.
     */
    fun getSuggestions(command: String, informationProviders: InformationProviders): List<String>
}