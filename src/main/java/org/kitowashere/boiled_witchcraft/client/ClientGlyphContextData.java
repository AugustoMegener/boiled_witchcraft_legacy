package org.kitowashere.boiled_witchcraft.client;

import org.kitowashere.boiled_witchcraft.core.GlyphType;

public class ClientGlyphContextData {
    private static String selectedGlyph;

    public static void setSelectedGlyph(String newGlyph) { selectedGlyph = newGlyph; }

    public static GlyphType getSelectedGlyph() { return GlyphType.fromString(selectedGlyph); }
}
