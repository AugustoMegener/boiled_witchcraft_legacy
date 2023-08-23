package org.kitowashere.boiled_witchcraft.registry;

import org.kitowashere.boiled_witchcraft.core.GlyphType;
import org.kitowashere.boiled_witchcraft.glyphs.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.PlantGlyphMagic;

import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.*;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.TFM;

public class GlyphTypeRegistry {
    public static void register() {
        GlyphType.register("fire", new FireGlyphMagic(SFM.get(), TFM.get()));
        GlyphType.register("ice", new IceGlyphMagic(SIM.get(), TFM.get()));
        GlyphType.register("light", new LightGlyphMagic(SLM.get(), TFM.get()));
        GlyphType.register("plant", new PlantGlyphMagic(SPM.get(), TFM.get()));
    }
}
