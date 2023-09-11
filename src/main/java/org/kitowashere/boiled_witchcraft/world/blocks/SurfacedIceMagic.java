package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;

public class SurfacedIceMagic extends DirectionalBlock {
    public SurfacedIceMagic() {
        super(Properties.of(Material.ICE_SOLID).explosionResistance(-1).requiresCorrectToolForDrops().strength(10));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);

        pBuilder.add(FACING);
    }
}
