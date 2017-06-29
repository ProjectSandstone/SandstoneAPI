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
package com.github.projectsandstone.api.event

import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.block.BlockState
import com.github.projectsandstone.api.entity.Entity
import com.github.projectsandstone.api.entity.living.player.Player
import com.github.projectsandstone.api.event.achievement.GrantAchievementEvent
import com.github.projectsandstone.api.event.block.BlockInteractEvent
import com.github.projectsandstone.api.event.entity.damage.DamageCause
import com.github.projectsandstone.api.event.entity.damage.EntityDamageEvent
import com.github.projectsandstone.api.event.init.*
import com.github.projectsandstone.api.event.message.MessageChannelEvent
import com.github.projectsandstone.api.event.message.MessageEvent
import com.github.projectsandstone.api.event.player.PlayerEvent
import com.github.projectsandstone.api.event.plugin.PluginLoadFailedEvent
import com.github.projectsandstone.api.event.plugin.PluginLoadedEvent
import com.github.projectsandstone.api.event.plugin.PluginLoadingEvent
import com.github.projectsandstone.api.event.service.ChangeServiceProviderEvent
import com.github.projectsandstone.api.plugin.PluginContainer
import com.github.projectsandstone.api.plugin.PluginManager
import com.github.projectsandstone.api.service.RegisteredProvider
import com.github.projectsandstone.api.statistic.Achievement
import com.github.projectsandstone.api.text.Text
import com.github.projectsandstone.api.text.channel.MessageChannel
import com.github.projectsandstone.api.world.World
import com.github.projectsandstone.eventsys.event.annotation.Extension
import com.google.common.util.concurrent.Futures
import java.util.concurrent.Future

/**
 * Base interface to create event instances.
 */
interface SandstoneEventFactory {

    fun createServerStartingEvent(): ServerStartingEvent

    fun createServerStartedEvent(loadedWorlds: List<World>): ServerStartedEvent

    fun createServerStoppedEvent(): ServerStoppedEvent

    fun createServerStoppingEvent(): ServerStoppingEvent

    fun createPreInitializationEvent(): PreInitializationEvent

    fun createInitializationEvent(): InitializationEvent

    fun createPostInitializationEvent(): PostInitializationEvent

    fun createPluginLoadingEvent(pluginManager: PluginManager, pluginContainer: PluginContainer): PluginLoadingEvent

    fun createPluginLoadedEvent(pluginManager: PluginManager, pluginContainer: PluginContainer): PluginLoadedEvent

    fun createPluginLoadFailedEvent(pluginManager: PluginManager, pluginContainer: PluginContainer, reason: PluginLoadFailedEvent.Reason): PluginLoadFailedEvent

    fun <T : Any> createChangeServiceProviderEvent(service: TypeInfo<T>,
                                                   oldProvider: RegisteredProvider<T>?,
                                                   newProvider: RegisteredProvider<T>): ChangeServiceProviderEvent<T>

    fun <T : Any> createChangeServiceProviderEvent(service: Class<T>,
                                                   oldProvider: RegisteredProvider<T>?,
                                                   newProvider: RegisteredProvider<T>): ChangeServiceProviderEvent<T> =
            createChangeServiceProviderEvent(TypeInfo.of(service), oldProvider, newProvider)

    fun createEntityDamageEvent(damageCause: DamageCause, entities: List<Entity>, world: World): EntityDamageEvent

    fun createBlockInteractEvent(block: BlockState): BlockInteractEvent

    @Extension(implement = PlayerEvent::class)
    fun createPlayerBlockInteractEvent(player: Player, block: BlockState): BlockInteractEvent // TODO: World parameter in cleaner way

    fun createMessageEvent(message: Text): MessageEvent

    @Extension(implement = PlayerEvent::class)
    fun createMessageEvent(player: Player, message: Text): MessageEvent

    fun createMessageChannelEvent(channel: MessageChannel?, message: Text): MessageChannelEvent

    @Extension(implement = PlayerEvent::class)
    fun createMessageChannelEvent(player: Player, channel: MessageChannel?, message: Text): MessageChannelEvent

    fun createGrantAchievementEvent(achievement: Achievement, channel: MessageChannel?, message: Text): GrantAchievementEvent

    @Extension(implement = PlayerEvent::class)
    fun createGrantAchievementEvent(player: Player, achievement: Achievement, channel: MessageChannel?, message: Text): GrantAchievementEvent

    companion object {

        private var instance_: SandstoneEventFactory? = null
        private var async: Future<SandstoneEventFactory>? = null

        val instance: SandstoneEventFactory
            get() = if (instance_ != null) {
                if (async != null)
                    async = null

                instance_!!
            } else if (async != null) async!!.get() else
                Sandstone.eventManager.eventGenerator.createFactory(SandstoneEventFactory::class.java).let {
                    instance_ = it
                    return it
                }


        fun createAsync(): Future<SandstoneEventFactory> {
            return if (instance_ != null)
                Futures.immediateFuture(instance_)
            else
                if (async != null) {
                    val asc = async!!
                    if (asc.isDone) {
                        instance_ = asc.get()
                        async = null
                    }

                    asc
                } else
                    Sandstone.eventManager.eventGenerator.createFactoryAsync(SandstoneEventFactory::class.java).let {
                        async = it
                        return@let it
                    }
        }
    }
}