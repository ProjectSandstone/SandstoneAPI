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
package com.github.projectsandstone.api.event.achievement;

import com.github.projectsandstone.api.event.Constants;
import com.github.projectsandstone.api.event.player.PlayerEvent;
import com.github.projectsandstone.api.statistic.Achievement;
import com.github.projectsandstone.eventsys.ap.Factory;
import com.github.projectsandstone.eventsys.event.Event;
import com.github.projectsandstone.eventsys.event.annotation.Extension;

import org.jetbrains.annotations.NotNull;

/**
 * Fired when an achievement is granted for a subject.
 */
@Factory(Constants.FACTORY_CLASS)
@Factory(value = Constants.FACTORY_CLASS, extensions = {@Extension(implement = PlayerEvent.class)})
public interface GrantAchievementEvent extends Event {

    /**
     * Granted achievement.
     *
     * @return Granted achievement.
     */
    @NotNull
    Achievement getAchievement();

}