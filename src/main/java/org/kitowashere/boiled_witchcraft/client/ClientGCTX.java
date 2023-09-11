package org.kitowashere.boiled_witchcraft.client;

import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;

public class ClientGCTX {
    private static GlyphType selectedGlyph = FIRE_GLYPH.get();
    private static GlyphMagic context_set = selectedGlyph.newMagic();

    public static void setSelectedGlyph(GlyphType newGlyph) { selectedGlyph = newGlyph; }
    public static GlyphType getSelectedGlyph() { return selectedGlyph; }

    public static void setGlyphMagic(GlyphMagic newContexts) { context_set = newContexts;}
    public static GlyphMagic getGlyphMagic() { return context_set; }
}