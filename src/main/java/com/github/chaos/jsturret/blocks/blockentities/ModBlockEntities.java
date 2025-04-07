package com.github.chaos.jsturret.blocks.blockentities;

import com.github.chaos.jsturret.Jsturret;
import com.github.chaos.jsturret.blocks.ModBlocks;
import com.github.chaos.jsturret.blocks.blockentities.custom.JsTurretBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<JsTurretBlockEntity> JS_TURRET_BLOCK_ENTITY =
            register("jsturret", JsTurretBlockEntity::new, ModBlocks.JS_TURRET);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.of(Jsturret.MOD_ID, name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {
    }
}
