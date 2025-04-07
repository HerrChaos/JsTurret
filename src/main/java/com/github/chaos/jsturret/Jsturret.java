package com.github.chaos.jsturret;

import com.github.chaos.jsturret.blocks.ModBlocks;
import com.github.chaos.jsturret.blocks.blockentities.ModBlockEntities;
import com.github.chaos.jsturret.networking.ModNetworking;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Jsturret implements ModInitializer {

    public static final String MOD_ID = "jsturret";

    public static Identifier id(String id) {
        return Identifier.of(MOD_ID, id);
    }

    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModNetworking.init();
        ModBlockEntities.init();
    }
}
