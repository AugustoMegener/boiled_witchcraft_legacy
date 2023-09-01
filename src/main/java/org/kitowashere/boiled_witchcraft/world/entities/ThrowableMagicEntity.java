package org.kitowashere.boiled_witchcraft.world.entities;

import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class ThrowableMagicEntity extends AbstractArrow {
    public ThrowableMagicEntity(EntityType<ThrowableMagicEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }
}
