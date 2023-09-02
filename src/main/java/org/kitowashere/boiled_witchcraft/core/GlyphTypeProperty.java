package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public class GlyphTypeProperty extends Property<GlyphType> {
    public GlyphTypeProperty(String pName, Class<GlyphType> pClazz) {
        super(pName, pClazz);
    }

    @Override
    public @NotNull Collection<GlyphType> getPossibleValues() {
        return GlyphType.getAllGlyphTypes();
    }

    @Override
    public @NotNull String getName(@NotNull GlyphType glyphType) {
        return glyphType.name();
    }

    @Override
    public @NotNull Optional<GlyphType> getValue(@NotNull String pValue) {
        return Optional.ofNullable(GlyphType.fromString(pValue));
    }
}
