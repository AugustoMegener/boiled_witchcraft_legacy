package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.*;

public interface GlyphTypeGroup<T extends Enum<T>> extends StringRepresentable, Comparable<T> {
    @NotNull
    GlyphType getGlyph();

    int getIndex();

    @Override
    default @NotNull String getSerializedName() {
        return getGlyph().name();
    }

    @Override
    default int compareTo(@NotNull T o) {
        return Integer.compare(this.getIndex(), o.ordinal());
    }

    enum PrimalGlyph implements GlyphTypeGroup<PrimalGlyph> {
        FIRE(FIRE_GLYPH.get()), ICE(ICE_GLYPH.get()), LIGHT(LIGHT_GLYPH.get()), PLANT(PLANT_GLYPH.get());

        private final GlyphType GLYPH;

        PrimalGlyph(GlyphType glyph) {
            GLYPH = glyph;
        }

        @Override
        public @NotNull GlyphType getGlyph() {
            return GLYPH;
        }

        @Override
        public int getIndex() { return ordinal(); }
    }
}
