package org.kitowashere.boiled_witchcraft.world.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphType;
import org.kitowashere.boiled_witchcraft.core.GlyphTypeProperty;
import org.kitowashere.boiled_witchcraft.world.blocks.entities.GlyphBlockEntity;

import static net.minecraft.core.Direction.UP;
import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.GLYPH_BLOCK_ENTITY;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.GLYPH_BLOCK;

public class GlyphBlock extends Block implements EntityBlock {
    public static final GlyphTypeProperty GLYPH = new GlyphTypeProperty("GLYPH", GlyphType.class);


    public GlyphBlock() {
        super(Properties.of(Material.AIR).requiresCorrectToolForDrops().noCollission().instabreak());
    }

    public static void setGlyphCTX(Level level, BlockPos pos, CompoundTag nbt) {
        BlockEntity entity = level.getBlockEntity(pos);

        if (entity == null) {
            entity = (((GlyphBlock) GLYPH_BLOCK.get())).newBlockEntity(pos, level.getBlockState(pos));
        }

        if (entity != null) {
            entity.load(nbt);
        }
    }

    public GlyphMagic getGlyphMagic(Level level, BlockPos pos) {
        GlyphBlockEntity blockEntity = (GlyphBlockEntity) level.getBlockEntity(pos);

        return blockEntity==null ? null : blockEntity.getMagic();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GLYPH);

        super.createBlockStateDefinition(builder);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        GlyphMagic magic = getGlyphMagic(level, pos);

        if(!player.isShiftKeyDown() && magic!=null) {
            magic.applyOnSurface(level, pos, UP);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        GlyphMagic magic = getGlyphMagic(level, pos);

        if (magic != null) {
            magic.glyphTouched(state, level, pos, entity, UP);
            magic.applyOnSurface(level, pos, UP);
        }

        super.entityInside(state, level, pos, entity);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return  Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.01D, 16.0D);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return GLYPH_BLOCK_ENTITY.get().create(pPos, pState);
    }
}