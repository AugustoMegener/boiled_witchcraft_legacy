package org.kitowashere.boiled_witchcraft.networking.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.kitowashere.boiled_witchcraft.BoiledWitchcraft;
import org.kitowashere.boiled_witchcraft.world.blocks.entities.SprayerBlockEntity;

import java.awt.*;
import java.util.function.Supplier;


public class STGGPacketC2S {

    private final GlobalPos sprayerPos;

    public STGGPacketC2S(Level level, BlockPos pos) {
        sprayerPos = GlobalPos.of(level.dimension(), pos);
    }

    public STGGPacketC2S(FriendlyByteBuf buf) {
        sprayerPos = buf.readGlobalPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeGlobalPos(sprayerPos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerLevel level = ServerLifecycleHooks.getCurrentServer().getLevel(sprayerPos.dimension());

            if (level != null && level.getBlockEntity(sprayerPos.pos()) instanceof SprayerBlockEntity sprayer) {
                sprayer.spray();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
