package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class SurfacedIceMagic extends Block {
    public SurfacedIceMagic() {
        super(Properties.of(Material.ICE_SOLID).explosionResistance(-1).requiresCorrectToolForDrops().strength(10));
    }
}
