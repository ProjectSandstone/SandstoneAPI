package com.github.projectsandstone.api.util

import com.github.jonathanxd.koresproxy.KoresProxy
import com.github.jonathanxd.koresproxy.gen.direct.DummyCustom

/**
 * Creates a dummy for type [T].
 */
inline fun <reified T> dummy(): T =
        KoresProxy.newProxyInstance {
            if (T::class.java.isInterface)
                it.interfaces(T::class.java)
            else
                it.superClass(T::class.java)

            it.classLoader(T::class.java.classLoader)
                    .invocationHandler { _, _, _, _ -> null }
                    .addCustom(DummyCustom.getInstance())
        }