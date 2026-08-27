package com.qyl27.taczautofire.network;

import com.qyl27.taczautofire.TaczAutoFire;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";

    private NetworkHandler() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(NetworkHandler::registerPayloads);
    }

    private static void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar(PROTOCOL_VERSION)
                .playBidirectional(
                        ProtocolPayload.TYPE,
                        ProtocolPayload.STREAM_CODEC,
                        (payload, context) -> {
                        }
                );
    }

    private record ProtocolPayload() implements CustomPacketPayload {
        private static final Type<ProtocolPayload> TYPE = new Type<>(
                ResourceLocation.fromNamespaceAndPath(TaczAutoFire.MOD_ID, "protocol")
        );
        private static final StreamCodec<RegistryFriendlyByteBuf, ProtocolPayload> STREAM_CODEC =
                StreamCodec.unit(new ProtocolPayload());

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
