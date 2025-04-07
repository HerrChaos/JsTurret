package com.github.chaos.jsturret.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<AmethystDispenserBlockEntity> JsTurretBlockEntity = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of("amyshield", "amethyst_dispenser_block_entity"),
            FabricBlockEntityTypeBuilder.create(AmethystDispenserBlockEntity::new, ModBlocks.AMETHYST_DISPENSER).build()
    );

    public static void init() {
    }
}
