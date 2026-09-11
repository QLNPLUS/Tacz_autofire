package com.qyl27.taczautofire.config;

import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class AutoFireConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue GLOBAL;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> GLOBAL_BLACKLIST;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> GLOBAL_WHITELIST;
    public static final ModConfigSpec.BooleanValue GLOBAL_EXCLUDE_AUTO;
    public static final ModConfigSpec.BooleanValue NBT;
    public static final ModConfigSpec.BooleanValue EFFECT;
    public static final ModConfigSpec.BooleanValue ATTRIBUTE;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment(
                "Server-authoritative policy for TACZ semi-automatic and burst weapons.",
                "NeoForge synchronizes this value to clients when they join the world.",
                "When GLOBAL is true, every eligible TACZ gun can fire continuously and the other settings are ignored,",
                "unless the global blacklist, whitelist, or GLOBAL_EXCLUDE_AUTO rules change the result.",
                "Global rule priority is: blacklist, then whitelist, then GLOBAL_EXCLUDE_AUTO."
        );
        GLOBAL = builder.comment("Allow all TACZ semi-automatic and burst weapons.")
                .define("GLOBAL", true);
        GLOBAL_BLACKLIST = builder.comment(
                        "Gun IDs denied when GLOBAL is true. This has the highest priority.",
                        "Use complete TACZ IDs such as tacz:ak47. If an ID is in both lists, the blacklist wins.")
                .defineList("GLOBAL_BLACKLIST", List.of(),
                        entry -> entry instanceof String
                                && ((String) entry).indexOf(':') > 0
                                && ResourceLocation.tryParse((String) entry) != null);
        GLOBAL_WHITELIST = builder.comment(
                        "Gun IDs allowed when GLOBAL is true, before GLOBAL_EXCLUDE_AUTO is evaluated.",
                        "Use complete TACZ IDs such as tacz:ak47. These entries override GLOBAL_EXCLUDE_AUTO.")
                .defineList("GLOBAL_WHITELIST", List.of(),
                        entry -> entry instanceof String
                                && ((String) entry).indexOf(':') > 0
                                && ResourceLocation.tryParse((String) entry) != null);
        GLOBAL_EXCLUDE_AUTO = builder.comment(
                        "When GLOBAL is true, exclude TACZ guns whose configured fire mode list contains AUTO.",
                        "This only prevents GLOBAL from making their SEMI or BURST modes continuous; native AUTO remains unchanged.")
                .define("GLOBAL_EXCLUDE_AUTO", true);
        NBT = builder.comment("Allow weapons whose root ItemStack tag contains {AUTOallow:1b}.")
                .define("NBT", true);
        EFFECT = builder.comment("Allow players carrying the tacz_autofire:autofire effect.")
                .define("EFFECT", true);
        ATTRIBUTE = builder.comment(
                        "Allow players whose tacz_autofire:autofire attribute value is greater than zero.",
                        "The attribute is syncable so the client can evaluate it, e.g. set via /attribute.")
                .define("ATTRIBUTE", true);
        SPEC = builder.build();
    }

    private AutoFireConfig() {
    }
}
