package org.kitowashere.boiled_witchcraft.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;


public class ThrowableFireMagic extends ThrowableMagicEntity {
    public ThrowableFireMagic(EntityType<? extends ThrowableFireMagic> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, new ResourceLocation(MODID, "textures/entity/throwable_magic/fire.png"));
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().setRemainingFireTicks(1200);
        this.remove(RemovalReason.KILLED);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2, true, Level.ExplosionInteraction.BLOCK);
        this.remove(RemovalReason.KILLED);
    }
}