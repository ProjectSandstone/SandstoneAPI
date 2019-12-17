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
package com.github.projectsandstone.api.event;

import com.github.koresframework.eventsys.event.Event;
import com.github.koresframework.eventsys.event.EventListener;
import com.github.koresframework.eventsys.event.EventPriority;
import com.github.koresframework.eventsys.result.ListenResult;

import org.jetbrains.annotations.NotNull;

public interface JavaEventListener<T extends Event> extends EventListener<T> {

    @Override
    ListenResult onEvent(T t, Object owner);

    @NotNull
    @Override
    default EventPriority getPriority() {
        return EventListener.DefaultImpls.getPriority(this);
    }

    @Override
    default String getChannel() {
        return EventListener.DefaultImpls.getChannel(this);
    }

    @Override
    default boolean getIgnoreCancelled() {
        return EventListener.DefaultImpls.getIgnoreCancelled(this);
    }

    @Override
    default boolean getCancelAffected() {
        return EventListener.DefaultImpls.getCancelAffected(this);
    }
}
