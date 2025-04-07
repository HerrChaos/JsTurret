package com.github.chaos.jsturret.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.AnimationState;

public class JsTurretModel extends Model {
	private final ModelPart base;
	private final ModelPart foot;
	private final ModelPart foot2;
	private final ModelPart foot3;
	private final ModelPart centerPole;
	private final ModelPart body;
	private final ModelPart gun;

	public final AnimationState animationState = new AnimationState();

	public JsTurretModel(ModelPart root) {
		super(root, RenderLayer::getEntityCutout);
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

		ModelPartData cube_r1 = base.addChild("cube_r1", ModelPartBuilder.create().uv(0, 10).cuboid(-7.0F, -2.0F, -4.0F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r2 = base.addChild("cube_r2", ModelPartBuilder.create().uv(0, 20).cuboid(-7.0F, -2.0F, -4.0F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		ModelPartData cube_r3 = base.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -2.0F, -4.0F, 14.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		ModelPartData foot = base.addChild("foot", ModelPartBuilder.create().uv(36, 38).cuboid(-3.0F, -1.0F, -10.0F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.5F, 0.0F));

		ModelPartData cube_r4 = foot.addChild("cube_r4", ModelPartBuilder.create().uv(56, 60).cuboid(-2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(0, 60).cuboid(-7.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(36, 30).cuboid(-6.0F, -2.0F, -6.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -8.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData foot2 = base.addChild("foot2", ModelPartBuilder.create().uv(44, 8).cuboid(-3.0F, -1.0F, -10.0F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 2.0944F, 0.0F));

		ModelPartData cube_r5 = foot2.addChild("cube_r5", ModelPartBuilder.create().uv(64, 14).cuboid(-2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(64, 0).cuboid(-7.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(44, 0).cuboid(-6.0F, -2.0F, -6.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -8.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData foot3 = base.addChild("foot3", ModelPartBuilder.create().uv(44, 22).cuboid(-3.0F, -1.0F, -10.0F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, -2.0944F, 0.0F));

		ModelPartData cube_r6 = foot3.addChild("cube_r6", ModelPartBuilder.create().uv(24, 65).cuboid(-2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(12, 65).cuboid(-7.0F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(44, 14).cuboid(-6.0F, -2.0F, -6.0F, 4.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -8.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData centerPole = base.addChild("centerPole", ModelPartBuilder.create().uv(56, 50).cuboid(-3.0F, -5.0F, -1.0F, 6.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(56, 28).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(-0.2F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r7 = centerPole.addChild("cube_r7", ModelPartBuilder.create().uv(56, 55).cuboid(-3.0F, -3.0F, -1.0F, 6.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r8 = centerPole.addChild("cube_r8", ModelPartBuilder.create().uv(38, 52).cuboid(-3.0F, -3.0F, -1.5F, 6.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		ModelPartData cube_r9 = centerPole.addChild("cube_r9", ModelPartBuilder.create().uv(20, 52).cuboid(-3.0F, -3.0F, -1.5F, 6.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 48).cuboid(-3.0F, -6.75F, -2.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(58, 34).cuboid(2.0F, -10.75F, -2.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(44, 58).cuboid(-4.0F, -10.75F, -2.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(58, 71).cuboid(2.0F, -13.75F, 1.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(64, 71).cuboid(-4.0F, -13.75F, 1.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(68, 65).cuboid(2.0F, -11.25F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
				.uv(18, 72).cuboid(2.0F, -12.5F, 0.25F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(64, 42).cuboid(2.0F, -11.75F, -0.25F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(70, 71).cuboid(1.8F, -12.0F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 72).cuboid(-2.8F, -12.0F, -0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(24, 72).cuboid(-4.0F, -12.5F, 0.25F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(66, 12).cuboid(-4.0F, -11.75F, -0.25F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(68, 68).cuboid(-4.0F, -11.25F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r10 = body.addChild("cube_r10", ModelPartBuilder.create().uv(70, 42).cuboid(-1.0F, -1.45F, -2.82F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(70, 34).cuboid(-1.0F, -1.2F, -2.82F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
				.uv(58, 42).cuboid(5.0F, -1.45F, -2.82F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(68, 60).cuboid(5.0F, -1.2F, -2.82F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -10.75F, 2.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData gun = body.addChild("gun", ModelPartBuilder.create().uv(44, 28).cuboid(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(16, 58).cuboid(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 53).cuboid(-1.5F, 1.75F, -2.5F, 3.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(56, 44).cuboid(-1.5F, 1.25F, -2.5F, 3.0F, 1.0F, 5.0F, new Dilation(-0.2F))
				.uv(0, 67).cuboid(-1.5F, -1.5F, 6.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(36, 44).cuboid(-1.5F, 0.5F, 1.5F, 3.0F, 1.0F, 7.0F, new Dilation(0.0F))
				.uv(30, 58).cuboid(-1.5F, -1.5F, -6.25F, 3.0F, 3.0F, 4.0F, new Dilation(-0.2F))
				.uv(30, 72).cuboid(-0.5F, -1.5F, -7.85F, 1.0F, 1.0F, 2.0F, new Dilation(-0.2F))
				.uv(70, 39).cuboid(-1.0F, 0.5F, -7.85F, 2.0F, 1.0F, 2.0F, new Dilation(-0.2F))
				.uv(6, 72).cuboid(0.5F, -1.0F, -7.85F, 1.0F, 2.0F, 2.0F, new Dilation(-0.2F))
				.uv(12, 72).cuboid(-1.5F, -1.0F, -7.85F, 1.0F, 2.0F, 2.0F, new Dilation(-0.2F))
				.uv(72, 27).cuboid(-0.5F, 1.0F, -4.75F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -11.0F, 0.5F));

		ModelPartData gunhead = gun.addChild("gunhead", ModelPartBuilder.create().uv(36, 66).cuboid(-1.0F, -1.1F, 2.77F, 2.0F, 2.0F, 4.0F, new Dilation(-0.2F))
				.uv(48, 67).cuboid(-1.0F, -1.1F, 0.42F, 2.0F, 2.0F, 3.0F, new Dilation(-0.4F))
				.uv(0, 30).cuboid(-1.0F, -1.1F, -13.98F, 2.0F, 2.0F, 16.0F, new Dilation(-0.5F))
				.uv(66, 21).cuboid(-1.0F, -1.1F, -16.98F, 2.0F, 2.0F, 4.0F, new Dilation(-0.4F))
				.uv(66, 7).cuboid(-1.5F, -0.1F, 11.27F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.1F, -12.27F));

		ModelPartData guntip = gunhead.addChild("guntip", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, -16.5F));

		ModelPartData cog = gun.addChild("cog", ModelPartBuilder.create().uv(58, 67).cuboid(-1.5F, -1.5F, -4.5F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(20, 48).cuboid(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.5F, 6.5F));
		return TexturedModelData.of(modelData, 128, 128);
	}
}