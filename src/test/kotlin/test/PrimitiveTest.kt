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
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.constants.SandstonePlugin
import com.github.projectsandstone.api.event.Event
import com.github.projectsandstone.api.event.service.ChangeServiceProviderEvent
import com.github.projectsandstone.api.plugin.PluginContainer
import com.github.projectsandstone.api.service.RegisteredProvider
import com.github.projectsandstone.api.util.internal.gen.event.PropertyInfo
import com.github.projectsandstone.api.util.internal.gen.event.SandstoneEventGen
import com.github.projectsandstone.example.MyService
import com.github.projectsandstone.example.MyServiceImpl
import java.nio.file.Paths

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



}

interface MyEvt : Event {

    var amount: Short
    var ok: Boolean
    var i: Float
    var d: Double
    var l: Long

}