package com.qyl27.taczautofire.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class AutoFireConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue GLOBAL;
    public static final ForgeConfigSpec.BooleanValue GLOBAL_EXCLUDE_AUTO;
    public static final ForgeConfigSpec.BooleanValue NBT;
    public static final ForgeConfigSpec.BooleanValue EFFECT;
    public static final ForgeConfigSpec.BooleanValue ATTRIBUTE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment(
                "Server-authoritative policy for TACZ semi-automatic and burst weapons.",
                "Forge synchronizes this value to clients when they join the world.",
                "When GLOBAL is true, every eligible TACZ gun can fire continuously and the other settings are ignored,",
                "unless GLOBAL_EXCLUDE_AUTO excludes guns that support the AUTO fire mode."
        );
        GLOBAL = builder.comment("Allow all TACZ semi-automatic and burst weapons.")
                .define("GLOBAL", true);
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
