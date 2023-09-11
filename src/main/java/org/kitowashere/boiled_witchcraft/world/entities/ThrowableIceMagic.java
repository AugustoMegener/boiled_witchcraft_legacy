package org.kitowashere.boiled_witchcraft.world.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

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
        getMagic().ifPresent(glyphMagic -> glyphMagic.applyOnSurface(level, pResult.getBlockPos().mutable().move(pResult.getDirection()), pResult.getDirection()));
        this.discard();
    }
}
