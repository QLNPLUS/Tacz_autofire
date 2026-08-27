package com.qyl27.taczautofire;

import com.qyl27.taczautofire.config.AutoFireConfig;
import com.qyl27.taczautofire.effect.ModEffects;
import com.qyl27.taczautofire.network.NetworkHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(TaczAutoFire.MOD_ID)
public final class TaczAutoFire {
    public static final String MOD_ID = "tacz_autofire";

    public TaczAutoFire(IEventBus modEventBus, ModContainer modContainer) {
        ModEffects.register(modEventBus);
        ModAttributes.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, AutoFireConfig.SPEC);
        NetworkHandler.register(modEventBus);
    }
}
