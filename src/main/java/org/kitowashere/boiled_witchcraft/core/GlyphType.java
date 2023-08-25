package org.kitowashere.boiled_witchcraft.core;

import java.util.ArrayList;

public record GlyphType(String name, GlyphMagic magic) {
    private static final ArrayList<GlyphType> registeredGlyphs = new ArrayList<>();

    public static int getGlyphAmount() { return registeredGlyphs.size(); }

    public static int indexOf(GlyphType glyphType) { return registeredGlyphs.indexOf(glyphType); }

    public static GlyphType fromString(String name) {
        for (GlyphType glyph : registeredGlyphs) { if (glyph.name().equals(name)) { return glyph; } } return null;
    }

    public static GlyphType fromIndex(int index) { return index <= registeredGlyphs.size() ? registeredGlyphs.get(index) : null; }

    public static void register(String name, GlyphMagic magic) {
        registeredGlyphs.add(new GlyphType(name, magic));
    }
}