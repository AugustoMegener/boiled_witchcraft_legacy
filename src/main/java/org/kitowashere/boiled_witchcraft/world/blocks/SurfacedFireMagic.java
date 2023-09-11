package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING;

public class SurfacedFireMagic extends BaseFireBlock {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 2);

    public SurfacedFireMagic() {
        super(Properties.of(Material.FIRE).noCollission(), 4.0F);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEVEL);

        super.createBlockStateDefinition(builder);
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Entity entity) {
        entity.setRemainingFireTicks(1200);
        entity.moveRelative(0.5F, Vec3.atLowerCornerOf(state.getValue(FACING).getNormal().multiply(5)));

        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader p_53455_, @NotNull BlockPos p_53456_) {
        return true;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState p_60555_, @NotNull BlockGetter p_60556_, @NotNull BlockPos p_60557_, @NotNull CollisionContext p_60558_) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.00D, 16.0D);
    }

    @Override
    protected boolean canBurn(@NotNull BlockState p_49284_) {
        return true;
    }
}
