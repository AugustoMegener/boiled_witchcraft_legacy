package org.kitowashere.boiled_witchcraft.world.entities;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ThrowablePlantMagic extends ThrowableMagicEntity {
    public ThrowablePlantMagic(EntityType<ThrowableMagicEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();

        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, 1200));
        }

        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        getMagic().ifPresent(glyphMagic -> glyphMagic.applyOnSurface(level, pResult.getBlockPos().mutable().move(pResult.getDirection()), pResult.getDirection()));
        this.discard();
    }
}
