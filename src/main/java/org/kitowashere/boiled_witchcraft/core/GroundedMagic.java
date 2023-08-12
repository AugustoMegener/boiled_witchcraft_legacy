package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.kitowashere.boiled_witchcraft.blocks.GroundedFireMagic;

import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.*;

public interface GroundedMagic {
    void execute(Level level, BlockPos pos);

    static void fire(Level level, BlockPos pos) {
        BlockPos[] fireMagicBlocks = {pos, pos.above(), pos.above().above()};

        for (int i = 0; i < 3; i++) {
            BlockState magic = GFM.get().defaultBlockState().setValue(GroundedFireMagic.LEVEL, i);
            level.setBlock(fireMagicBlocks[i], magic, 3);
        }
    }
    static void ice(Level level, BlockPos pos) {
        BlockPos[] iceMagicBlocks = {pos, pos.above(), pos.above().above()};

        for (int i = 0; i < 3; i++) {
            BlockState magic = GIM.get().defaultBlockState();
            level.setBlock(iceMagicBlocks[i], magic, 3);
        }
    }

    static void light(Level level, BlockPos pos) {
        level.setBlock(pos.above(), SPARK.get().defaultBlockState(), 3);
        level.removeBlock(pos, true);
    }

    static void plant(Level level, BlockPos pos) {
        BlockPos[] plantMagicBlocks = {pos, pos.above(), pos.above().above()};

        for (int i = 0; i < 3; i++) {
            BlockState magic = GPM.get().defaultBlockState().setValue(GroundedFireMagic.LEVEL, i);
            level.setBlock(plantMagicBlocks[i], magic, 3);
        }
    }
}
