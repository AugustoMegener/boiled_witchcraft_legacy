package org.kitowashere.boiled_witchcraft.blocks;

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

public class SurfacedFireMagic extends BaseFireBlock {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 2);

    public SurfacedFireMagic() {
        super(Properties.of(Material.FIRE).noCollission(), 4.0F);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);

        super.createBlockStateDefinition(builder);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.setRemainingFireTicks(1200);
        entity.moveRelative(0.5F, new Vec3(0.0F, 0.5F, 0.0F));

        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader p_53455_, BlockPos p_53456_) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.00D, 16.0D);
    }

    @Override
    protected boolean canBurn(BlockState p_49284_) {
        return true;
    }
}
