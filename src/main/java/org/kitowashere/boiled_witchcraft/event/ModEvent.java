package org.kitowashere.boiled_witchcraft.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;
import static net.minecraftforge.event.TickEvent.Phase.START;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.kitowashere.boiled_witchcraft.client.overlays.GlyphSelector;
import org.kitowashere.boiled_witchcraft.world.items.Pencil;
import org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx.PlayerGCTXProvider;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ModEvent {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).isPresent()) {
                event.addCapability(new ResourceLocation(MODID, "proprieties"), new PlayerGCTXProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).ifPresent(
            oldStore -> event.getOriginal().getCapability(PlayerGCTXProvider.PLAYER_CONTEXT).ifPresent(
                    newStore -> newStore.copyFrom(oldStore)
            )
        );
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerGCTXProvider.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side.isClient() && event.phase == START) {
            boolean enabled = false;

            for (Item i : new Item[]{event.player.getItemInHand(MAIN_HAND).getItem(), event.player.getItemInHand(OFF_HAND).getItem()}) {
                if (i instanceof Pencil) { enabled = true; break; }
            }

            GlyphSelector.setEnabled(enabled);
        }
     }
}
