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
package com.github.projectsandstone.api.event.block;

import com.github.projectsandstone.api.block.BlockState;
import com.github.projectsandstone.api.event.player.PlayerEvent;
import com.github.projectsandstone.api.util.Transaction;
import com.github.projectsandstone.eventsys.ap.Factory;
import com.github.projectsandstone.eventsys.event.Cancellable;
import com.github.projectsandstone.eventsys.event.Event;
import com.github.projectsandstone.eventsys.event.annotation.Extension;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.projectsandstone.api.event.Constants.FACTORY_CLASS;

/**
 * Fired when a group of block states is changed.
 */
@Factory(FACTORY_CLASS)
@Factory(value = FACTORY_CLASS, extensions = @Extension(implement = PlayerEvent.class))
public interface BlockChangeEvent extends Event, Cancellable {

    /**
     * Transaction of blocks which will be affected.
     *
     * @return Transaction of blocks which will be affected.
     */
    @NotNull
    List<Transaction<BlockState>> getTransactions();

    /**
     * When block state group is changed to burn state
     */
    @Factory(value = FACTORY_CLASS, inheritProperties = true)
    interface Burn extends BlockChangeEvent {

    }

    /**
     * When block state group is changed as result of placement
     */
    @Factory(value = FACTORY_CLASS, inheritProperties = true)
    interface Place extends BlockChangeEvent {

    }

    /**
     * When block state group is changed as result of block breaking
     */
    @Factory(value = FACTORY_CLASS, inheritProperties = true)
    interface Break extends BlockChangeEvent {

    }

    /**
     * When block state group is changed as result of a modification of states.
     */
    @Factory(value = FACTORY_CLASS, inheritProperties = true)
    interface Modify extends BlockChangeEvent {

    }

}
