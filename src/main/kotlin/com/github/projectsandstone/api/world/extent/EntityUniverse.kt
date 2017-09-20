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
package com.github.projectsandstone.api.world.extent

import com.flowpowered.math.vector.Vector3d
import com.github.projectsandstone.api.entity.Entity
import com.github.projectsandstone.api.entity.EntityType
import com.github.projectsandstone.api.inventory.ItemStack

/**
 * Container of entities instance.
 */
interface EntityUniverse {

    /**
     * Entities
     */
    val entities: Collection<Entity>

    /**
     * Gets entities inside the area
     */
    fun getEntities(from: Vector3d, to: Vector3d): Collection<Entity> {
        val min = from.min(to)
        val max = from.max(to)

        return this.entities.filter {
            val position = it.location.position

            return@filter position in min..max
        }
    }

    /**
     * Creates an entity (the entity may or may not be spawned at [location]).
     *
     * @param type Entity type.
     * @param location Location of entity in the universe.
     * @return Entity instance if the entity can be spawned in [location] or null if not.
     */
    fun createEntity(type: EntityType, location: Vector3d): Entity?


    /**
     * Spawn the entity in the universe (if it is not already spawned).
     *
     * @param entity Entity instance.
     * @return True if the entity spawned with success.
     */
    fun spawnEntity(entity: Entity): Boolean

    /**
     * Spawn the entity in the universe.
     *
     * @param type Type of the entity.
     * @param location Location of the entity in the universe.
     * @return True if the entity spawned with success.
     */
    fun spawnEntity(type: EntityType, location: Vector3d): Boolean

    /**
     * Spawns a item stack in the universe.
     *
     * @param stack item stack.
     * @param location Location to spawn item.
     * @return True if the item spawned with success.
     */
    fun spawnItem(stack: ItemStack, location: Vector3d): Boolean

}