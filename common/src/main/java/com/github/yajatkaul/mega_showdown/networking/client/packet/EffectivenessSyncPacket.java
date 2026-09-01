package com.github.yajatkaul.mega_showdown.networking.client.packet;

import com.github.yajatkaul.mega_showdown.MegaShowdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public record EffectivenessSyncPacket(Map<String, Map<String, Double>> typeChart) implements CustomPacketPayload {
    public static final ResourceLocation PACKET_ID = ResourceLocation.fromNamespaceAndPath(MegaShowdown.MOD_ID, "effectiveness_sync");
    public static final Type<EffectivenessSyncPacket> TYPE = new Type<>(PACKET_ID);

    // Codec for the inner Map<String, Double>
    private static final StreamCodec<ByteBuf, Map<String, Double>> INNER_MAP_CODEC =
            ByteBufCodecs.map(
                    HashMap::new,
                    ByteBufCodecs.STRING_UTF8,
                    ByteBufCodecs.DOUBLE
            );

    // Codec for the outer Map<String, Map<String, Double>>
    private static final StreamCodec<ByteBuf, Map<String, Map<String, Double>>> OUTER_MAP_CODEC =
            ByteBufCodecs.map(
                    HashMap::new,
                    ByteBufCodecs.STRING_UTF8,
                    INNER_MAP_CODEC
            );

    public static final StreamCodec<ByteBuf, EffectivenessSyncPacket> STREAM_CODEC = StreamCodec.composite(
            OUTER_MAP_CODEC,
            EffectivenessSyncPacket::typeChart,
            EffectivenessSyncPacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}