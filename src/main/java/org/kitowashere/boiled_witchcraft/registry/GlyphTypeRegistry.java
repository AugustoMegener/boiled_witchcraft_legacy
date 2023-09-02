package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import org.kitowashere.boiled_witchcraft.core.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.PlantGlyphMagic;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class GlyphTypeRegistry {
    public static final GlyphType FIRE_GLYPH = new GlyphType(
            "fire",
            FireGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/fire_glyph.png")
    );
    public static final GlyphType ICE_GLYPH = new GlyphType(
            "ice",
            IceGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/ice_glyph.png")
    );
    public static final GlyphType LIGHT_GLYPH = new GlyphType(
            "light",
            LightGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/light_glyph.png")
    );
    public static final GlyphType PLANT_GLYPH = new GlyphType(
            "plant",
            PlantGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/plant_glyph.png")
    );
}
