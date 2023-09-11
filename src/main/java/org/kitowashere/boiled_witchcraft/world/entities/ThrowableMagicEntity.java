package org.kitowashere.boiled_witchcraft.world.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;

import java.util.Optional;

public abstract class ThrowableMagicEntity extends AbstractArrow {
    private GlyphMagic magic;

    public ThrowableMagicEntity(EntityType<ThrowableMagicEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() { return ItemStack.EMPTY; }

    public Optional<GlyphMagic> getMagic() {
        return Optional.ofNullable(magic);
    }

    public void setMagic(GlyphMagic glyphMagic) {
        magic = glyphMagic;
    }
}
