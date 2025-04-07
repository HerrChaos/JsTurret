package com.github.chaos.jsturret.client;

import com.github.chaos.jsturret.Jsturret;
import com.github.chaos.jsturret.blocks.blockentities.ModBlockEntities;
import com.github.chaos.jsturret.client.models.JsTurretModel;
import com.github.chaos.jsturret.client.renderers.JsTurretBlockEntityRenderer;
import com.github.chaos.jsturret.client.renderers.JsTurretItemRenderer;
import com.github.chaos.jsturret.networking.ModNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;

public class JsturretClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(JsTurretBlockEntityRenderer.JS_TURRET_MODEL_LAYER, JsTurretModel::getTexturedModelData);

        BlockEntityRendererFactories.register(ModBlockEntities.JS_TURRET_BLOCK_ENTITY, JsTurretBlockEntityRenderer::new);

        SpecialModelTypes.ID_MAPPER.put(Jsturret.id("jsturret"), JsTurretItemRenderer.Unbaked.CODEC);

        ModNetworking.cInit();
    }
}
