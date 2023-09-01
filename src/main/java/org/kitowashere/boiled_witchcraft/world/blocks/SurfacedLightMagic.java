package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class SurfacedLightMagic extends Block {
    public SurfacedLightMagic() {
        super(Properties.of(Material.AIR).instabreak().noCollission().lightLevel(state -> 15).noOcclusion());
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext context){
        return Block.box(0.1875, 0.1875, 0.1875, 0.8125, 0.8125, 0.8125);
    }
}
