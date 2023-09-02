package org.kitowashere.boiled_witchcraft.world.entities;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.ICE_GLYPH;

public class ThrowableIceMagic extends ThrowableMagicEntity {
    public ThrowableIceMagic(EntityType<ThrowableMagicEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().setTicksFrozen(1200);
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        ICE_GLYPH.newMagic().applyOnSurface(level, pResult.getBlockPos().above(), Direction.UP);
        this.discard();
    }
}
