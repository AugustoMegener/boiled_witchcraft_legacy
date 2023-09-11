package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.*;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphGroup;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.FireGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.IceGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.LightGlyphMagic;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.PlantGlyphMagic;

import java.util.Optional;
import java.util.function.Supplier;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class GlyphTypeRegistry {
    public static final DeferredRegister<GlyphType> GLYPH_TYPES = DeferredRegister.create(new ResourceLocation(MODID, "glyph_types"), MODID);
    public static final Supplier<IForgeRegistry<GlyphType>> GLYPH_REGISTRY = GLYPH_TYPES.makeRegistry(RegistryBuilder::new);
    public static final RegistryObject<GlyphType> FIRE_GLYPH = GLYPH_TYPES.register("fire_glyph", () -> new GlyphType(
            FireGlyphMagic::new,
            GlyphGroup.PRIMAL
    ));

    public static final RegistryObject<GlyphType> ICE_GLYPH = GLYPH_TYPES.register("ice_glyph", () -> new GlyphType(
            IceGlyphMagic::new,
            GlyphGroup.PRIMAL
    ));

    public static final RegistryObject<GlyphType> LIGHT_GLYPH = GLYPH_TYPES.register("light_glyph", () -> new GlyphType(
            LightGlyphMagic::new,
            GlyphGroup.PRIMAL
    ));
    public static final RegistryObject<GlyphType> PLANT_GLYPH = GLYPH_TYPES.register("plant_glyph", () -> new GlyphType(
            PlantGlyphMagic::new,
            GlyphGroup.PRIMAL
    ));

    public static ResourceLocation getGlyphTypeResourceLocation(GlyphType glyphType) {
        return GLYPH_REGISTRY.get().getKey(glyphType);
    }

    public static String getName(GlyphType glyphType) {
        return getGlyphTypeResourceLocation(glyphType).getPath();
    }

    public static Component translatableName(GlyphType glyphType) {
        return Component.translatable(getGlyphTypeResourceLocation(glyphType).toLanguageKey("glyph"));
    }

    public static ResourceLocation getTexture(GlyphType glyphType) {
        return getGlyphTypeResourceLocation(glyphType).withPrefix("textures/block/").withSuffix(".png");
    }

    public static @NotNull Optional<Holder.Reference<Block>> getGlyphBlock(GlyphType glyphType) {
        ResourceLocation glyphResource = getGlyphTypeResourceLocation(glyphType);

        return ForgeRegistries.BLOCKS.getDelegate(
                new ResourceLocation(glyphResource.getNamespace(),glyphResource.getPath()+"_block")
        );
    }
}
