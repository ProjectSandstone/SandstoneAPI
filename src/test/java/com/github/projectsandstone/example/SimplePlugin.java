/**
 *      SandstoneAPI - Minecraft Server Modding API
 *
 *         The MIT License (MIT)
 *
 *      Copyright (c) 2017 Sandstone <https://github.com/ProjectSandstone/>
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
package com.github.projectsandstone.example;

import com.google.inject.Inject;

import com.github.projectsandstone.api.Game;
import com.github.projectsandstone.api.event.Listener;
import com.github.projectsandstone.api.event.init.InitializationEvent;
import com.github.projectsandstone.api.logging.Logger;
import com.github.projectsandstone.api.plugin.Plugin;
import com.github.projectsandstone.api.plugin.PluginDefinition;
import com.github.projectsandstone.api.util.version.Schemes;

/**
 * Created by jonathan on 13/08/16.
 */
@Plugin(id = "com.github.projectsandstone.example", version = "1.0.0")
public class SimplePlugin {

    private final Game game;
    private final Logger logger;

    @Inject
    public SimplePlugin(Game game, Logger logger, PluginDefinition pluginDefinition) {
        this.game = game;
        this.logger = logger;
        pluginDefinition.applyVersion(version -> version.changeScheme(Schemes.getSemVerScheme()));
    }

    @Listener
    public void onInit(InitializationEvent initializationEvent) {
        logger.info("Simple plugin initialized!");

        game.getServiceManager().setProvider(this, MyService.class, new MyServiceImpl());
    }

}
