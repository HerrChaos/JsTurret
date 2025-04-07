package com.github.chaos.jsturret.networking.packets;

import com.github.chaos.jsturret.Jsturret;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record JsTurretLoadPacket(String code, BlockPos pos) implements CustomPayload {
    public static final Identifier JS_TURRET_LOAD_PACKET_ID = Jsturret.id("js_turret_load");
    public static final CustomPayload.Id<JsTurretLoadPacket> ID = new CustomPayload.Id<>(JS_TURRET_LOAD_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, JsTurretLoadPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, JsTurretLoadPacket::code,
            BlockPos.PACKET_CODEC, JsTurretLoadPacket::pos,
            JsTurretLoadPacket::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
