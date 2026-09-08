package com.qyl27.taczautofire.mixin;

import com.qyl27.taczautofire.AutoFireEligibility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Optional compatibility for TaCZ Dual Wield on Forge 1.20.1.
 *
 * Dual Wield replaces TaCZ's ShootKey loop in dual mode and evaluates both
 * hands through its own canContinuouslyShoot method, so the normal ShootKey
 * mixin cannot grant auto-fire there.
 */
@Pseudo
@Mixin(targets = "dev.sistermole.taczdualwield.client.DualWieldClient", remap = false)
public abstract class DualWieldClientMixin {
    @Inject(
            method = "canContinuouslyShoot",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private static void taczAutoFire$allowConfiguredWeapons(
            ItemStack stack,
            CallbackInfoReturnable<Boolean> callback
    ) {
        if (callback.getReturnValueZ()) {
            return;
        }

        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && AutoFireEligibility.allowsContinuousFire(player, stack)) {
            callback.setReturnValue(true);
        }
    }
}
