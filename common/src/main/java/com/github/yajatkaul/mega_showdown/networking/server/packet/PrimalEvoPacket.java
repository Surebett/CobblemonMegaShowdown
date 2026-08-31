package com.github.yajatkaul.mega_showdown.networking.server.packet;

import com.github.yajatkaul.mega_showdown.MegaShowdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record PrimalEvoPacket(UUID pokemonId) implements CustomPacketPayload {
    public static final ResourceLocation PACKET_ID = ResourceLocation.fromNamespaceAndPath(MegaShowdown.MOD_ID, "primal_evo");
    public static final CustomPacketPayload.Type<PrimalEvoPacket> TYPE = new CustomPacketPayload.Type<>(PACKET_ID);

    public static final StreamCodec<ByteBuf, PrimalEvoPacket> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC,
            PrimalEvoPacket::pokemonId,
            PrimalEvoPacket::new
    );

    @Override
    public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
