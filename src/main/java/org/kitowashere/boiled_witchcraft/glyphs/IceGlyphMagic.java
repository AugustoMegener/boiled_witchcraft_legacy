package org.kitowashere.boiled_witchcraft.glyphs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.entities.ThrowableMagicEntity;

public class IceGlyphMagic extends GlyphMagic {
    public IceGlyphMagic(RegistryObject<Block> block, RegistryObject<EntityType<ThrowableMagicEntity>> projectile) {
        super(block, projectile);
    }

    @Override
    public void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface) {
        entity.moveTo(pos.mutable().move(surface, 4), entity.getYRot(), entity.getXRot());
    }

    @Override
    public void applyOnSurface(Level level, BlockPos pos, Direction surface) {
        makePillar(3, level, pos, surface);
    }

    @Override
    public void useOnPaper(ServerLevel level, LivingEntity shooter, float vel) {
        shootProjectile(level, shooter, vel);
    }
}
