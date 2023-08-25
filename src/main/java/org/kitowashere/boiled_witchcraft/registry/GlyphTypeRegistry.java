package org.kitowashere.boiled_witchcraft.registry;

import org.kitowashere.boiled_witchcraft.core.GlyphType;
import org.kitowashere.boiled_witchcraft.glyphs.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.glyphs.PlantGlyphMagic;

import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.*;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.*;

public class GlyphTypeRegistry {
    public static void register() {
        GlyphType.register("fire", new FireGlyphMagic(SFM, TFM));
        GlyphType.register("ice", new IceGlyphMagic(SIM, TIM));
        GlyphType.register("light", new LightGlyphMagic(SLM, TLM));
        GlyphType.register("plant", new PlantGlyphMagic(SPM, TPM));
    }
}
