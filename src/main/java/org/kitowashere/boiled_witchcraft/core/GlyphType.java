package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public record GlyphType(@NotNull String name, @NotNull Supplier<GlyphMagic> magic, @NotNull ResourceLocation texture) {
    public static final DeferredRegister<GlyphType> GLYPH_REGISTER = DeferredRegister.create(new ResourceLocation(MODID, "GLYPH"), MODID);
    public static final Supplier<IForgeRegistry<GlyphType>> GLYPH_REGISTER_SUPPLIER = GLYPH_REGISTER.makeRegistry(RegistryBuilder::new);

    public Component translatableName() {
        return Component.translatable(MODID + ".glyph_type." + name);
    }

    public GlyphMagic newMagic() { return magic.get(); }
}