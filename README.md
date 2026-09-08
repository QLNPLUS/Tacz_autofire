# TACZ Auto Fire

A NeoForge 1.21.1 addon for TACZ that must be installed on both the server and every client. It lets semi-automatic and burst fire modes continue firing while the shoot key is held. TACZ still controls cooldowns, RPM modifiers, burst timing, animations, recoil, ammo use, and server-side validation.

## Configuration

NeoForge creates the server-authoritative configuration after the world first starts:

```text
<world>/serverconfig/tacz_autofire-server.toml
```

For a modpack-wide default, place a prepared copy at `defaultconfigs/tacz_autofire-server.toml` before creating a world. NeoForge synchronizes the active server value to clients when they join; a normal client cannot select a different mode with a local configuration file.

```toml
GLOBAL = true
GLOBAL_EXCLUDE_AUTO = true
NBT = true
EFFECT = true
ATTRIBUTE = true
```

- `GLOBAL`: allow eligible TACZ guns in `SEMI` or `BURST` mode to fire continuously. When enabled, `NBT`, `EFFECT`, and `ATTRIBUTE` do not restrict the result.
- `GLOBAL_EXCLUDE_AUTO`: when `GLOBAL` is enabled, exclude guns whose TACZ data contains the `AUTO` fire mode. This prevents multi-mode guns from getting continuous fire in `SEMI` or `BURST`; their native `AUTO` mode is unchanged.
- `NBT`: allow gun stacks whose `minecraft:custom_data` component contains `AUTOallow:1b`.
- `EFFECT`: allow players carrying the `tacz_autofire:autofire` effect.
- `ATTRIBUTE`: allow players whose `tacz_autofire:autofire` attribute value is greater than zero.

When `GLOBAL` is disabled, the enabled `NBT`, `EFFECT`, and `ATTRIBUTE` conditions work independently and are combined with OR logic. Enabling multiple conditions allows any of them to grant continuous fire.

To opt in an existing gun with vanilla commands, drop it on the ground and run:

```mcfunction
/data modify entity @e[type=minecraft:item,sort=nearest,limit=1] Item.components."minecraft:custom_data".AUTOallow set value 1b
```

Remove the opt-in tag:

```mcfunction
/data remove entity @e[type=minecraft:item,sort=nearest,limit=1] Item.components."minecraft:custom_data".AUTOallow
```

Pick the gun back up afterward. Modpack scripts can set the same `minecraft:custom_data` component directly on the gun `ItemStack`.

Example effect command:

```mcfunction
/effect give @s tacz_autofire:autofire infinite
```

Native automatic weapons and TACZ burst weapons already marked as continuously firing retain their original behavior in either mode.

The addon uses a required versioned NeoForge payload channel, so clients missing the addon or using an incompatible protocol cannot join. This establishes server control for normal modded clients, but it is not a replacement for anti-cheat against a deliberately modified client or an external click macro.

## Development

The default `tacz_jar` in `gradle.properties` points to the local TACZ dependency:

```text
libs/tacz-neoforge-1.21.1-1.1.8-hotfix-r6.jar
```

The dependency JAR is intentionally ignored by Git. Download `tacz-neoforge-1.21.1-1.1.8-hotfix-r6.jar` and place it at that path, or pass a different path with `-Ptacz_jar=<path>`.
