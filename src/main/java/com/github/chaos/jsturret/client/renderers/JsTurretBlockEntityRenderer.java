package com.github.chaos.jsturret.client.renderers;

import com.github.chaos.jsturret.Jsturret;
import com.github.chaos.jsturret.blocks.blockentities.custom.JsTurretBlockEntity;
import com.github.chaos.jsturret.client.models.JsTurretModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

public class JsTurretBlockEntityRenderer implements BlockEntityRenderer<JsTurretBlockEntity> {
    public static EntityModelLayer JS_TURRET_MODEL_LAYER = new EntityModelLayer(Jsturret.id("jsturret"), "main");

    private JsTurretModel jsTurretModel;

    public double age = 0;
    public double startTick = 0;

    public JsTurretBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        this(context.getLoadedEntityModels());
    }

    public JsTurretBlockEntityRenderer(LoadedEntityModels models) {
        this.jsTurretModel = new JsTurretModel(models.getModelPart(JS_TURRET_MODEL_LAYER));
    }

    @Override
    public void render(JsTurretBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        matrices.push();

        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(new Quaternionf(1, 0, 0, 0));

        if (entity.justShot()) {
            this.startTick = entity.age + 20;
        }

        if (this.startTick == entity.age + 1) {
            jsTurretModel.getPart("gunhead").get().originZ = -11.77F + 2.5F;
            jsTurretModel.getPart("cog").get().setAngles(-57.5F, 0, 0);
        } else {
            jsTurretModel.getPart("gunhead").get().originZ = -11.77F;
            jsTurretModel.getPart("cog").get().setAngles(0, 0, 0);
        }

        this.jsTurretModel.getPart("body").get().setAngles(0, (float) Math.toRadians(entity.getYaw()), 0);

        this.jsTurretModel.getPart("gun").get().setAngles((float) Math.toRadians(entity.getPitch()), 0, 0);

        this.jsTurretModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(Jsturret.id("textures/entity/jsturret/jsturret.png"))), light, overlay);

        matrices.pop();
    }

    public void renderItem(ItemDisplayContext displayContext, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, boolean glint) {
        matrices.push();

        matrices.translate(0.5, 1.5, 0.5);
        matrices.multiply(new Quaternionf(1, 0, 0, 0));

        this.jsTurretModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutout(Jsturret.id("textures/entity/jsturret/jsturret.png"))), light, overlay);

        matrices.pop();
    }
}
