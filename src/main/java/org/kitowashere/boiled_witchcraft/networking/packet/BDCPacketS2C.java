package org.kitowashere.boiled_witchcraft.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.kitowashere.boiled_witchcraft.client.gui.overlay.TitanBloodTokens;

import java.util.function.Supplier;


public class BDCPacketS2C {
    private final int density;

    public BDCPacketS2C(int density) {
        this.density = density;
    }

    public BDCPacketS2C(FriendlyByteBuf buf) {
        this.density = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(density);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context event = ctx.get();

        event.enqueueWork(() -> TitanBloodTokens.amount = density);

        event.setPacketHandled(true);
    }
}
