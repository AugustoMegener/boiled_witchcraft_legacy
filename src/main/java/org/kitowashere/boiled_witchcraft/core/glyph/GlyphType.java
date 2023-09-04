package org.kitowashere.boiled_witchcraft.core.glyph;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;

import java.util.Objects;
import java.util.function.Supplier;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class GlyphType {
    public static final DeferredRegister<GlyphType> GLYPH_REGISTER = DeferredRegister.create(new ResourceLocation(MODID, "glyph_types"), MODID);
    public static final IForgeRegistry<GlyphType> GLYPH_REGISTRY = GLYPH_REGISTER.makeRegistry(RegistryBuilder::new).get();

    public final @NotNull ResourceLocation id;
    public final @NotNull ResourceLocation texture;
    private final @NotNull Supplier<GlyphMagic> magic;

    public GlyphType(@NotNull Supplier<GlyphMagic> magic) {
        this.id = Objects.requireNonNull(GLYPH_REGISTRY.getKey(this));
        this.magic = magic;
        this.texture = id.withPrefix("textures.block");
    }

    public Component translatableName() {
        return Component.translatable(id.toLanguageKey("glyph"));
    }

    public GlyphMagic newMagic() {
        return magic.get();
    }
}