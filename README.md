# SandstoneAPI
Server Modding API for Minecraft.

Sandstone is very inspired by [SpongePowered](https://www.spongepowered.org/) (Some API characteristics is very similar (likely identical) to SpongePowered),
and [Bukkit API](https://bukkit.org/), but the Sandstone purpose is a bit different, Sandstone API aims to support all Minecraft Editions Platforms.

## What is Sandstone

Sandstone is a Server Modding API for Minecraft that aims to provide a common API to develop mods that works in multiple Minecraft Editions, Sandstone is designed to support Minecraft PE and Minecraft PC Edition.
Sandstone is not a server, it's only a API, and the API is implemented by a Platform-Dependent plugin (like SandstoneBukkit, SandstoneSponge and SandstoneNukkit), Sandstone is written in Kotlin, and we plan to provide a LUA Plugin API to support non-JVM platforms like PocketMine-MP.

## Supported Languages

- Java
- Kotlin (**partial**)
- Scala (`object` plugins only)

## Officially supported platforms

- Bukkit
- Sponge
- Nukkit

## How Sandstone works

It depends on implementation, but commonly we use `Adapter` pattern and weak cache some instances.

## Attention

**Does not compare Sandstone objects by identity (`==` in Java and `===` in Kotlin), use the equals method in Java and `==` in Kotlin to compare objects**

**Adapter delegates `hashCode` and `equals` methods to `Adaptee` instance.**

## Inconsistencies

Please report any inconsistency between platforms, Sandstone MUST be consistent between Platforms, if operation `c()` returns `9` in `Platform A` then the same operation **MUST** return `9` in `Platform B`, `Platform C`...

Inconsistencies MUST be reported and fixed in the implementation and not in the `API` or `Common` project.

## Debug parameters

Sandstone provides debug vm parameters to save all Sandstone generated classes: `-Dsandstone.debug=true`.

If you want to save only `Event` implementation generated classes: `-Dsandstone.debug.eventgen=true`

If you want to save only `@Listener` generated classes: `-Dsandstone.debug.listenergen=true`

__CodeProxy provides `-Dcodeproxy.saveproxies=true` to save Proxy classes__

**Obs: these parameters is for debug purposes only, saved classes help us to find bugs in the Code Generator, if you are not a advanced user trying to find the cause of a bug, does not use these parameters!!!**

## More

- [CONTRIBUTING](CONTRIBUTING.md)
- [FAQ](FAQ.md)