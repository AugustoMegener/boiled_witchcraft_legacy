package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.kitowashere.boiled_witchcraft.entities.ThrowableMagicEntity;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public abstract class GlyphMagic {
    public abstract void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface);

    public abstract void applyOnSurface(Level level, BlockPos pos, Direction surface);

    public abstract void useOnPaper(ServerLevel level, LivingEntity shooter, float vel);

    protected void makePillar(Block block, int range, Level level, BlockPos pos, Direction surface) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < range; i++) {
            level.setBlock(blockPos, block.defaultBlockState(), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    protected void makePillar(Block block, int range, Level level, BlockPos pos, Direction surface, IntegerProperty transitionProperty, int transitionRange) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < range-transitionRange; i++) {
            level.setBlock(blockPos, block.defaultBlockState().setValue(transitionProperty, 0), UPDATE_ALL);
            blockPos.move(surface);
        }

        for (int i = 0; i < transitionRange; i++) {
            level.setBlock(blockPos, block.defaultBlockState().setValue(transitionProperty, i), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    protected void shootProjectile(ThrowableMagicEntity projectile, ServerLevel level, LivingEntity shooter, float vel) {
        projectile.setPos(shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, vel, 1.0F);

        level.addFreshEntity(projectile);
    }
}
