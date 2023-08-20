package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TFM;

public enum GlyphType implements StringRepresentable {
    FIRE("fire",  SurfacedMagic::fire, TFM.get()), ICE("ice", SurfacedMagic::ice, TFM.get()),
    LIGHT("light",  SurfacedMagic::light, TFM.get()), PLANT("plant", SurfacedMagic::plant, TFM.get());

    private final String NAME;
    private final SurfacedMagic SURFACED_MAGIC;
    private final EntityType<AbstractHurtingProjectile> TROW_MAGIC_PROJECTILE;

    GlyphType(String name, SurfacedMagic action, EntityType trowMagicProjectile) {
        NAME = name;
        SURFACED_MAGIC = action;
        TROW_MAGIC_PROJECTILE = trowMagicProjectile;
    }

    @Override
    public String getSerializedName() { return NAME; }

    public void doMagicInSurface(Level level, BlockPos pos, Direction surface) { SURFACED_MAGIC.executeSurfacedMagic(level, pos, surface); }
    public void trowMagic(Level level, Vec3 dir, float vel) { TROW_MAGIC_PROJECTILE.create(level).shoot(dir.x, dir.y, dir.z, vel, 0); }
}