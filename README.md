# TACZ Auto Fire

A Forge 1.20.1 addon for TACZ that must be installed on both the server and every client. It lets semi-automatic and burst fire modes continue firing while the shoot key is held. TACZ still controls cooldowns, RPM modifiers, burst timing, animations, recoil, ammo use, and server-side validation.

## Configuration

Forge creates the server-authoritative configuration after the world first starts:

```text
<world>/serverconfig/tacz_autofire-server.toml
```

For a modpack-wide default, place a prepared copy at `defaultconfigs/tacz_autofire-server.toml` before creating a world. Forge synchronizes the active server value to clients when they join; a normal client cannot select a different mode with a local configuration file.

```toml
GLOBAL = true
NBT = true
EFFECT = true
```

- `GLOBAL`: allow every TACZ gun in `SEMI` or `BURST` mode. When enabled, `NBT` and `EFFECT` do not restrict the result.
- `NBT`: allow gun stacks whose root tag contains `AUTOallow:1b`.
- `EFFECT`: allow players carrying the `tacz_autofire:autofire` effect.

When `GLOBAL` is disabled, the enabled `NBT` and `EFFECT` conditions work independently and are combined with OR logic. Enabling both allows either condition to grant continuous fire.

To opt in an existing gun with vanilla commands, drop it on the ground and run:

```mcfunction
/data modify entity @e[type=minecraft:item,sort=nearest,limit=1] Item.tag.AUTOallow set value 1b
```

Remove the opt-in tag:

```mcfunction
/data remove entity @e[type=minecraft:item,sort=nearest,limit=1] Item.tag.AUTOallow
```

Pick the gun back up afterward. Modpack scripts can set the same root `AUTOallow` byte/boolean tag directly on the gun `ItemStack`.

Example effect command:

```mcfunction
/effect give @s tacz_autofire:autofire infinite
```

Native automatic weapons and TACZ burst weapons already marked as continuously firing retain their original behavior in either mode.

The addon uses a required versioned network channel, so clients missing the addon or using an incompatible protocol cannot join. This establishes server control for normal modded clients, but it is not a replacement for anti-cheat against a deliberately modified client or an external click macro.

## Development

The default `tacz_jar` in `gradle.properties` points to the adjacent local TACZ build:

```text
../TACZ-1.20.1/build/libs/tacz-1.20.1-1.1.8-hotfix.jar
```

Change that property when using a different development layout.
