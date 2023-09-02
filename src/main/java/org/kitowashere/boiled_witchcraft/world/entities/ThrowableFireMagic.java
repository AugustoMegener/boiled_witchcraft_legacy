package org.kitowashere.boiled_witchcraft.world.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;


public class ThrowableFireMagic extends ThrowableMagicEntity {
    public ThrowableFireMagic(EntityType<ThrowableMagicEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().setRemainingFireTicks(1200);
        this.discard();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 0.5f, true, Level.ExplosionInteraction.BLOCK);
        this.discard();
    }
}