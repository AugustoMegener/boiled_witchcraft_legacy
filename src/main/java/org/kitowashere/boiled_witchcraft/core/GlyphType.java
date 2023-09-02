package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Supplier;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public record GlyphType(@NotNull String name, @NotNull Supplier<GlyphMagic> magic, @NotNull ResourceLocation texture) implements Comparable<GlyphType> {
    private static final ArrayList<GlyphType> registeredGlyphs = new ArrayList<>();

    public GlyphType { registeredGlyphs.add(this); }

    public static int indexOf(GlyphType glyphType) { return registeredGlyphs.indexOf(glyphType); }

    public static GlyphType fromString(String name) {
        for (GlyphType glyph : registeredGlyphs) { if (glyph.name().equals(name)) { return glyph; } } return null;
    }

    public static GlyphType fromIndex(int index) { return index <= registeredGlyphs.size() ? registeredGlyphs.get(index) : null; }

    public static ArrayList<GlyphType> getAllGlyphTypes() { return new ArrayList<>(registeredGlyphs); }

    public GlyphType next(int min, int max, int step) {
        return indexOf(this)>=max ? fromIndex(min) : fromIndex(indexOf(this)+step);
    }

    public Component translatableName() {
        return Component.translatable(MODID + ".glyph_type." + name);
    }

    public GlyphMagic newMagic() { return magic.get(); }

    @Override
    public int compareTo(@NotNull GlyphType o) {
        return Integer.compare(indexOf(o), indexOf(this));
    }
}