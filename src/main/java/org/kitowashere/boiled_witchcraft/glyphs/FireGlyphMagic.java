package org.kitowashere.boiled_witchcraft.glyphs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.kitowashere.boiled_witchcraft.blocks.SurfacedFireMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.entities.ThrowableFireMagic;

import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.SFM;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TFM;

public class FireGlyphMagic extends GlyphMagic {

    @Override
    public void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface) { }

    @Override
    public void applyOnSurface(Level level, BlockPos pos, Direction surface) {
        makePillar(SFM.get(), 3, level, pos, surface, SurfacedFireMagic.LEVEL, 3);
    }

    @Override
    public void useOnPaper(ServerLevel level, LivingEntity shooter, float vel) {
        shootProjectile(TFM.get().create(level), level, shooter, vel);
    }
}
