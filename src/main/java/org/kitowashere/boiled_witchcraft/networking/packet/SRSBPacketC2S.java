package org.kitowashere.boiled_witchcraft.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.kitowashere.boiled_witchcraft.world.blocks.entities.SprayerBlockEntity;

import java.util.function.Supplier;


public class SRSBPacketC2S {

    private final GlobalPos sprayerPos;
    private final int range;

    public SRSBPacketC2S(Level level, BlockPos pos, int range) {
        sprayerPos = GlobalPos.of(level.dimension(), pos);
        this.range = range;
    }

    public SRSBPacketC2S(FriendlyByteBuf buf) {
        sprayerPos = buf.readGlobalPos();
        range = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeGlobalPos(sprayerPos);
        buf.writeInt(range);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerLevel level = ServerLifecycleHooks.getCurrentServer().getLevel(sprayerPos.dimension());

            if (level != null && level.getBlockEntity(sprayerPos.pos()) instanceof SprayerBlockEntity sprayer) {
                sprayer.setRange(range);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
