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
package com.github.projectsandstone.api.world

import org.spongepowered.math.vector.Vector3d
import org.spongepowered.math.vector.Vector3i
import com.github.projectsandstone.api.text.channel.MessageReceiver
import com.github.projectsandstone.api.util.Identifiable
import com.github.projectsandstone.api.world.extent.Extent
import java.nio.file.Path

/**
 * World.
 *
 * Multi-platform naming note: Some platforms use the name `Level` instead of `World`.
 */
interface World : MessageReceiver, Identifiable, Extent {

    /**
     * Name of the world
     */
    val name: String

    /**
     * World root directory
     */
    val directory: Path

    override val world: World
        get() = this

    override fun getLocation(position: Vector3d): Location<World> {
        return Location(this, position)
    }

    override fun getLocation(position: Vector3i): Location<World> {
        return this.getLocation(position.toDouble())
    }

    override fun getLocation(x: Double, y: Double, z: Double): Location<World> {
        return this.getLocation(Vector3d(x, y, z))
    }

    override fun getLocation(x: Int, y: Int, z: Int): Location<World> {
        return this.getLocation(Vector3i(x, y, z).toDouble())
    }

    @Suppress("UNCHECKED_CAST")
    override fun getWorldLocation(location: Location<Extent>): Location<World> {
        if(location.extent !is World)
            return location.extent.getWorldLocation(location)

        return location as Location<World>
    }
}