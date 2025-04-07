package com.github.chaos.jsturret.client.models;

import com.github.chaos.jsturret.blocks.blockentities.custom.JsTurretBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.12.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class JsTurret extends EntityModel<JsTurretBlockEntity> {
	private final ModelPart base;
	private final ModelPart foot;
	private final ModelPart foot2;
	private final ModelPart foot3;
	private final ModelPart centerPole;
	private final ModelPart body;
	private final ModelPart gun;

	protected JsTurret(ModelPart root) {
		super(root);
		this.base = root.getChild("base");
		this.foot = this.base.getChild("foot");
		this.foot2 = this.base.getChild("foot2");
		this.foot3 = this.base.getChild("foot3");
		this.centerPole = this.base.getChild("centerPole");
		this.body = root.getChild("body");
		this.gun = this.body.getChild("gun");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = base.addChild("cube_r1", ModelPartBuilder.create().uv(-12, -6).cuboid(-7.0F, -2.0F, -4.0F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r2 = base.addChild("cube_r2", ModelPartBuilder.create().uv(-12, -6).cuboid(-7.0F, -2.0F, -4.0F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		ModelPartData cube_r3 = base.addChild("cube_r3", ModelPartBuilder.create().uv(-12, -6).cuboid(-7.0F, -2.0F, -4.0F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		ModelPartData foot = base.addChild("foot", ModelPartBuilder.create().uv(-5, -3).cuboid(-3.0F, -1.0F, -10.0F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.5F, 0.0F));

		ModelPartData cube_r4 = foot.addChild("cube_r4", ModelPartBuilder.create().uv(-4, -3).cuboid(-2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(-4, -3).cuboid(-7.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(-5, -4).cuboid(-6.0F, -2.0F, -6.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -8.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData foot2 = base.addChild("foot2", ModelPartBuilder.create().uv(-5, -3).cuboid(-3.0F, -1.0F, -10.0F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 2.0944F, 0.0F));

		ModelPartData cube_r5 = foot2.addChild("cube_r5", ModelPartBuilder.create().uv(-4, -3).cuboid(-2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(-4, -3).cuboid(-7.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(-5, -4).cuboid(-6.0F, -2.0F, -6.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -8.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData foot3 = base.addChild("foot3", ModelPartBuilder.create().uv(-5, -3).cuboid(-3.0F, -1.0F, -10.0F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, -2.0944F, 0.0F));

		ModelPartData cube_r6 = foot3.addChild("cube_r6", ModelPartBuilder.create().uv(-4, -3).cuboid(-2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(-4, -3).cuboid(-7.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(-5, -4).cuboid(-6.0F, -2.0F, -6.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -8.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData centerPole = base.addChild("centerPole", ModelPartBuilder.create().uv(-2, 0).cuboid(-3.0F, -5.0F, -1.0F, 6.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(-3, -2).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(-0.2F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r7 = centerPole.addChild("cube_r7", ModelPartBuilder.create().uv(-2, 0).cuboid(-3.0F, -3.0F, -1.0F, 6.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r8 = centerPole.addChild("cube_r8", ModelPartBuilder.create().uv(-3, -1).cuboid(-3.0F, -3.0F, -1.5F, 6.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		ModelPartData cube_r9 = centerPole.addChild("cube_r9", ModelPartBuilder.create().uv(-3, -1).cuboid(-3.0F, -3.0F, -1.5F, 6.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(-4, -2).cuboid(-3.0F, -6.75F, -2.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(1, -2).cuboid(2.0F, -10.75F, -2.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(1, -2).cuboid(-4.0F, -10.75F, -2.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(2.0F, -13.75F, 1.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(-4.0F, -13.75F, 1.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(3, 0).cuboid(2.0F, -11.25F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(2.0F, -12.5F, 0.25F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(2.0F, -11.75F, -0.25F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 0).cuboid(1.8F, -12.0F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 0).cuboid(-2.8F, -12.0F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(-4.0F, -12.5F, 0.25F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(-4.0F, -11.75F, -0.25F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(3, 0).cuboid(-4.0F, -11.25F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r10 = body.addChild("cube_r10", ModelPartBuilder.create().uv(4, 1).cuboid(-1.0F, -1.45F, -2.82F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(-1.0F, -1.2F, -2.82F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(5.0F, -1.45F, -2.82F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 1).cuboid(5.0F, -1.2F, -2.82F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -10.75F, 2.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData gun = body.addChild("gun", ModelPartBuilder.create().uv(4, 1).cuboid(-2.0F, -11.5F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(2, -2).cuboid(-1.5F, -12.5F, -2.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F))
		.uv(1, -3).cuboid(-1.5F, -9.5F, -2.0F, 3.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(3, -1).cuboid(-1.5F, -12.5F, 6.5F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(-1, -5).cuboid(-1.5F, -10.5F, 2.0F, 3.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(5, 1).cuboid(-1.5F, -11.0F, 1.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(5, 1).cuboid(-1.5F, -11.0F, 6.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 0).cuboid(-1.5F, -12.0F, 3.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.2F))
		.uv(2, -2).cuboid(-1.5F, -12.5F, -5.75F, 3.0F, 3.0F, 4.0F, new Dilation(-0.2F))
		.uv(5, 0).cuboid(-0.5F, -12.5F, -7.35F, 1.0F, 1.0F, 2.0F, new Dilation(-0.2F))
		.uv(5, 0).cuboid(-1.0F, -10.5F, -7.35F, 2.0F, 1.0F, 2.0F, new Dilation(-0.2F))
		.uv(6, 0).cuboid(0.5F, -12.0F, -7.35F, 1.0F, 2.0F, 2.0F, new Dilation(-0.2F))
		.uv(6, 0).cuboid(-1.5F, -12.0F, -7.35F, 1.0F, 2.0F, 2.0F, new Dilation(-0.2F))
		.uv(-10, -14).cuboid(-1.0F, -12.0F, -25.75F, 2.0F, 2.0F, 16.0F, new Dilation(-0.5F))
		.uv(2, -2).cuboid(-1.0F, -12.0F, -28.75F, 2.0F, 2.0F, 4.0F, new Dilation(-0.4F))
		.uv(2, -2).cuboid(-1.0F, -12.0F, -9.0F, 2.0F, 2.0F, 4.0F, new Dilation(-0.2F))
		.uv(3, -1).cuboid(-1.0F, -12.0F, -11.35F, 2.0F, 2.0F, 3.0F, new Dilation(-0.4F))
		.uv(5, 0).cuboid(-0.5F, -10.0F, -4.25F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}
	@Override
	public void setAngles(JsTurretBlockEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}