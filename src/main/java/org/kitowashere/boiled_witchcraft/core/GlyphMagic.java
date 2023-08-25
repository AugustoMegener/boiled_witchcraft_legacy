package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.entities.ThrowableMagicEntity;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public abstract class GlyphMagic {
    protected final RegistryObject<Block> BLOCK;
    protected final RegistryObject<EntityType<ThrowableMagicEntity>> PROJECTILE;

    public GlyphMagic(RegistryObject<Block> block, RegistryObject<EntityType<ThrowableMagicEntity>> projectile) {
        BLOCK = block;
        PROJECTILE = projectile;
    }


    public abstract void glyphTouched(BlockState state, Level level, BlockPos pos, Entity entity, Direction surface);

    public abstract void applyOnSurface(Level level, BlockPos pos, Direction surface);

    public abstract void useOnPaper(ServerLevel level, LivingEntity shooter, float vel);

    protected void makePillar(int range, Level level, BlockPos pos, Direction surface) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < range; i++) {
            level.setBlock(blockPos, BLOCK.get().defaultBlockState(), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    protected void makePillar(int range, Level level, BlockPos pos, Direction surface, IntegerProperty transitionProperty, int transitionRange) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < range-transitionRange; i++) {
            level.setBlock(blockPos, BLOCK.get().defaultBlockState().setValue(transitionProperty, 0), UPDATE_ALL);
            blockPos.move(surface);
        }

        for (int i = 0; i < transitionRange; i++) {
            level.setBlock(blockPos, BLOCK.get().defaultBlockState().setValue(transitionProperty, i), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    protected void shootProjectile(ServerLevel level, LivingEntity shooter, float vel) {
        ThrowableMagicEntity projectileEntity = PROJECTILE.get().create(level);

        projectileEntity.setPos(shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());
        projectileEntity.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, vel, 1.0F);

        level.addFreshEntity(projectileEntity);
    }
}
