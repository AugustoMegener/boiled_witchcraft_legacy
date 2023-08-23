package org.kitowashere.boiled_witchcraft.glyphs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class LightGlyphMagic extends GlyphMagic {

    public LightGlyphMagic(Block block, EntityType projectile) {
        super(block, projectile);
    }

    @Override
    public void doMagicInSurface(int range, Level level, BlockPos pos, Direction surface) {
        level.setBlock(pos.mutable().move(surface, range), BLOCK.defaultBlockState(), UPDATE_ALL);
    }

    @Override
    public void throwMagic(ServerLevel level, Entity shooter, float vel) {
        GlyphMagic.shootProjectile(PROJECTILE, level, shooter, vel);
    }
}
