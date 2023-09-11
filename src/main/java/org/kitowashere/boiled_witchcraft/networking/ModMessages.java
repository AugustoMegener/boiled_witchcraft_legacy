package org.kitowashere.boiled_witchcraft.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.kitowashere.boiled_witchcraft.networking.packet.GCTXPacketC2S;
import org.kitowashere.boiled_witchcraft.networking.packet.GCTXPacketS2C;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return  packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MODID, "messages"))
                .networkProtocolVersion(() -> "1.8")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(GCTXPacketS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(GCTXPacketS2C::new)
                .encoder(GCTXPacketS2C::toBytes)
                .consumerMainThread(GCTXPacketS2C::handle)
                .add();

        net.messageBuilder(GCTXPacketC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(GCTXPacketC2S::new)
                .encoder(GCTXPacketC2S::toBytes)
                .consumerMainThread(GCTXPacketC2S::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) { INSTANCE.sendToServer(message); }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) { INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message); }
}