package org.kitowashere.boiled_witchcraft.core.glyph;

import java.util.ArrayList;

public class GlyphGroup extends ArrayList<GlyphType> {
    public static final GlyphGroup PRIMAL = new GlyphGroup();

    public void include(GlyphType glyph) {
        add(glyph);
    }

    public boolean isMember(GlyphType glyphType) {
        for (GlyphType i : this) {
            if (i == glyphType) return true;
        }
        return false;
    }

    public GlyphType nextOrFirst(GlyphType member) {
        if (isMember(member)) return get((indexOf(member) + 1) % size());
        else return get(0);
    }

}