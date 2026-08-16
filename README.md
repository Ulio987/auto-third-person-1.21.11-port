# Auto Third Person 1.21.11 移植版

将 **AutoThirdPerson v2.2**（原版 quaternary 2.2 支持 1.21.5）移植到 Minecraft **1.21.11**（Fabric）。

## 移植结果

- 产物：`build/libs/auto_third_person-2.2+1.21.11.jar`
- 依赖：Fabric API 0.141.3+1.21.11
- 加载器要求：Fabric Loader >= 0.18.4（0.19.3 亦可）
- 纯客户端 mod

## 功能

乘坐矿车/船/马等载具时自动切换到第三人称视角。

## 移植修复内容（1.21.11 关键点）

1. **实体类包下沉**：Boat → `vehicle.boat`、Minecart → `vehicle.minecart` 子包
2. **`LocalPlayer.startRiding` 变三参**：`(Entity, boolean, boolean)`
3. **`DebugOverlay` → `DebugScreenOverlay`**
4. **按键绑定分类**：`KeyMapping` 的 String 分类 → `Category` record（`Category.MISC`）
5. **资源定位符**：`ResourceLocation` → `Identifier`（`fromNamespaceAndPath`）
6. **Fabric API 资源加载**：resource-loader-v0 全家 `@Deprecated` → 改用 **resource-loader-v1** 的 `ResourceLoader.get(PackType).registerReloader(Identifier, ResourceManagerReloadListener)`
7. **pack.mcmeta**：需 `min_format`/`max_format`（>64 强制）；1.21.11 资源格式 = 75

## 配置

- 配置文件：`.minecraft/config/auto_third_person.cfg`（CrummyConfig 格式）
- 修改后 F3+T 或 `/auto_third_person reload` 刷新

## 构建

```
gradle build
```

产物在 `build/libs/auto_third_person-2.2+1.21.11.jar`。

## 安装

将 jar 放入 `versions/1.21.11-Fabric/mods/` 目录，需已安装 Fabric API。

## 原项目与许可证

- 原项目：**AutoThirdPerson** by quat1024，https://github.com/quat1024/AutoThirdPerson
- 原项目许可：**LGPL-3.0-or-later**（本移植版同样以 LGPL-3.0-or-later 授权）
- 移植基于 quat1024 官方仓库自带 mojmap 源码（Xplat-1.21.5 / Fabric-1.21.5 / Core / CrummyConfig）

## 目录结构

```
src/main/java/agency/highlysuspect/...
├── 核心逻辑（视角切换判断）
├── Fabric 平台入口
└── CrummyConfig 配置系统
```
