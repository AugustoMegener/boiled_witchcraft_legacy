package org.kitowashere.boiled_witchcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CryingObsidianBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class GroundedIceMagic extends IceBlock {
    public GroundedIceMagic() {
        super(Properties.of(Material.FIRE).explosionResistance(-1).requiresCorrectToolForDrops().strength(10));
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.setTicksFrozen(1200);

        super.entityInside(state, level, pos, entity);
    }
}
