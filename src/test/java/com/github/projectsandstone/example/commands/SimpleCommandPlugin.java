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
package com.github.projectsandstone.example.commands;

import com.google.inject.Inject;

import com.github.jonathanxd.kwcommands.command.Command;
import com.github.jonathanxd.kwcommands.command.CommandName;
import com.github.projectsandstone.api.Game;
import com.github.projectsandstone.api.command.CommonArguments;
import com.github.projectsandstone.api.command.CommonRequirements;
import com.github.projectsandstone.api.entity.living.player.Player;
import com.github.projectsandstone.api.event.init.InitializationEvent;
import com.github.projectsandstone.api.plugin.Plugin;
import com.github.projectsandstone.eventsys.event.annotation.Listener;

import org.slf4j.Logger;

import kotlin.Unit;

@Plugin(id = "com.github.projectsandstone.example", version = "1.0.0")
public class SimpleCommandPlugin {

    private final Game game;
    private final Logger logger;

    @Inject
    public SimpleCommandPlugin(Game game, Logger logger) {
        this.game = game;
        this.logger = logger;
    }

    @Listener
    public void init(InitializationEvent event) {
        game.getCommandManager().registerCommand(Command.builder()
                        .name(CommandName.name("tp"))
                        .description("Teleport a player to another")
                        .addRequirements(CommonRequirements.permission("player.adm.tp"))
                        .addArgument(CommonArguments.player(this.game, "from").build())
                        .addArgument(CommonArguments.player(this.game, "target").build())
                        .handler((commandContainer, informationManager, resultHandler) -> {
                            Player from = commandContainer.getArgumentValue("from");
                            Player target = commandContainer.getArgumentValue("target");

                            if (from != null && target != null) {
                                from.teleport(target.getLocation());
                            }

                            return Unit.INSTANCE;
                        })
                        .build(),
                this);

        game.getCommandManager().registerInstance(PingCommand.class, new PingCommand(), this);
    }


}