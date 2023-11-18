package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kitowashere.boiled_witchcraft.world.blocks.entities.SprayerBlockEntity;

public class Sprayer extends Block implements EntityBlock {
    public Sprayer() { super(Properties.of(Material.METAL)); }

    @Nullable @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new SprayerBlockEntity(pPos, pState);
    }

    @Override
    public @NotNull InteractionResult use(
        @NotNull BlockState pState, @NotNull Level pLevel,          @NotNull BlockPos pPos,
        @NotNull Player pPlayer,    @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit)
    {
        if (pLevel.getBlockEntity(pPos) instanceof SprayerBlockEntity blockEntity && pPlayer instanceof ServerPlayer player) {
            NetworkHooks.openScreen(player, blockEntity, pPos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull VoxelShape getShape(
        @NotNull BlockState pState, @NotNull BlockGetter pLevel,
        @NotNull BlockPos pPos,     @NotNull CollisionContext pContext
    ) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.375, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0.375, 0.25, 0.75, 0.4375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.4375, 0.375, 0.625, 0.625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.625, 0.3125, 0.6875, 1, 0.6875), BooleanOp.OR);

        return shape;
    }
}
