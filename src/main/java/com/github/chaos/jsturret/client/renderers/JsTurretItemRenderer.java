package com.github.chaos.jsturret.client.renderers;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.model.special.SimpleSpecialModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;

public class JsTurretItemRenderer implements SimpleSpecialModelRenderer {
    private JsTurretBlockEntityRenderer blockEntityRenderer;

    public JsTurretItemRenderer(JsTurretBlockEntityRenderer blockEntityRenderer) {
        this.blockEntityRenderer = blockEntityRenderer;
    }

    @Override
    public void render(ItemDisplayContext displayContext, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, boolean glint) {

        this.blockEntityRenderer.renderItem(displayContext, matrices, vertexConsumers, light, overlay, glint);
    }

    @Environment(EnvType.CLIENT)
    public static record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final JsTurretItemRenderer.Unbaked INSTANCE = new JsTurretItemRenderer.Unbaked();
        public static final MapCodec<JsTurretItemRenderer.Unbaked> CODEC = MapCodec.unit(INSTANCE);

        public MapCodec<JsTurretItemRenderer.Unbaked> getCodec() {
            return CODEC;
        }

        public SpecialModelRenderer<?> bake(LoadedEntityModels entityModels) {
            return new JsTurretItemRenderer(new JsTurretBlockEntityRenderer(entityModels));
        }
    }
}
