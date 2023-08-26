package org.kitowashere.boiled_witchcraft.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.kitowashere.boiled_witchcraft.networking.packet.GlyphContextPacket;

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

        net.messageBuilder(GlyphContextPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <MSG> void sendToServer(MSG message) { INSTANCE.sendToServer(message); }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) { INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message); }
}