package com.qyl27.taczautofire;

import com.qyl27.taczautofire.config.AutoFireConfig;
import com.qyl27.taczautofire.effect.ModEffects;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.item.gun.FireMode;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class AutoFireEligibility {
    public static final String ALLOW_TAG = "AUTOallow";

    private AutoFireEligibility() {
    }

    public static boolean allowsContinuousFire(LivingEntity shooter, ItemStack stack) {
        if (!(stack.getItem() instanceof IGun gun)) {
            return false;
        }

        FireMode fireMode = gun.getFireMode(stack);
        if (fireMode != FireMode.SEMI && fireMode != FireMode.BURST) {
            return false;
        }

        if (AutoFireConfig.GLOBAL.get()) {
            return true;
        }

        boolean allowedByNbt = AutoFireConfig.NBT.get()
                && stack.hasTag()
                && stack.getTag().getBoolean(ALLOW_TAG);
        boolean allowedByEffect = AutoFireConfig.EFFECT.get()
                && shooter.hasEffect(ModEffects.AUTOFIRE.get());
        boolean allowedByAttribute = AutoFireConfig.ATTRIBUTE.get()
                && shooter.getAttributeValue(ModAttributes.AUTOFIRE.get()) > 0.0D;
        return allowedByNbt || allowedByEffect || allowedByAttribute;
    }
}
