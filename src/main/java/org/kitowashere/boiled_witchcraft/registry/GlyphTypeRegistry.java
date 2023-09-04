package org.kitowashere.boiled_witchcraft.registry;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.PlantGlyphMagic;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.core.glyph.GlyphType.GLYPH_REGISTRY;

public class GlyphTypeRegistry {
    public static final DeferredRegister<GlyphType> GLYPH_TYPES = DeferredRegister.create(GLYPH_REGISTRY, MODID);

    public static final RegistryObject<GlyphType> FIRE_GLYPH = GLYPH_TYPES.register("fire_glyph", () -> new GlyphType(
            FireGlyphMagic::new
    ));

    public static final RegistryObject<GlyphType> ICE_GLYPH = GLYPH_TYPES.register("fire_glyph", () -> new GlyphType(
            IceGlyphMagic::new
    ));

    public static final RegistryObject<GlyphType> LIGHT_GLYPH = GLYPH_TYPES.register("fire_glyph", () -> new GlyphType(
            LightGlyphMagic::new
    ));
    public static final RegistryObject<GlyphType> PLANT_GLYPH = GLYPH_TYPES.register("fire_glyph", () -> new GlyphType(
            PlantGlyphMagic::new
    ));
}
