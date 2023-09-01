package org.kitowashere.boiled_witchcraft.client;

import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

public class ClientGCTX {
    private static GlyphType selectedGlyph = GlyphType.fromIndex(0);
    private static GlyphMagic context_set = selectedGlyph.newMagic();

    public static void setSelectedGlyph(GlyphType newGlyph) { selectedGlyph = newGlyph; }
    public static GlyphType getSelectedGlyph() { return selectedGlyph; }

    public static void setMagic(GlyphMagic newContexts) { context_set = newContexts;}
    public static GlyphMagic getMagic() { return context_set; }
}