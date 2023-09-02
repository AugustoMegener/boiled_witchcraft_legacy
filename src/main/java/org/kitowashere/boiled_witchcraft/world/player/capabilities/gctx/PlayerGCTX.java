package org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;

public class PlayerGCTX {
    private GlyphType selectedGlyph;
    private GlyphMagic magic;

    public PlayerGCTX() {
        setSelectedGlyph(FIRE_GLYPH);
    }

    public void setSelectedGlyph(@NotNull GlyphType newGlyph) {
        selectedGlyph = newGlyph;
        magic = selectedGlyph.newMagic();
    }

    public GlyphType getSelectedGlyph() { return selectedGlyph; }
    public GlyphMagic getGlyphMagic() { return magic; }

    public void copyFrom(PlayerGCTX provider) {
        selectedGlyph = provider.selectedGlyph;
        magic = provider.magic;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("glyph", selectedGlyph.name());
        nbt.put("contexts", magic.serializeNBT());
    }

    public void loadNBTData(CompoundTag nbt) {
        GlyphType newGlyph = GlyphType.fromString(nbt.getString("glyph"));

        selectedGlyph = newGlyph!=null ? newGlyph : FIRE_GLYPH;
        magic = selectedGlyph.newMagic();
        magic.deserializeNBT((CompoundTag) nbt.get("contexts"));
    }
}
