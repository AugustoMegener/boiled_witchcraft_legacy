package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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

public class SurfacedPlantMagic extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 2);

    public SurfacedPlantMagic() {
        super(Properties.of(Material.GRASS).strength(10));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEVEL);

        super.createBlockStateDefinition(builder);
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Entity entity) {
        entity.makeStuckInBlock(state, new Vec3(25, 5, 25));
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState p_57336_, @NotNull BlockGetter p_57337_, @NotNull BlockPos p_57338_, @NotNull CollisionContext p_57339_) {
        return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    }
}
