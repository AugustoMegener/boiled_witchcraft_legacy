package org.kitowashere.boiled_witchcraft.event.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.kitowashere.boiled_witchcraft.client.ClientGCTX;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphGroup;
import org.kitowashere.boiled_witchcraft.core.glyph.GlyphType;
import org.kitowashere.boiled_witchcraft.core.glyph.context.GlyphContext;
import org.kitowashere.boiled_witchcraft.networking.ModMessages;
import org.kitowashere.boiled_witchcraft.networking.packet.GCTXPacketC2S;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.PENCIL;
import static org.kitowashere.boiled_witchcraft.util.KeyBinding.*;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ForgeEvent {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (
                player != null && (player.getItemInHand(InteractionHand.MAIN_HAND).is(PENCIL.get()) || player.getItemInHand(InteractionHand.MAIN_HAND).is(PENCIL.get()))
        ) {
            boolean clicked = false;

            if (WRAP_GLYPH_KEY.consumeClick()) {
                GlyphType next = GlyphGroup.PRIMAL.nextOrFirst(ClientGCTX.getSelectedGlyph());

                ClientGCTX.setSelectedGlyph(next);
                ClientGCTX.setGlyphMagic(next.newMagic());

                clicked = true;
            }
            if (CONFIG_UP_KEY.consumeClick()) {
                ClientGCTX.getGlyphMagic().CONTEXT_KIT[0].configure(GlyphContext.InputConfigType.UP);
                clicked = true;
            }
            if (CONFIG_DOWN_KEY.consumeClick()) {
                ClientGCTX.getGlyphMagic().CONTEXT_KIT[0].configure(GlyphContext.InputConfigType.DOWN);
                clicked = true;
            }

            if (clicked) ModMessages.sendToServer(new GCTXPacketC2S(ClientGCTX.getSelectedGlyph(), ClientGCTX.getGlyphMagic()));
        }
    }
}
