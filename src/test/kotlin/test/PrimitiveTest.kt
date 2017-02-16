/**
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
package test

import com.github.jonathanxd.iutils.reflection.RClass
import com.github.jonathanxd.iutils.reflection.Reflection
import com.github.jonathanxd.iutils.type.ConcreteTypeInfo
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.block.BlockState
import com.github.projectsandstone.api.block.BlockType
import com.github.projectsandstone.api.block.BlockTypes
import com.github.projectsandstone.api.entity.living.player.Player
import com.github.projectsandstone.api.event.Event
import com.github.projectsandstone.api.event.SandstoneEventFactory
import com.github.projectsandstone.api.text.Text
import com.github.projectsandstone.api.util.SID
import com.github.projectsandstone.api.util.internal.gen.event.SandstoneEventGen
import com.github.projectsandstone.api.world.Location
import com.github.projectsandstone.api.world.World
import org.junit.Assert
import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) {

    val type = object : ConcreteTypeInfo<MyEvt>() {}

    Reflection.changeFinalField(RClass.getRClass(Sandstone::class.java), "sandstonePath_", Paths.get("."))

    val gen = SandstoneEventGen.gen(type, mapOf(
            "amount" to 9.toShort(),
            "ok" to false,
            "i" to 10.5F,
            "d" to 15.4,
            "l" to 100L
    ))

    println("Has short amount: ${gen.hasProperty(Short::class.javaPrimitiveType!!, "amount")}")

    val intGetter = gen.getIntGetterProperty("amount")!!

    val asInt = intGetter.getAsInt()

    println("As int: $asInt")

    val fakePlayer = TestPlayer()

    val iter = SandstoneEventFactory.instance.createPlayerBlockInteractEvent(fakePlayer, object : BlockState {
        override val type: BlockType
            get() = BlockTypes.BEDROCK

    })

    val player = iter.getGetterProperty(Player::class.java, "player")!!

    Assert.assertEquals("XZ", player.getValue())
}

class TestPlayer : Player {

    override val sandstoneId: SID
        get() = SID.UuidSid(UUID.randomUUID())

    override val location: Location<World>
        get() = TODO("not implemented")

    override val name: String
        get() = "XZ"

    override fun sendMessage(text: Text) {

    }

    override fun teleport(location: Location<*>) {

    }

    override val isOnline: Boolean
        get() = true

    override fun kick() {
    }

    override val player: Player?
        get() = this

    override fun kick(reason: Text) {

    }

}

interface MyEvt : Event {

    var amount: Short
    var ok: Boolean
    var i: Float
    var d: Double
    var l: Long

}