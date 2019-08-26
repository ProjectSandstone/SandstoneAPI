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
package com.github.projectsandstone.example;

import com.github.jonathanxd.iutils.text.Text;
import com.github.projectsandstone.api.entity.living.player.Player;
import com.github.projectsandstone.api.event.message.MessageEvent;
import com.github.projectsandstone.api.event.player.PlayerEvent;
import com.github.koresframework.eventsys.event.annotation.Listener;
import com.github.koresframework.eventsys.event.annotation.Name;
import com.github.koresframework.eventsys.event.property.GetterProperty;

public class MyListener2 {

    @Listener
    public void message(MessageEvent event) {
        Text text = event.getMessage();
        // ...
    }

    @Listener
    public void message(MessageEvent event, @Name("message") Text message) {

    }

    @Listener
    public void message2(MessageEvent event) {
        if (event instanceof PlayerEvent) {
            PlayerEvent playerEvent = (PlayerEvent) event;

            Text message = event.getMessage();
            Player player = playerEvent.getPlayer();
            // ...
        }
    }

    @Listener
    public void message3(MessageEvent event) {
        GetterProperty<Player> playerProp = event.getGetterProperty(Player.class, "player");

        if (playerProp != null) {
            Text message = event.getMessage();
            Player player = playerProp.getValue();
            // ...
        }
    }

    @Listener
    public void message4(MessageEvent event,
                        @Name("message") Text message,
                        @Name("player") Player player) {

    }


}
