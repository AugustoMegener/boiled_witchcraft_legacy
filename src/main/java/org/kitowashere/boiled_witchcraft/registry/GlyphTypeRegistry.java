package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.core.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.PlantGlyphMagic;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.core.GlyphType.GLYPH_REGISTER_SUPPLIER;

public class GlyphTypeRegistry {
    public static final DeferredRegister<GlyphType> GLYPH = DeferredRegister.create(GLYPH_REGISTER_SUPPLIER.get(), MODID);

    public static final RegistryObject<GlyphType> FIRE_GLYPH = GLYPH.register("fire_glyph", () -> new GlyphType(
            "fire",
            FireGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/fire_glyph.png")
    ));
    public static final RegistryObject<GlyphType> ICE_GLYPH = GLYPH.register("fire_glyph", () -> new GlyphType(
            "ice",
            IceGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/ice_glyph.png")
    ));

    public static final RegistryObject<GlyphType> LIGHT_GLYPH = GLYPH.register("fire_glyph", () -> new GlyphType(
            "light",
            LightGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/light_glyph.png")
    ));
    public static final RegistryObject<GlyphType> PLANT_GLYPH = GLYPH.register("fire_glyph", () -> new GlyphType(
            "plant",
            PlantGlyphMagic::new,
            new ResourceLocation(MODID, "textures/block/plant_glyph.png")
    ));
}
