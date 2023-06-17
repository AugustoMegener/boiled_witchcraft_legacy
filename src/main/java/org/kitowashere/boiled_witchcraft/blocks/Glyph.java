package org.kitowashere.boiled_witchcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static org.kitowashere.boiled_witchcraft.blocks.Registry.GFM;

public class Glyph extends Block {
    public static final IntegerProperty GLYPH = IntegerProperty.create("glyph", 0, 1);

    public Glyph() {
        super(Properties.of(Material.LEAVES).destroyTime(1).noOcclusion().dynamicShape());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        switch (state.getValue(GLYPH)) {
            case 0:
                if(!player.isShiftKeyDown()){
                    BlockPos[] blocks = {pos, pos.above(), pos.above().above()};

                    for(int i = 0; i < 3; i++){
                        BlockState fireMagic = GFM.get().defaultBlockState().setValue(GroundedFireMagic.LEVEL, i);
                        level.setBlock(blocks[i], fireMagic, 0);
                    }
                }
                break;
            case 1: System.out.println("gelo"); break;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GLYPH);

        super.createBlockStateDefinition(builder);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return  Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.01D, 16.0D);
    }
}
