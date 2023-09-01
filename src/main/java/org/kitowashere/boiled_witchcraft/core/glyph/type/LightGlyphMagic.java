package org.kitowashere.boiled_witchcraft.core.glyph.type;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.kitowashere.boiled_witchcraft.core.GlyphContext;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.context.PillarContext;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.SLM;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TLM;

public class LightGlyphMagic extends GlyphMagic {
    @Override
    public GlyphContext[] newContextKit() {
        return new GlyphContext[] { new PillarContext() };
    }

    @Override
    public void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600));
        }
    }

    @Override
    public void applyOnSurface(Level level, BlockPos pos, Direction surface) {
        level.setBlock(pos.mutable().move(surface, ((PillarContext) CONTEXT_KIT[0]).getHeight()), SLM.get().defaultBlockState(), UPDATE_ALL);
        level.removeBlock(pos, false);
    }

    @Override
    public void useOnPaper(ServerLevel level, LivingEntity shooter, float vel) {
        shootProjectile(TLM.get().create(level) ,level, shooter, vel);
    }
}
