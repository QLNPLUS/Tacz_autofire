package com.qyl27.taczautofire;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, TaczAutoFire.MOD_ID);

    /**
     * While a player's value of this attribute is greater than zero, every eligible
     * TACZ semi-automatic and burst weapon can fire continuously.
     * <p>
     * The default value (0) is intentionally not exposed to living entities by default;
     * mods or command {@code /attribute} can raise it to grant auto-fire.
     */
    public static final DeferredHolder<Attribute, Attribute> AUTOFIRE = ATTRIBUTES.register(
            "autofire",
            () -> new RangedAttribute(
                    "attribute." + TaczAutoFire.MOD_ID + ".name.autofire",
                    0.0D,
                    0.0D,
                    1.0D
            ).setSyncable(true)
    );

    private ModAttributes() {
    }

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
        eventBus.addListener(ModAttributes::addAttributeToPlayer);
    }

    private static void addAttributeToPlayer(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, AUTOFIRE);
    }
}
