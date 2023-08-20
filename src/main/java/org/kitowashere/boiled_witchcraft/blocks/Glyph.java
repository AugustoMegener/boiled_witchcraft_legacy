package org.kitowashere.boiled_witchcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import static net.minecraft.core.Direction.UP;

public class Glyph extends Block {
    public static final EnumProperty<GlyphType> GLYPH = EnumProperty.create("glyph", GlyphType.class);

    public Glyph() {
        super(Properties.of(Material.AIR).requiresCorrectToolForDrops().noCollission().instabreak());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GLYPH);

        super.createBlockStateDefinition(builder);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!player.isShiftKeyDown()) state.getValue(GLYPH).doMagicInSurface(level, pos, UP);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        state.getValue(GLYPH).doMagicInSurface(level, pos, UP);

        super.entityInside(state, level, pos, entity);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return  Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.01D, 16.0D);
    }
}