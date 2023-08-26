package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import org.kitowashere.boiled_witchcraft.core.GlyphType;
import org.kitowashere.boiled_witchcraft.glyphs.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.PlantGlyphMagic;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.*;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.*;

public class GlyphTypeRegistry {
    public static void register() {
        GlyphType.register("fire", new FireGlyphMagic(SFM, TFM), new ResourceLocation(MODID, "textures/block/glyph_fire.png"));
        GlyphType.register("ice", new IceGlyphMagic(SIM, TIM), new ResourceLocation(MODID, "textures/block/glyph_ice.png"));
        GlyphType.register("light", new LightGlyphMagic(SLM, TLM), new ResourceLocation(MODID, "textures/block/glyph_light.png"));
        GlyphType.register("plant", new PlantGlyphMagic(SPM, TPM), new ResourceLocation(MODID, "textures/block/glyph_plant.png"));
    }
}
