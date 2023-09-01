package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class SurfacedIceMagic extends Block {
    public SurfacedIceMagic() {
        super(Properties.of(Material.ICE_SOLID).explosionResistance(-1).requiresCorrectToolForDrops().strength(10));
    }
}
