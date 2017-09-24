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

import com.google.common.util.concurrent.Futures;

import com.github.jonathanxd.iutils.exception.RethrowException;
import com.github.projectsandstone.api.Sandstone;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SandstoneEventFactoryCache {
    private static SandstoneEventFactory instance = null;
    private static Future<SandstoneEventFactory> async = null;

    public static SandstoneEventFactory getInstance() {
        if (instance != null) {
            if (async != null)
                async = null;

            return SandstoneEventFactoryCache.instance;
        } else if (async != null) {
            try {
                instance = async.get();
                async = null;
            } catch (InterruptedException | ExecutionException e) {
                throw RethrowException.rethrow(e);
            }
            return instance;
        } else {
            return instance = Sandstone.getEventManager().getEventGenerator().createFactory(SandstoneEventFactory.class);
        }
    }

    public static Future<SandstoneEventFactory> getAsync() {
        if (instance != null) {
            return Futures.immediateFuture(SandstoneEventFactoryCache.instance);
        } else if (async != null) {
            Future<SandstoneEventFactory> asc = async;

            if (asc.isDone()) {
                try {
                    instance = asc.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw RethrowException.rethrow(e);
                }
                async = null;
            }

            return asc;
        } else {
            return async = Sandstone.getEventManager().getEventGenerator().createFactoryAsync(SandstoneEventFactory.class);
        }
    }
}
