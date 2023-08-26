package org.kitowashere.boiled_witchcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.kitowashere.boiled_witchcraft.client.ClientGlyphContextData;

import java.util.function.Supplier;

public class GlyphContextPacket {
    private final String SELECTED_GLYPH;
    public GlyphContextPacket(String selectedGlyph) {
        SELECTED_GLYPH = selectedGlyph;
    }

    public GlyphContextPacket(FriendlyByteBuf buf) {
        SELECTED_GLYPH = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(SELECTED_GLYPH);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientGlyphContextData.setSelectedGlyph(SELECTED_GLYPH);
        });
        ctx.get().setPacketHandled(true);
    }
}
