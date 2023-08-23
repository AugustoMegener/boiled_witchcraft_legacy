package org.kitowashere.boiled_witchcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SurfacedLightMagic extends Block {
    public SurfacedLightMagic() {
        super(Properties.of(Material.AIR).instabreak().noCollission().lightLevel(state -> 15).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context){
        return Block.box(0.1875, 0.1875, 0.1875, 0.8125, 0.8125, 0.8125);
    }
}
