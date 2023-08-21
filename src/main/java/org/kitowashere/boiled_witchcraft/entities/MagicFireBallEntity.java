package org.kitowashere.boiled_witchcraft.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;


public class MagicFireBallEntity extends AbstractHurtingProjectile {
    public MagicFireBallEntity(EntityType<? extends MagicFireBallEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) { pResult.getEntity().setRemainingFireTicks(1200); }
}