package com.qyl27.taczautofire.mixin;

import com.qyl27.taczautofire.AutoFireEligibility;
import com.tacz.guns.client.input.ShootKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ShootKey.class, remap = false)
public abstract class ShootKeyMixin {
    @ModifyVariable(
            method = "autoShoot",
            at = @At(value = "STORE"),
            name = "canContinuouslyShoot",
            require = 1
    )
    private static boolean taczAutoFire$allowConfiguredWeapons(boolean original) {
        if (original) {
            return true;
        }

        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && AutoFireEligibility.allowsContinuousFire(player, player.getMainHandItem());
    }
}
