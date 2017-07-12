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
package test

import com.github.jonathanxd.iutils.reflection.Reflection
import com.github.jonathanxd.iutils.type.AbstractTypeInfo
import com.github.jonathanxd.iutils.type.TypeInfo
import com.github.projectsandstone.api.Sandstone
import com.github.projectsandstone.api.constants.SandstonePlugin
import com.github.projectsandstone.api.event.service.ChangeServiceProviderEvent
import com.github.projectsandstone.api.plugin.PluginContainer
import com.github.projectsandstone.api.service.RegisteredProvider
import com.github.projectsandstone.eventsys.event.annotation.Mutable
import com.github.projectsandstone.eventsys.gen.event.CommonEventGenerator
import com.github.projectsandstone.eventsys.impl.CommonLogger
import com.github.projectsandstone.example.MyService
import com.github.projectsandstone.example.MyServiceImpl
import java.nio.file.Paths

fun main(args: Array<String>) {

    val type = object : AbstractTypeInfo<ChangeServiceProviderEvent<MyService>>() {}

    Reflection.changeFinalField(Sandstone::class.java, null, "sandstonePath_", Paths.get("."))

    val gen = CommonEventGenerator(CommonLogger()).createFactory(MyFct::class.java)
            .createProvider(TypeInfo.of(MyService::class.java), null, MyProvider, "A")

    println("Has int propertyV: ${gen.hasProperty(Int::class.java, "propertyV")}")
    println("Has String propertyV: ${gen.hasProperty(String::class.java, "propertyV")}")

    val property = gen.getGSProperty(String::class.java, "propertyV")!!

    property.getValue().let(::println)

    assert(gen.getProperty(Int::class.java, "propertyV") == null)


}

object MyProvider : RegisteredProvider<MyService> {
    override val service: Class<MyService> = MyService::class.java
    override val provider: MyService = MyServiceImpl()
    override val plugin: PluginContainer = SandstonePlugin

}

interface MyFct {
    fun <T : Any> createProvider(service: TypeInfo<T>,
                                 oldProvider: RegisteredProvider<T>?,
                                 newProvider: RegisteredProvider<T>,
                                 @Mutable propertyV: String): ChangeServiceProviderEvent<T>
}