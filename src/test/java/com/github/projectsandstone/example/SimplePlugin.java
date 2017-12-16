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

import com.google.inject.Inject;

import com.github.jonathanxd.iutils.text.Text;
import com.github.projectsandstone.api.Game;
import com.github.projectsandstone.api.Sandstone;
import com.github.projectsandstone.api.block.BlockState;
import com.github.projectsandstone.api.block.BlockTypes;
import com.github.projectsandstone.api.entity.living.player.Player;
import com.github.projectsandstone.api.event.block.BlockInteractEvent;
import com.github.projectsandstone.api.event.init.InitializationEvent;
import com.github.projectsandstone.api.event.message.MessageEvent;
import com.github.projectsandstone.api.event.player.PlayerEvent;
import com.github.projectsandstone.api.inventory.CarriedInventory;
import com.github.projectsandstone.api.inventory.Carrier;
import com.github.projectsandstone.api.inventory.ItemStack;
import com.github.projectsandstone.api.inventory.OperationResult;
import com.github.projectsandstone.api.inventory.TransactionResult;
import com.github.projectsandstone.api.item.ItemType;
import com.github.projectsandstone.api.item.ItemTypes;
import com.github.projectsandstone.api.plugin.Plugin;
import com.github.projectsandstone.api.plugin.PluginDefinition;
import com.github.projectsandstone.api.util.version.Schemes;
import com.github.projectsandstone.api.world.Location;
import com.github.projectsandstone.api.world.World;
import com.github.projectsandstone.eventsys.event.annotation.Listener;
import com.github.projectsandstone.eventsys.event.annotation.Name;
import com.github.projectsandstone.eventsys.event.property.GetterProperty;
import com.github.projectsandstone.example.myevent.MyEvent;
import com.github.projectsandstone.example.myevent.MyFactory;
import com.github.projectsandstone.example.myevent.MyObj;
import com.github.projectsandstone.example.myevent.MyOwnEvent;

import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    }

    private void doEventThings() {
        MyFactory factory = game.getEventManager().getEventGenerator().createFactory(MyFactory.class);

        MyEvent myEvent = factory.createMyEvent(new MyObj());

        game.getEventManager().registerListeners(this, new MyListener2());
        game.getEventManager().registerListener(this, MessageEvent.class, new MyListener());

        game.getEventManager().dispatch(new MyOwnEvent(new MyObj()), this);

        game.getEventManager().dispatch(new MyOwnEvent(new MyObj()), this);

        game.getServiceManager().setProvider(this, MyService.class, new MyServiceImpl());

        MessageEvent test = Sandstone.getEventFactory().createMessageEvent(Text.of("Test"));

        game.getEventManager().dispatch(test, this);

        boolean cancelled = test.isCancelled();

        if (!cancelled) {
            logger.info("Message sent!!!");
        }
    }


    @Listener
    public void playerChat(MessageEvent event) {
        // Property:
        GetterProperty<Player> property = event.getGetterProperty(Player.class, "player");
        if (property != null) {
            Player player = property.getValue();
            // ...
        }

        // PlayerEvent

        if (event instanceof PlayerEvent) {
            PlayerEvent playerEvent = (PlayerEvent) event;
            Player player = playerEvent.getPlayer();
        }
    }

    // Function parameter
    @Listener
    public void playerChat(MessageEvent event, @Name("player") Player player) {
        // ...
    }


    // Function parameter
    @Listener
    public void playerInteract(BlockInteractEvent event, @Name("player") Player player) {
        BlockState block = event.getBlockState();

        if (block.getType() == BlockTypes.LAVA) {
            player.sendMessage(Text.of("Wow, this is too hot!!!"));

            OperationResult<List<ItemStack>> take = player.getInventory().take(10, ItemTypes.STRING);

            if (take.isSuccess()) {
                List<ItemStack> result = take.getResult();
                // Do something
            }

        }

        if (block.getType() == BlockTypes.CHEST) {
            CarriedInventory<? extends Carrier> inventory = ((Carrier) block).getInventory();

            List<ItemStack> all = inventory.peekAll();

            TransactionResult<List<ItemStack>> listTransactionResult = player.getInventory().offerAll(all);

            for (ItemStack itemStack : listTransactionResult.getRejected()) {
                player.getLocation().getExtent().spawnItem(itemStack, player.getLocation().getPosition());
            }
        }

        if (block.getType() == BlockTypes.TRAPPED_CHEST) {
            CarriedInventory<? extends Carrier> inventory = ((Carrier) block).getInventory();

            ThreadLocalRandom current = ThreadLocalRandom.current();

            int i = current.nextInt(10) + 3;

            List<ItemType> itemTypes = this.game.getRegistry().getAll(ItemType.class);

            for (int x = 0; x < i; ++x) {

                ItemType item = itemTypes.get(current.nextInt(itemTypes.size()));

                ItemStack itemStack = ItemStack.Factory.of(item, current.nextInt(3) + 1);

                TransactionResult<ItemStack> offer = player.getInventory().offer(itemStack);

                ItemStack rejected = offer.getRejected();

                if(rejected.getQuantity() > 0) {

                    Location<World> location = player.getLocation();

                    World extent = location.getExtent();

                    extent.spawnItem(rejected, location.getPosition());
                }

            }

        }
    }
}
