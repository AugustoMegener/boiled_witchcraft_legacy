package org.kitowashere.boiled_witchcraft.glyphs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.blocks.SurfacedFireMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.entities.ThrowableMagicEntity;

public class PlantGlyphMagic extends GlyphMagic {
    public PlantGlyphMagic(RegistryObject<Block> block, RegistryObject<EntityType<ThrowableMagicEntity>> projectile) {
        super(block, projectile);
    }

    @Override
    public void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, 600));
        }
    }

    @Override
    public void applyOnSurface(Level level, BlockPos pos, Direction surface) {
        makePillar(3, level, pos, surface, SurfacedFireMagic.LEVEL, 3);
    }

    @Override
    public void useOnPaper(ServerLevel level, LivingEntity shooter, float vel) {
        shootProjectile(level, shooter, vel);
    }
}
