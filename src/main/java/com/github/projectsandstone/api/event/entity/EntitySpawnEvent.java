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
package com.github.projectsandstone.api.event.entity;

import com.github.projectsandstone.eventsys.ap.Factory;

import org.jetbrains.annotations.NotNull;

import static com.github.projectsandstone.api.event.Constants.FACTORY_CLASS;

/**
 * Fired when an {@link com.github.projectsandstone.api.entity.Entity} is spawned.
 */
@Factory(FACTORY_CLASS)
public interface EntitySpawnEvent extends EntityEvent {

    /**
     * Reason of mob spawning.
     */
    @NotNull
    Cause getCause();

    enum Cause {

        /**
         * Natural mob spawning
         */
        NATURAL,

        /**
         * Spawned from a build of a player.
         */
        BUILD,

        /**
         * Mob spawner.
         */
        SPAWNER,

        /**
         * Creative spawn egg
         */
        SPAWN_EGG,

        /**
         * Projectiles (like chicken eggs and ender pearl)
         */
        PROJECTILE,

        /**
         * Custom spawning.
         */
        CUSTOM,

        /**
         * Unknown spawn reason.
         */
        UNKNOWN
    }
}