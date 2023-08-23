package org.kitowashere.boiled_witchcraft.glyphs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.kitowashere.boiled_witchcraft.blocks.SurfacedFireMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.entities.ThrowableMagicEntity;

public class PlantGlyphMagic<T extends ThrowableMagicEntity> extends GlyphMagic<T> {
    public PlantGlyphMagic(Block block, EntityType<T> projectile) {
        super(block, projectile);
    }

    @Override
    public void doMagicInSurface(int range, Level level, BlockPos pos, Direction surface) {
        GlyphMagic.makePillar(BLOCK, range, level, pos, surface, SurfacedFireMagic.LEVEL, 3);
    }

    @Override
    public void throwMagic(ServerLevel level, Entity shooter, float vel) {
        GlyphMagic.shootProjectile(PROJECTILE, level, shooter, vel);
    }
}
