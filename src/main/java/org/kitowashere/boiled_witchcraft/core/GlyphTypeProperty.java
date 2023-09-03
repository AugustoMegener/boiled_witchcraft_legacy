package org.kitowashere.boiled_witchcraft.core;

import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public class GlyphTypeProperty<T extends GlyphTypeGroup<T>> extends Property<T> {


    protected GlyphTypeProperty(String pName, Class<T> pClazz) {
        super(pName, pClazz);
    }

    @Override
    public Collection<T> getPossibleValues() {
        return null;
    }

    @Override
    public String getName(T p_61696_) {
        return null;
    }

    @Override
    public Optional<T> getValue(String pValue) {
        return Optional.empty();
    }
}
