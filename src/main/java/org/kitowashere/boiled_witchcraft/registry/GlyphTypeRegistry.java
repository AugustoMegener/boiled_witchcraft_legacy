package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import org.kitowashere.boiled_witchcraft.core.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.type.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.type.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.type.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.type.PlantGlyphMagic;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class GlyphTypeRegistry {
    public static void register() {
        new GlyphType(
                "fire",
                FireGlyphMagic::new,
                new ResourceLocation(MODID, "textures/block/fire_glyph.png")
        );
        new GlyphType(
                "ice",
                IceGlyphMagic::new,
                new ResourceLocation(MODID, "textures/block/ice_glyph.png")
        );
        new GlyphType(
                "light",
                LightGlyphMagic::new,
                new ResourceLocation(MODID, "textures/block/light_glyph.png")
        );
        new GlyphType(
                "plant",
                PlantGlyphMagic::new,
                new ResourceLocation(MODID, "textures/block/plant_glyph.png")
        );
    }
}
