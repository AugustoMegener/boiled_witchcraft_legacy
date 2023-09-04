package org.kitowashere.boiled_witchcraft.core.glyph.context;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class GlyphContext implements INBTSerializable<CompoundTag>, IGuiOverlay {
    public abstract void fromBytes(FriendlyByteBuf buf);

    public abstract void toBytes(FriendlyByteBuf buf);

    public abstract void configure();
}
