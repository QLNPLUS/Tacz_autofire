package com.qyl27.taczautofire;

import com.qyl27.taczautofire.config.AutoFireConfig;
import com.qyl27.taczautofire.effect.ModEffects;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.api.item.gun.FireMode;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

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
            return !AutoFireConfig.GLOBAL_EXCLUDE_AUTO.get()
                    || !TimelessAPI.getCommonGunIndex(gun.getGunId(stack))
                    .map(index -> index.getGunData().getFireModeSet().contains(FireMode.AUTO))
                    .orElse(false);
        }

        boolean allowedByNbt = AutoFireConfig.NBT.get()
                && stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY)
                .getUnsafe()
                .getBoolean(ALLOW_TAG);
        boolean allowedByEffect = AutoFireConfig.EFFECT.get()
                && shooter.hasEffect(ModEffects.AUTOFIRE);
        boolean allowedByAttribute = AutoFireConfig.ATTRIBUTE.get()
                && shooter.getAttributeValue(ModAttributes.AUTOFIRE) > 0.0D;
        return allowedByNbt || allowedByEffect || allowedByAttribute;
    }
}
