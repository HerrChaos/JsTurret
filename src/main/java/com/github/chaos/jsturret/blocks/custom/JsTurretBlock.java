package com.github.chaos.jsturret.blocks.custom;

import com.github.chaos.jsturret.blocks.blockentities.ModBlockEntities;
import com.github.chaos.jsturret.blocks.blockentities.custom.JsTurretBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WritableBookItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

public class JsTurretBlock extends BlockWithEntity {
    public JsTurretBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(JsTurretBlock::new);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new JsTurretBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.JS_TURRET_BLOCK_ENTITY, JsTurretBlockEntity::tick);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() instanceof WritableBookItem) {
            String code = stack.get(DataComponentTypes.WRITABLE_BOOK_CONTENT).stream(false).collect(Collectors.joining("\n"));

            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof JsTurretBlockEntity turret) {
                turret.setJsCode(code);
                return ActionResult.SUCCESS;
            }
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2, 0, 0.2, 0.8, 1, 0.8);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2, 0, 0.2, 0.8, 1, 0.8);
    }
}
