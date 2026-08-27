package com.qyl27.taczautofire.effect;

import com.qyl27.taczautofire.TaczAutoFire;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEffects {
    private static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TaczAutoFire.MOD_ID);

    public static final RegistryObject<MobEffect> AUTOFIRE = EFFECTS.register(
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
