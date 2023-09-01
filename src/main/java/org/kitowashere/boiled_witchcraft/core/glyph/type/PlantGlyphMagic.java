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
import org.kitowashere.boiled_witchcraft.core.glyph.context.PillarContext;
import org.kitowashere.boiled_witchcraft.world.blocks.SurfacedFireMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;

import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.SPM;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TLM;

public class PlantGlyphMagic extends GlyphMagic {
    @Override
    public GlyphContext[] newContextKit() {
        return new GlyphContext[] { new PillarContext() };
    }

    @Override
    public void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, 600));
        }
    }

    @Override
    public void applyOnSurface(Level level, BlockPos pos, Direction surface) {
        makePillar(SPM.get(), ((PillarContext) CONTEXT_KIT[0]).getHeight(), level, pos, surface, SurfacedFireMagic.LEVEL, 3);
    }

    @Override
    public void useOnPaper(ServerLevel level, LivingEntity shooter, float vel) {
        shootProjectile(TLM.get().create(level), level, shooter, vel);
    }
}
