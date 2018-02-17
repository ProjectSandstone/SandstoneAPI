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

import com.github.projectsandstone.api.entity.living.player.Player
import com.github.projectsandstone.api.entity.living.player.User
import com.github.projectsandstone.api.world.World
import java.util.*

/**
 * Server instance.
 */
interface Server {

    /**
     * Server bound ip, or empty if not specified
     */
    val ip: String

    /**
     * Port of server
     */
    val port: Int

    /**
     * Name of server
     */
    val serverName: String

    /**
     * Server motd
     */
    val motd: String

    /**
     * Max players allowed in [Server]
     */
    val maxPlayers: Int

    /**
     * Worlds in the [Server].
     */
    val worlds: Collection<World>

    /**
     * Players currently connected to [Server].
     */
    val players: Collection<Player>

    /**
     * Gets online player by [uuid].
     */
    fun getOnlinePlayer(uuid: UUID): Player?

    /**
     * Gets online player by [name].
     */
    fun getOnlinePlayer(name: String): Player?

    /**
     * Gets user by [uuid].
     */
    fun getUser(uuid: UUID): User?

    /**
     * Gets world by [uuid].
     */
    fun getWorld(uuid: UUID): World?

    /**
     * Gets worl by [name].
     */
    fun getWorld(name: String): World?
}