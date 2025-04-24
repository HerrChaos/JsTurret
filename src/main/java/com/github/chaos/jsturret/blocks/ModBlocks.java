package com.github.chaos.jsturret.blocks;

import com.github.chaos.jsturret.Jsturret;
import com.github.chaos.jsturret.blocks.custom.JsTurretBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block JS_TURRET = register(
            "jsturret",
            JsTurretBlock::new,
            AbstractBlock.Settings.create().solidBlock((state, view, pos) -> false).nonOpaque(),
            true
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Jsturret.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Jsturret.MOD_ID, name));
    }

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(JS_TURRET.asItem());
    }

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(ModBlocks::addItemsToCombatTabItemGroup);
    }
}
