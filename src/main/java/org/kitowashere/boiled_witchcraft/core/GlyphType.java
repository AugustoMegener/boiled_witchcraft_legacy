package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public record GlyphType(String name, GlyphMagic magic) {
    private static final ArrayList<GlyphType> registeredGlyphs = new ArrayList<>();

    public static int getGlyphAmount() { return registeredGlyphs.size(); }

    public static int indexOf(GlyphType glyphType) { return registeredGlyphs.indexOf(glyphType); }

    public static GlyphType fromString(String name) {
        for (GlyphType glyph : registeredGlyphs) { if (glyph.name().equals(name)) { return glyph; } } return null;
    }

    public static GlyphType fromIndex(int index) { return index <= registeredGlyphs.size() ? registeredGlyphs.get(index) : null; }

    public static GlyphType register(String name, GlyphMagic magic) {
        GlyphType glyph = new GlyphType(name, magic);
        registeredGlyphs.add(glyph);
        return glyph;
    }
}