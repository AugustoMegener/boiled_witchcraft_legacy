package org.kitowashere.boiled_witchcraft.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.kitowashere.boiled_witchcraft.client.models.ThrowableMagicModel;
import org.kitowashere.boiled_witchcraft.client.renderer.ThrowableMagicRenderer;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThrowableMagicModel.LAYER_LOCATION, ThrowableMagicModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                TFM.get(),
                pContext -> new ThrowableMagicRenderer<>(pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/fire.png"))
        );

        event.registerEntityRenderer(
                TIM.get(),
                pContext -> new ThrowableMagicRenderer<>(pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/ice.png"))
        );

        event.registerEntityRenderer(
                TLM.get(),
                pContext -> new ThrowableMagicRenderer<>(pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/light.png"))
        );

        event.registerEntityRenderer(
                TPM.get(),
                pContext -> new ThrowableMagicRenderer<>(pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/plant.png"))
        );
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("glyph_selector", Overlays.GLYPH_SELECTOR);
    }
}
