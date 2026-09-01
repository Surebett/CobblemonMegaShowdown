package com.github.yajatkaul.mega_showdown.networking.client.handler;

import com.github.yajatkaul.mega_showdown.networking.client.packet.EffectivenessSyncPacket;
import com.github.yajatkaul.mega_showdown.utils.TypeEffectivenessUtils;
import dev.architectury.networking.NetworkManager;

public class EffectivenessSyncHandler {
    public static void handle(EffectivenessSyncPacket packet, NetworkManager.PacketContext context) {
        TypeEffectivenessUtils.typeChartMap = packet.typeChart();
    }
}
