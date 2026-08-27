package com.qyl27.taczautofire.effect;

import com.qyl27.taczautofire.TaczAutoFire;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {
    private static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TaczAutoFire.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> AUTOFIRE = EFFECTS.register(
            "autofire",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xD64A3A) {
            }
    );

    private ModEffects() {
    }

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
