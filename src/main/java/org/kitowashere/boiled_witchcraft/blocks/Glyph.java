package org.kitowashere.boiled_witchcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.GFM;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.GIM;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.SPARK;

public class Glyph extends Block {
    public static final IntegerProperty GLYPH = IntegerProperty.create("glyph", 0, 2);

    public Glyph() {
        super(Properties.of(Material.AIR).requiresCorrectToolForDrops().noCollission().instabreak());
    }

    public static void DoMagic(BlockState state, Level level, BlockPos pos) {
        switch (state.getValue(GLYPH)) {
            case 0:
                BlockPos[] fireMagicBlocks = {pos, pos.above(), pos.above().above()};

                for (int i = 0; i < 3; i++) {
                    BlockState magic = GFM.get().defaultBlockState().setValue(GroundedFireMagic.LEVEL, i);
                    level.setBlock(fireMagicBlocks[i], magic, 3);
                }
                break;
            case 1:
                BlockPos[] iceMagicBlocks = {pos, pos.above(), pos.above().above()};

                for (int i = 0; i < 3; i++) {
                    BlockState magic = GIM.get().defaultBlockState();
                    level.setBlock(iceMagicBlocks[i], magic, 3);
                }
                break;
            case 2:
                level.setBlock(pos.above(), SPARK.get().defaultBlockState(), 3);
                level.removeBlock(pos, true);
                break;
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!player.isShiftKeyDown()) {
            this.DoMagic(state, level, pos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        this.DoMagic(state, level, pos);

        super.entityInside(state, level, pos, entity);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GLYPH);

        super.createBlockStateDefinition(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return  Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.01D, 16.0D);
    }
}
