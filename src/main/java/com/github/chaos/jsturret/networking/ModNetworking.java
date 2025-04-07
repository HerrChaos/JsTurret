package com.github.chaos.jsturret.networking;

import com.github.chaos.jsturret.blocks.blockentities.custom.JsTurretBlockEntity;
import com.github.chaos.jsturret.networking.packets.JsTurretLoadPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

public class ModNetworking {
    public static void cInit() {
        ClientPlayNetworking.registerGlobalReceiver(JsTurretLoadPacket.ID, (payload, context) -> {
            MinecraftClient client = context.client();
            ClientWorld world = client.world;

            if (world == null) {
                return;
            }

            String code = payload.code();
            BlockPos pos = payload.pos();

            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof JsTurretBlockEntity jsTurretBlockEntity) {
                jsTurretBlockEntity.setJsCode(code);
            }
        });
    }

    public static void init() {
        PayloadTypeRegistry.playS2C().register(JsTurretLoadPacket.ID, JsTurretLoadPacket.CODEC);
    }


}
