package org.kitowashere.boiled_witchcraft.core.glyph.context;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class GlyphContext implements INBTSerializable<CompoundTag>, IGuiOverlay {

    public abstract int guiHeight();
    public abstract int guiWidth();

    public abstract Component translatableName();

    public abstract Component[] toComponents();

    public abstract void fromBytes(FriendlyByteBuf buf);

    public abstract void toBytes(FriendlyByteBuf buf);

    public abstract void configure(InputConfigType input);

    public enum InputConfigType { UP, DOWN }
}
