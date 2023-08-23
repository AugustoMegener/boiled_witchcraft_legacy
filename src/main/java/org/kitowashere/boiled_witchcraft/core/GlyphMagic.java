package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.kitowashere.boiled_witchcraft.entities.ThrowableMagicEntity;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public abstract class GlyphMagic {
    public final Block BLOCK;
    public final EntityType PROJECTILE;

    public GlyphMagic(Block block, EntityType projectile) {
        BLOCK = block;
        PROJECTILE = projectile;
    }

    public abstract void doMagicInSurface(int range, Level level, BlockPos pos, Direction surface);

    public abstract void throwMagic(ServerLevel level, net.minecraft.world.entity.Entity shooter, float vel);

    public static void makePillar(Block block, int range, Level level, BlockPos pos, Direction surface) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < range; i++) {
            level.setBlock(blockPos, block.defaultBlockState(), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    public static void makePillar(Block block, int range, Level level, BlockPos pos, Direction surface, IntegerProperty transitionProperty, int transitionRange) {
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

    public static void shootProjectile(EntityType<? extends ThrowableMagicEntity> projectile, ServerLevel level, net.minecraft.world.entity.Entity shooter, float vel) {
        ThrowableMagicEntity projectileEntity = projectile.create(level);

        level.addFreshEntity(projectileEntity);

        projectileEntity.setPos(shooter.position());
        projectileEntity.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0, vel, 1);
    }
}
