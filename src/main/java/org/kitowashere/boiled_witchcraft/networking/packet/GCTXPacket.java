package org.kitowashere.boiled_witchcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.kitowashere.boiled_witchcraft.client.ClientGCTX;
import org.kitowashere.boiled_witchcraft.core.GlyphMagic;
import org.kitowashere.boiled_witchcraft.core.GlyphType;

import java.util.function.Supplier;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;

public class GCTXPacket {
    private final GlyphType SELECTED_GLYPH;
    private final GlyphMagic MAGIC;

    public GCTXPacket(GlyphType selectedGlyph) {
        SELECTED_GLYPH = selectedGlyph;
        MAGIC = selectedGlyph.newMagic();
    }

    public GCTXPacket(GlyphType selectedGlyph, GlyphMagic magic) {
        SELECTED_GLYPH = selectedGlyph;
        MAGIC = magic;
    }

    public GCTXPacket(FriendlyByteBuf buf) {
        GlyphType new_glyph = GlyphType.fromString(buf.readUtf());

        SELECTED_GLYPH = new_glyph!=null ? new_glyph : FIRE_GLYPH;
        MAGIC = SELECTED_GLYPH.newMagic();
        MAGIC.fromBytes(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(SELECTED_GLYPH.name());
        MAGIC.toBytes(buf);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientGCTX.setSelectedGlyph(SELECTED_GLYPH);
            ClientGCTX.setMagic(MAGIC);
        });
        ctx.get().setPacketHandled(true);
    }
}
