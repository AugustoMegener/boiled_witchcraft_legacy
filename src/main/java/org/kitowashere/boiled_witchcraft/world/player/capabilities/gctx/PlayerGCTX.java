package org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.GLYPH_REGISTRY;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;

public class PlayerGCTX {
    private GlyphType selectedGlyph;
    private GlyphMagic magic;

    public PlayerGCTX() {
        setSelectedGlyph(FIRE_GLYPH.get());
    }

    public void setSelectedGlyph(@NotNull GlyphType newGlyph) {
        selectedGlyph = newGlyph;
        magic = selectedGlyph.newMagic();
    }

    public void setMagic(@NotNull GlyphMagic newMagic) {
        if (newMagic.getClass().isInstance(newMagic)) magic = newMagic;
    }

    public GlyphType getSelectedGlyph() { return selectedGlyph; }
    public GlyphMagic getGlyphMagic() { return magic; }

    public void copyFrom(PlayerGCTX provider) {
        selectedGlyph = provider.selectedGlyph;
        magic = provider.magic;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("glyph", GlyphTypeRegistry.getGlyphTypeResourceLocation(selectedGlyph).toString());
        nbt.put("contexts", magic.serializeNBT());
    }

    public void loadNBTData(CompoundTag nbt) {
        GlyphType newGlyph = GLYPH_REGISTRY.get().getValue(ResourceLocation.of(nbt.getString("glyph"), ':'));

        selectedGlyph = newGlyph!=null ? newGlyph : FIRE_GLYPH.get();
        magic = selectedGlyph.newMagic();
        magic.deserializeNBT((CompoundTag) nbt.get("contexts"));
    }
}
