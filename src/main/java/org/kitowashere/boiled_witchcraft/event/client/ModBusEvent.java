package org.kitowashere.boiled_witchcraft.event.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.kitowashere.boiled_witchcraft.client.models.ThrowableMagicModel;
import org.kitowashere.boiled_witchcraft.client.overlays.GlyphSelector;
import org.kitowashere.boiled_witchcraft.client.overlays.PaperInfo;
import org.kitowashere.boiled_witchcraft.client.renderer.ThrowableMagicRenderer;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.*;
import static org.kitowashere.boiled_witchcraft.util.KeyBinding.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBusEvent {

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
        event.registerAboveAll("glyph_selector", GlyphSelector::render);
        event.registerAboveAll("glyph_paper_info", PaperInfo::render);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(CONFIG_UP_KEY);
        event.register(CONFIG_DOWN_KEY);
        event.register(WRAP_GLYPH_KEY);
    }
}
