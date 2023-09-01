package org.kitowashere.boiled_witchcraft.world.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import java.util.Objects;

import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.GLYPH_BLOCK_ENTITY;

public class GlyphBlockEntity extends BlockEntity {
    public GlyphType glyph;
    public GlyphMagic magic;

    public GlyphBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(GLYPH_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);

        glyph = Objects.requireNonNull(GlyphType.fromString(pTag.getString("glyph")));
        magic = glyph.newMagic();
        magic.deserializeNBT((CompoundTag) pTag.get("context"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);

        pTag.putString("glyph", glyph.name());
        pTag.put("context", magic.serializeNBT());
    }
}
