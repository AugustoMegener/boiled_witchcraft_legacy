package org.kitowashere.boiled_witchcraft.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public abstract class ThrowableMagicEntity extends AbstractHurtingProjectile {
    public final ResourceLocation TEXTURE;

    protected ThrowableMagicEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel, ResourceLocation texture) {
        super(pEntityType, pLevel);
        TEXTURE = texture;
    }
}
