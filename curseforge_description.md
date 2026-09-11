# TACZ Auto Fire — CurseForge Mod Description

## 英文版（English）

### TACZ Auto Fire

Make every TACZ semi-automatic and burst weapon fire continuously while you hold the trigger. No more click-spamming for semi-auto rifles or burst guns — just aim and hold.

This client-side addon for **Timeless & Classics Guns (TACZ)** (NeoForge 1.21.1) lets you configure exactly **which weapons** can be fired full-auto, through four independent modes and global gun ID rules:

#### ✨ Features

Global gun ID rules are evaluated only when GLOBAL is enabled. The priority is GLOBAL_BLACKLIST > GLOBAL_WHITELIST > GLOBAL_EXCLUDE_AUTO. Use complete TACZ gunid values, including the namespace, such as tacz:ak47. A blacklist entry wins if the same ID appears in both lists.

| Mode | How to enable | What it does |
| --- | --- | --- |
| **Global** | Set `GLOBAL` to `true` in the config | Every TACZ semi-auto / burst gun fires full-auto for every player |
| **NBT Tag** | Add `{AUTOallow:1b}` to a gun's root tag (e.g. via data pack, kubejs, or commands) | Only tagged guns fire full-auto |
| **Effect** | Give the player the `tacz_autofire:autofire` effect | While the effect is active, the player fires full-auto |
| **Attribute** *(new in 1.3.0)* | Raise the player's `tacz_autofire:autofire` attribute above 0 | While the attribute value is > 0, the player fires full-auto |

The NBT, Effect, and Attribute modes work together with OR logic. The `GLOBAL` switch overrides those non-global modes. Within Global mode, the priority is GLOBAL_BLACKLIST > GLOBAL_WHITELIST > GLOBAL_EXCLUDE_AUTO.

#### 🎮 Quick Start

**Attribute mode (1.3.0):**
```
/attribute @s tacz_autofire:autofire base set 1
```

**Effect mode:**
```
/effect give @s tacz_autofire:autofire 30 0
```

**NBT mode** (an example with an item modifier / data pack item):
```
/give @s tacz:ak47{AUTOallow:1b} 1
```

**Config** (`serverconfig/tacz_autofire-server.toml` after first launch):
```toml
GLOBAL    = true   # all guns full-auto
GLOBAL_BLACKLIST = ["tacz:ak47"] # deny these gun IDs in Global mode
GLOBAL_WHITELIST = ["tacz:some_gun"] # allow these gun IDs in Global mode
NBT       = true   # allow {AUTOallow:1b} guns
EFFECT    = true   # allow autofire effect
ATTRIBUTE = true   # allow autofire attribute (value > 0)
```

#### 📦 Requirements
- Minecraft **1.21.1**
- NeoForge **21.1.x**
- [Timeless & Classics Guns](https://www.curseforge.com/minecraft/mc-mods/timeless-and-classics-guns) **1.1.8+** (the mod will not load without it)

#### ❓ FAQ
- **Is it server-side?** The config is server-authoritative and synced to clients. The actual trigger behavior is patched client-side, so the mod must be installed on the client (and the server, if you want the config enforced).
- **Which guns are affected?** Any TACZ gun whose fire mode is SEMI or BURST. Fully-automatic guns are unaffected.

#### ⚖️ License
All Rights Reserved.

---

## 中文版（Chinese）

### TACZ 自动开火

按住扳机即可让所有 TACZ 半自动 / 点射枪械持续开火。不再需要疯狂连点——瞄准、按住，完事。

本模组是 **Timeless & Classics Guns (TACZ)**（NeoForge 1.21.1）的客户端附属模组，通过四种互不干扰的模式，让你自定义**哪些武器**可以全自动开火：

#### ✨ 功能

| 模式 | 开启方式 | 效果 |
| --- | --- | --- |
| **全局模式** | 配置中把 `GLOBAL` 设为 `true` | 所有玩家的所有 TACZ 半自动 / 点射枪全自动 |
| **NBT 模式** | 给枪的根标签加上 `{AUTOallow:1b}`（数据包 / KubeJS / 指令均可） | 只有带标签的枪全自动 |
| **药水效果模式** | 给玩家施加 `tacz_autofire:autofire` 效果 | 效果持续期间玩家全自动 |
| **属性模式** *(1.3.0 新增)* | 把玩家的 `tacz_autofire:autofire` 属性提高到 0 以上 | 属性值 > 0 期间玩家全自动 |

NBT、药水效果和属性模式之间使用或逻辑，并可在服务端配置中独立开关。`GLOBAL` 开启时会覆盖这些非全局模式；全局模式内部优先级为 GLOBAL_BLACKLIST > GLOBAL_WHITELIST > GLOBAL_EXCLUDE_AUTO。

#### 🎮 快速上手

**属性模式（1.3.0 新增）：**
```
/attribute @s tacz_autofire:autofire base set 1
```

**效果模式：**
```
/effect give @s tacz_autofire:autofire 30 0
```

**NBT 模式（示例）：**
```
/give @s tacz:ak47{AUTOallow:1b} 1
```

**配置**（首次启动后位于 `serverconfig/tacz_autofire-server.toml`）：
```toml
GLOBAL    = true   # 全局全自动
GLOBAL_BLACKLIST = ["tacz:ak47"] # 全局模式禁止这些 gunid
GLOBAL_WHITELIST = ["tacz:some_gun"] # 全局模式允许这些 gunid
NBT       = true   # 允许带 {AUTOallow:1b} 的枪
EFFECT    = true   # 允许自动开火效果
ATTRIBUTE = true   # 允许自动开火属性（值 > 0）
```

#### 📦 前置要求
- Minecraft **1.21.1**
- NeoForge **21.1.x**
- [Timeless & Classics Guns](https://www.curseforge.com/minecraft/mc-mods/timeless-and-classics-guns) **1.1.8+**（缺少将无法启动）

#### ❓ 常见问题
- **是服务端模组吗？** 配置由服务端权威下发并同步给客户端，实际扳机行为在客户端修补，因此客户端必须安装本模组（若要在服务器上强制配置，服务端也需安装）。
- **哪些枪受影响？** 所有开火模式为半自动（SEMI）或点射（BURST）的 TACZ 枪械。全自动枪械不受影响。

#### ⚖️ 许可证
保留所有权利。
