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
@file:JvmName("CommonInformation")

/**
 * Common information ids, most of Sandstone information is provided instead of registered, this means that information
 * is dynamically resolved.
 */
package com.github.projectsandstone.api.command

import com.github.jonathanxd.kwcommands.information.Information
import com.github.projectsandstone.api.entity.living.player.Player
import com.github.projectsandstone.api.plugin.PluginContainer
import com.github.projectsandstone.api.service.permission.Subject
import org.slf4j.Logger

val PERMISSION_SUBJECT = Information.Id(Subject::class.java, arrayOf("subject"))
val COMMAND_SOURCE_SUBJECT = Information.Id(CommandSource::class.java, arrayOf("command_source"))
val PLAYER_SUBJECT = Information.Id(Player::class.java, arrayOf("player"))
val LOGGER_SUBJECT = Information.Id(Logger::class.java, arrayOf("logger"))
val PLUGIN_SUBJECT = Information.Id(PluginContainer::class.java, arrayOf("plugin"))