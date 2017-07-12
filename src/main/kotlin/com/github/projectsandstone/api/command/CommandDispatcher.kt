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

import com.github.jonathanxd.kwcommands.manager.InformationManager
import com.github.jonathanxd.kwcommands.processor.CommandResult

/**
 * Dispatcher of commands.
 */
interface CommandDispatcher {

    /**
     * Dispatches a command as plain string.
     *
     * The default implementation calls [toCommandStringList] to splits the [commandStr] to a
     * [List] of [String] and calls [dispatch] with the list.
     */
    fun dispatch(commandStr: String, informationManager: InformationManager): List<CommandResult> {
        return this.dispatch(commandStr.toCommandStringList(), informationManager)
    }

    /**
     * Dispatches a command as a list of commands and arguments value.
     */
    fun dispatch(commandList: List<String>, informationManager: InformationManager): List<CommandResult>

    /**
     * Gets suggestion based on [input] and in [informationManager].
     */
    fun getSuggestions(input: String, informationManager: InformationManager)

}