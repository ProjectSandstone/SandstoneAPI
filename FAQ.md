# FAQ


- **Why another plugin API? Bukkit, Sponge, Forge, Spigot, Nukkit, etc... aren't enough?**
  - Yes, they are enough, but none of them solve the current problem: A plugin written to Bukkit will not work in Sponge, a plugin written to Nukkit will not work in Sponge. A limited multi-platform support exists between Bukkit, Spigot and PaperSpigot, but it's not enough, we need a multi-platform and multi-edition support. Sandstone comes to solve this problem, a plugin written on top of Sandstone API works in multiple Minecraft platforms and editions.

- Can they support platform `X`?
  - Yes, if the platform is mature and have a good community we will support it, if you want to request a platform support send a PR to [GPPS Platform Support](https://github.com/ProjectSandstone/GPPS/platform_support/).

- When they will support version `X` of Minecraft (or version `Y` of Platform `Z`)?
  - It depends on the state of supported platforms, if platform `Z` supports `1.10.2` but platform `A` currently supports only `1.8.9`, Sandstone will partially support `1.10.2`, it means that `Elytra` will be present in implementation to platform `Z` but not in implementation to platform `A`. Sandstone always track the latest version of Minecraft regardless the version of supported platforms.
  
- Are mods supported?
  - Only if the platform support mods (like `SpongePowered`).

- How to install Sandstone?
  - Officially implementations is provided as a plugin, you only need to install like a normal plugin (read the platform documentation to learn how to install plugins).
  
- How to install Sandstone Plugins?
  - Sandstone create `Sandstone` directory in the root directory of the server, inside this directory the Sandstone create the `plugins` directory, all plugins should be dropped inside this directory.
  
- Where is configuration saved?
  - In `config` directory inside the `Sandstone` directory.
  
- Which is the default configuration format?
  - Sandstone uses `json` as the default configuration format, the default configuration saver uses a user-friendly format to save json (multiline).


# Developer FAQ

- How to write multi-platform and multi-edition plugins and activate and deactivate features depending on platform and edition?
  - Sandstone provide `Platform` class and `GameEdition`, both can be retrieved from a `Game` instance, the `Platform.platformName` returns the platform name like `Spigot`, `SpongeVanilla`, `CraftBukkit`, `PaperSpigot`, and `Platform.platformBaseName` the base platform name like `Sponge`, `Bukkit`, `Nukkit`, etc... The `GameEdition` may be `GameEditions.PE`, `GameEditions.PC` or a `GameEdition` defined by the implementation.

- Can I create a __plugin__ that extends the platform support? 
  - Yes, and it is simple, `SandstoneCommon` uses `AdapterHelper` to provide class adapters, first create your own class adapter, then register in `Adapters.adapters`. If you need a better reference see the **SandstoneCommon** Readme and the **AdapterHelper** documentation.
  - Only for projects that implements **SandstoneCommon** and support **AdapterHelper**, all official implementations supports **AdapterHelper**.
  - **Attention: Adapter is platform dependent, creating Adapters for non-platform classes will not work. Make sure to read the Adapters section of the implementation**