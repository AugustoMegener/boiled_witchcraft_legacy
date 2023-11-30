package org.kitowashere.boiled_witchcraft.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.kitowashere.boiled_witchcraft.client.gui.overlay.GlyphSelector;
import org.kitowashere.boiled_witchcraft.networking.ModMessages;
import org.kitowashere.boiled_witchcraft.networking.packet.BDCPacketS2C;
import org.kitowashere.boiled_witchcraft.networking.packet.GCTXPacketS2C;
import org.kitowashere.boiled_witchcraft.core.blood.BloodDensityProvider;
import org.kitowashere.boiled_witchcraft.world.items.Pencil;
import org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx.PlayerGCTXProvider;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;
import static net.minecraftforge.event.TickEvent.Phase.START;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.core.blood.BloodDensityProvider.TITAN_BLOOD_HANDLER;

@Mod.EventBusSubscriber(modid = MODID)
public class ModEvent {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).isPresent()) {
                event.addCapability(new ResourceLocation(MODID, "proprieties/pgctx"), new PlayerGCTXProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesChunk(AttachCapabilitiesEvent<LevelChunk> event) {
        if (!event.getObject().getCapability(TITAN_BLOOD_HANDLER).isPresent()) {
            event.addCapability(new ResourceLocation(MODID, "proprieties/blood_density"), new BloodDensityProvider(event.getObject()));
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).ifPresent(
            oldStore -> event.getOriginal().getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).ifPresent(
                newStore -> {
                    newStore.copyFrom(oldStore);
                    ModMessages.sendToPlayer(
                        new GCTXPacketS2C(oldStore.getSelectedGlyph(), oldStore.getGlyphMagic()),
                        (ServerPlayer) event.getEntity()
                    );
                }
            )
        );
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerGCTXProvider.class);
        event.register(BloodDensityProvider.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (event.side.isClient() && event.phase == START) {
            boolean enabled = false;

            for (Item i : new Item[]{player.getItemInHand(MAIN_HAND).getItem(), player.getItemInHand(OFF_HAND).getItem()}) {
                if (i instanceof Pencil) { enabled = true; break; }
            }

            GlyphSelector.setEnabled(enabled);
        }
        if (event.side.isServer() && event.phase == START) {
            player.level.getChunkAt(player.blockPosition()).getCapability(TITAN_BLOOD_HANDLER).ifPresent(cap -> {
                ModMessages.sendToPlayer(new BDCPacketS2C(cap.getAmount()), (ServerPlayer) player);
            });
        }
    }
}
