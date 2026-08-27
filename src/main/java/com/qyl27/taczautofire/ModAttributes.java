package com.qyl27.taczautofire;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, TaczAutoFire.MOD_ID);

    /**
     * While a player's value of this attribute is greater than zero, every eligible
     * TACZ semi-automatic and burst weapon can fire continuously.
     * <p>
     * The default value (0) is intentionally not exposed to living entities by default;
     * mods or command {@code /attribute} can raise it to grant auto-fire.
     */
    public static final RegistryObject<Attribute> AUTOFIRE = ATTRIBUTES.register(
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
        event.add(EntityType.PLAYER, AUTOFIRE.get());
    }
}
