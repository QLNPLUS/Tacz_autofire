# TACZ Auto Fire — Changelog

## 1.4.0

### NeoForge 1.21.1

- Added `GLOBAL_EXCLUDE_AUTO` to prevent `GLOBAL` from making `SEMI` or `BURST` modes continuous for TACZ guns that also support the `AUTO` fire mode; native `AUTO` remains unchanged.
- Published the NeoForge 1.21.1 `1.4.0` release artifact.

### NeoForge 1.21.1（中文）

- 新增 `GLOBAL_EXCLUDE_AUTO`：当 `GLOBAL` 开启时，包含 `AUTO` 开火模式的 TACZ 多模式枪械不会被把 `SEMI` 或 `BURST` 模式改为持续开火；原生 `AUTO` 模式不受影响。
- 发布 NeoForge 1.21.1 的 `1.4.0` 版本产物。


## 1.3.0

### ✨ New Feature — Attribute Mode

Added a new **attribute-based** way to enable auto-fire: a player whose
`tacz_autofire:autofire` attribute value is **greater than 0** will fire every
eligible TACZ semi-auto / burst gun continuously while holding the trigger.

**Get started:**
```
/attribute @s tacz_autofire:autofire base set 1
```

Details:
- New custom attribute `tacz_autofire:autofire` (range 0–1, default 0), registered on `minecraft:player` so the vanilla `/attribute` command works directly.
- The attribute is **syncable**, so the client (where the trigger behavior is patched) evaluates it correctly in multiplayer.
- Attribute mode can be combined with the existing NBT tag mode and effect mode (OR logic), and is toggled independently via the new server config option:
  ```toml
  ATTRIBUTE = true
  ```
- Added attribute name translations (English & Simplified Chinese).

### 🔧 Other
- No changes to existing GLOBAL / NBT / EFFECT behaviors.
- No API or config layout breaks; the `serverconfig` file only gains the new `ATTRIBUTE` entry.

---

## 1.3.0 更新日志（中文）

### ✨ 新功能 —— 属性模式

新增基于**属性（Attribute）**的自动开火方式：玩家的 `tacz_autofire:autofire`
属性值**大于 0** 时，按住扳机即可让所有符合条件的 TACZ 半自动 / 点射枪械持续开火。

**快速开始：**
```
/attribute @s tacz_autofire:autofire base set 1
```

细节：
- 新增自定义属性 `tacz_autofire:autofire`（范围 0–1，默认 0），并注册到 `minecraft:player` 上，可直接使用原版 `/attribute` 指令。
- 属性已标记为**可同步（syncable）**，多人游戏中客户端（扳机行为在客户端修补）也能正确读取。
- 属性模式与原有的 NBT 标签模式、药水效果模式可叠加（或逻辑），并通过新的服务端配置项独立开关：
  ```toml
  ATTRIBUTE = true
  ```
- 新增属性名称翻译（英文 & 简体中文）。

### 🔧 其他
- GLOBAL / NBT / EFFECT 原有行为完全不变。
- 无破坏性改动，配置文件仅新增 `ATTRIBUTE` 条目。
