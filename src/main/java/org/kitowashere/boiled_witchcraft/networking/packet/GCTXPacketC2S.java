package org.kitowashere.boiled_witchcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.magic.GlyphMagic;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;

import java.util.Optional;
import java.util.function.Supplier;

import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.FIRE_GLYPH;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.GLYPH_REGISTRY;
import static org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx.PlayerGCTXProvider.PLAYER_CONTEXT;

public class GCTXPacketC2S {
    private final GlyphType SELECTED_GLYPH;
    private final GlyphMagic MAGIC;

    public GCTXPacketC2S(GlyphType selectedGlyph) {
        SELECTED_GLYPH = selectedGlyph;
        MAGIC = selectedGlyph.newMagic();
    }

    public GCTXPacketC2S(GlyphType selectedGlyph, GlyphMagic magic) {
        SELECTED_GLYPH = selectedGlyph;
        MAGIC = magic;
    }

    public GCTXPacketC2S(FriendlyByteBuf buf) {
        GlyphType new_glyph = GLYPH_REGISTRY.get().getValue(buf.readResourceLocation());

        SELECTED_GLYPH = new_glyph!=null ? new_glyph : FIRE_GLYPH.get();
        MAGIC = SELECTED_GLYPH.newMagic();
        MAGIC.fromBytes(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(GlyphTypeRegistry.getGlyphTypeResourceLocation(SELECTED_GLYPH));
        MAGIC.toBytes(buf);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context event = ctx.get();
        ctx.get().enqueueWork(() -> {
            Optional.ofNullable(event.getSender()).ifPresent(x -> x.getCapability(PLAYER_CONTEXT).ifPresent(y -> {
                y.setSelectedGlyph(SELECTED_GLYPH);
                y.setMagic(MAGIC);
            }));

            ctx.get().setPacketHandled(true);
        });
    }
}
