# Auto Third Person — Minecraft 1.21.11 Port

A port of **AutoThirdPerson v2.2** (originally supporting up to 1.21.5) to **Minecraft 1.21.11** (Fabric).

## Port Details

- Artifact: `build/libs/auto_third_person-2.2+1.21.11.jar`
- Dependencies: Fabric API 0.141.3+1.21.11
- Loader: Fabric Loader >= 0.18.4 (0.19.3 also works)
- Client-side only

## Features

Automatically switches your camera to third-person view when riding vehicles and mounts (minecarts, boats, horses, etc.).

## Porting Notes (1.21.11 breaking changes)

1. **Entity class re-package**: `Boat` → `vehicle.boat` subpackage, `Minecart` → `vehicle.minecart`
2. **`LocalPlayer.startRiding` now takes 3 args**: `(Entity, boolean, boolean)`
3. **`DebugOverlay` → `DebugScreenOverlay`**
4. **Key binding categories**: `KeyMapping`'s `String` category → `Category` record (`Category.MISC`)
5. **Resource locations**: `ResourceLocation` → `Identifier` (`fromNamespaceAndPath`)
6. **Fabric API resource loading**: the whole `resource-loader-v0` reload-listener API is `@Deprecated` → switched to **resource-loader-v1**'s `ResourceLoader.get(PackType).registerReloader(Identifier, ResourceManagerReloadListener)`
7. **pack.mcmeta**: `min_format`/`max_format` are now required (>64); the 1.21.11 resource format is **75**

## Configuration

- Config file: `.minecraft/config/auto_third_person.cfg` (CrummyConfig format)
- Reload after editing with **F3+T** or the `/auto_third_person reload` client command

## Building

```
gradle build
```

The jar will be at `build/libs/auto_third_person-2.2+1.21.11.jar`.

## Installation

Place the jar into `versions/1.21.11-Fabric/mods/`. Fabric API is required.

## Upstream & License

- Original mod: **AutoThirdPerson** by quat1024 — https://github.com/quat1024/AutoThirdPerson
- Original license: **LGPL-3.0-or-later** (this port is likewise licensed under LGPL-3.0-or-later)
- Ported from quat1024's official mojmap sources (Xplat-1.21.5 / Fabric-1.21.5 / Core / CrummyConfig)

## Source Layout

```
src/main/java/agency/highlysuspect/...
├── Core logic (third-person camera switching)
├── Fabric platform entry points
└── CrummyConfig configuration system
```
