package com.github.yajatkaul.mega_showdown.networking.client.packet;

import com.github.yajatkaul.mega_showdown.MegaShowdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record InteractionWheelPacket(
        boolean shouldMega,
        boolean shouldUltra,
        boolean canMega,
        boolean canUltra,
        boolean shouldPrimal,
        boolean shouldCrown,
        boolean canPrimal,
        boolean canCrown
) implements CustomPacketPayload {

    public static final ResourceLocation PACKET_ID =
            ResourceLocation.fromNamespaceAndPath(
                    MegaShowdown.MOD_ID,
                    "interaction_wheel_packet"
            );

    public static final Type<InteractionWheelPacket> TYPE =
            new Type<>(PACKET_ID);

    public static final StreamCodec<ByteBuf, InteractionWheelPacket> STREAM_CODEC =
            new StreamCodec<>() {
                @Override
                public @NotNull InteractionWheelPacket decode(ByteBuf buf) {
                    byte flags = buf.readByte();

                    return new InteractionWheelPacket(
                            (flags & (1)) != 0,
                            (flags & (1 << 1)) != 0,
                            (flags & (1 << 2)) != 0,
                            (flags & (1 << 3)) != 0,
                            (flags & (1 << 4)) != 0,
                            (flags & (1 << 5)) != 0,
                            (flags & (1 << 6)) != 0,
                            (flags & (1 << 7)) != 0
                    );
                }

                @Override
                public void encode(ByteBuf buf, InteractionWheelPacket packet) {
                    int flags = 0;

                    if (packet.shouldMega()) flags |= 1;
                    if (packet.shouldUltra()) flags |= 1 << 1;
                    if (packet.canMega()) flags |= 1 << 2;
                    if (packet.canUltra()) flags |= 1 << 3;
                    if (packet.shouldPrimal()) flags |= 1 << 4;
                    if (packet.shouldCrown()) flags |= 1 << 5;
                    if (packet.canPrimal()) flags |= 1 << 6;
                    if (packet.canCrown()) flags |= 1 << 7;

                    buf.writeByte(flags);
                }
            };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}