package org.kitowashere.boiled_witchcraft.core.glyph;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.group.GlyphGroup;

import java.util.Collection;
import java.util.Optional;

import static org.kitowashere.boiled_witchcraft.core.glyph.GlyphType.GLYPH_REGISTRY;

public class GlyphTypeProperty extends Property<GlyphGroup> {

    protected GlyphTypeProperty(String pName, Class<GlyphGroup> pClazz) {
        super(pName, pClazz);
    }

    @Override
    public Collection<GlyphGroup> getPossibleValues() {
        return null;
    }

    @Override
    public String getName(GlyphGroup p_61696_) {
        return null;
    }

    @Override
    public Optional<GlyphGroup> getValue(String pValue) {
        return Optional.empty();
    }
}
