package org.kitowashere.boiled_witchcraft.core.glyph;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;

import java.util.function.Supplier;

public class GlyphType {
    private final @NotNull Supplier<GlyphMagic> MAGIC;

    public GlyphType(@NotNull Supplier<GlyphMagic> magic, @Nullable GlyphGroup group) {
        MAGIC = magic;
        if (group != null) group.include(this);
    }

    public GlyphMagic newMagic() {
        return MAGIC.get();
    }

    @Override
    public String toString() {
        return GlyphTypeRegistry.getName(this);
    }
}