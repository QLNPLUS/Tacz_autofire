package com.qyl27.taczautofire;

import com.qyl27.taczautofire.config.AutoFireConfig;
import com.qyl27.taczautofire.effect.ModEffects;
import com.qyl27.taczautofire.network.NetworkHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(TaczAutoFire.MOD_ID)
public final class TaczAutoFire {
    public static final String MOD_ID = "tacz_autofire";

    public TaczAutoFire() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEffects.register(modEventBus);
        ModAttributes.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, AutoFireConfig.SPEC);
        NetworkHandler.init();
    }
}
