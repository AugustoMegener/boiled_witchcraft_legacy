package org.kitowashere.boiled_witchcraft.client;

import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;

public class ClientGCTX {
    private static GlyphType selectedGlyph = FIRE_GLYPH;
    private static GlyphMagic context_set = selectedGlyph.newMagic();

    public static void setSelectedGlyph(GlyphType newGlyph) { selectedGlyph = newGlyph; }
    public static GlyphType getSelectedGlyph() { return selectedGlyph; }

    public static void setMagic(GlyphMagic newContexts) { context_set = newContexts;}
    public static GlyphMagic getMagic() { return context_set; }
}