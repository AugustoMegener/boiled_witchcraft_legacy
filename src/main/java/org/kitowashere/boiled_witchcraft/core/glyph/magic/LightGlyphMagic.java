package org.kitowashere.boiled_witchcraft.core.glyph.magic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.kitowashere.boiled_witchcraft.core.glyph.context.GlyphContext;
import org.kitowashere.boiled_witchcraft.core.glyph.context.PillarContext;
import org.kitowashere.boiled_witchcraft.core.glyph.context.SuspendedBlockContext;
import org.kitowashere.boiled_witchcraft.world.entities.ThrowableMagicEntity;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.SLM;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TLM;

public class LightGlyphMagic extends GlyphMagic {
    @Override
    public GlyphContext[] newContextKit() {
        return new GlyphContext[] { new SuspendedBlockContext() };
    }

    @Override
    public void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface) {
        if (entity instanceof LivingEntity && ((PillarContext) CONTEXT_KIT[0]).getHeight() <= 1) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600));
        }
    }

    @Override
    public void applyOnSurface(Level level, BlockPos pos, Direction surface) {
        level.removeBlock(pos, false);
        level.setBlock(pos.mutable().move(surface, ((PillarContext) CONTEXT_KIT[0]).getHeight()-1), SLM.get().defaultBlockState(), UPDATE_ALL);
    }

    @Override
    public void useOnPaper(ServerLevel level, LivingEntity shooter, float vel) {
        ThrowableMagicEntity projectile = TLM.get().create(level);

        if (projectile != null) {
            shootProjectile(projectile, level, shooter, vel);
        }
    }
}