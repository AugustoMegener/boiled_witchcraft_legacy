package org.kitowashere.boiled_witchcraft.world.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;

import static org.kitowashere.boiled_witchcraft.core.glyph.GlyphGroup.PRIMAL;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.GLYPH_REGISTRY;
import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.GLYPH_BLOCK_ENTITY;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;

public class GlyphBlockEntity extends BlockEntity {
    private GlyphType glyph;
    private GlyphMagic magic;

    public GlyphBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(GLYPH_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);

        glyph = GLYPH_REGISTRY.get().getValue(ResourceLocation.of(pTag.getString("glyph"), ':'));
        glyph = glyph!=null ? glyph : PRIMAL.get(0);

        magic = glyph.newMagic();
        magic.deserializeNBT((CompoundTag) pTag.get("contexts"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);

        pTag.putString("glyph", GlyphTypeRegistry.getGlyphTypeResourceLocation(glyph).toString());
        pTag.put("contexts", magic.serializeNBT());
    }

    public GlyphMagic getMagic() {
        if (magic == null) {
            if (glyph == null) glyph = FIRE_GLYPH.get();
            magic = glyph.newMagic();
        }

        return magic;
    }
}
