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
@file:JvmName("CommonArgumentTypes")

package com.github.projectsandstone.api.command

import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.jonathanxd.kwcommands.argument.ArgumentType
import com.github.jonathanxd.kwcommands.parser.*
import com.github.jonathanxd.kwcommands.util.simpleArgumentType
import com.github.projectsandstone.api.Game
import com.github.projectsandstone.api.entity.living.player.Player
import com.github.projectsandstone.api.text.PLAYER_NOT_FOUND

fun playerArgumentType(game: Game): ArgumentType<SingleInput, Player> =
        simpleArgumentType(
                PlayerTransformer(game),
                PlayerValidator(game),
                PlayerPossibilities(game),
                null,
                TypeInfo.of(Player::class.java))

class PlayerTransformer(val game: Game) : Transformer<SingleInput, Player> {
    override fun invoke(value: SingleInput): Player =
            game.server.players.first { player -> player.name == value.input }

}

class PlayerValidator(val game: Game) : Validator<SingleInput> {
    override fun invoke(argumentType: ArgumentType<SingleInput, *>, value: SingleInput): Validation =
            if (game.server.players.any { player -> player.name == value.input }) valid()
            else invalid(value, argumentType, this, PLAYER_NOT_FOUND)

}

class PlayerPossibilities(val game: Game) : Possibilities {
    override fun invoke(): List<Input> =
            game.objectHelper.createLiveListFromColl(game.server.players, { SingleInput(it.name) })

}
