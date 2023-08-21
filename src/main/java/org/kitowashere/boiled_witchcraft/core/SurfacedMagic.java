package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.kitowashere.boiled_witchcraft.blocks.GroundedFireMagic;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.*;

public interface SurfacedMagic {
    void execute(Level level, BlockPos pos, Direction surface);

    static void fire(Level level, BlockPos pos, Direction surface) {
        BlockPos.MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < 3; i++) {
            level.setBlock(blockPos, GFM.get().defaultBlockState().setValue(GroundedFireMagic.LEVEL, i), UPDATE_ALL);
            blockPos.move(surface);
        }
    }
    static void ice(Level level, BlockPos pos, Direction surface) {
        MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < 3; i++) {
            level.setBlock(blockPos, GIM.get().defaultBlockState(), UPDATE_ALL);
            blockPos.move(surface);
        }
    }

    static void light(Level level, BlockPos pos, Direction surface) {
        level.setBlock(pos.mutable().move(surface), SPARK.get().defaultBlockState(), UPDATE_ALL);
        level.removeBlock(pos.mutable().move(surface.getOpposite()), false);
    }

    static void plant(Level level, BlockPos pos, Direction surface) {
        MutableBlockPos blockPos = pos.mutable();

        for (int i = 0; i < 3; i++) {
            level.setBlock(blockPos, GPM.get().defaultBlockState(), UPDATE_ALL);
            blockPos.move(surface);
        }
    }
}