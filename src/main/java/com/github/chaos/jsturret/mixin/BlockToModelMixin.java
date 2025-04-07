package com.github.chaos.jsturret.mixin;

import com.github.chaos.jsturret.blocks.ModBlocks;
import com.github.chaos.jsturret.client.renderers.JsTurretItemRenderer;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(SpecialModelTypes.class)
public class BlockToModelMixin {

    @Inject(method = "buildBlockToModelTypeMap", at = @At(value = "INVOKE", target = "Ljava/util/Map;forEach(Ljava/util/function/BiConsumer;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void buildBlockToModelTypeMap(LoadedEntityModels entityModels, CallbackInfoReturnable<Map<Block, SpecialModelRenderer<?>>> cir, Map map, ImmutableMap.Builder builder) {
        map.put(ModBlocks.JS_TURRET, new JsTurretItemRenderer.Unbaked());
    }
}
