package org.kitowashere.boiled_witchcraft.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class MagicFireBallEntity extends AbstractHurtingProjectile {
    public MagicFireBallEntity(EntityType<? extends MagicFireBallEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (pResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entity = (EntityHitResult) pResult;

            entity.getEntity().setRemainingFireTicks(1200);
        }

        super.onHit(pResult);
    }
}
