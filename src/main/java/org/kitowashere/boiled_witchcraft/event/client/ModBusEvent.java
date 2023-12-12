package org.kitowashere.boiled_witchcraft.event.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.kitowashere.boiled_witchcraft.client.gui.overlay.GlyphSelector;
import org.kitowashere.boiled_witchcraft.client.gui.overlay.PaperInfo;
import org.kitowashere.boiled_witchcraft.client.gui.overlay.TitanBloodTokens;
import org.kitowashere.boiled_witchcraft.client.gui.screen.sprayer.SprayerScreen;
import org.kitowashere.boiled_witchcraft.client.models.ThrowableMagicModel;
import org.kitowashere.boiled_witchcraft.client.renderer.ThrowableMagicRenderer;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.SPRAYER_ITEM;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.*;
import static org.kitowashere.boiled_witchcraft.registry.FluidRegistry.FLOWING_DTB;
import static org.kitowashere.boiled_witchcraft.registry.FluidRegistry.SOURCE_DTB;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.*;
import static org.kitowashere.boiled_witchcraft.registry.MenuTypeRegistry.SPRAYER_MENU;
import static org.kitowashere.boiled_witchcraft.util.KeyBinding.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBusEvent {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(SOURCE_DTB.get(),   RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(FLOWING_DTB.get(),  RenderType.translucent());

            MenuScreens.register(SPRAYER_MENU.get(), SprayerScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThrowableMagicModel.LAYER_LOCATION, ThrowableMagicModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
            TFM.get(),
            pContext -> new ThrowableMagicRenderer<>(
                pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/fire.png")
            )
        );

        event.registerEntityRenderer(
            TIM.get(),
            pContext -> new ThrowableMagicRenderer<>(
                pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/ice.png")
            )
        );

        event.registerEntityRenderer(
            TLM.get(),
            pContext -> new ThrowableMagicRenderer<>(
                pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/light.png")
            )
        );

        event.registerEntityRenderer(
            TPM.get(),
            pContext -> new ThrowableMagicRenderer<>(
                pContext, new ResourceLocation(MODID, "textures/entity/throwable_magic/plant.png")
            )
        );
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("glyph_selector",        GlyphSelector::render);
        event.registerAboveAll("glyph_paper_info",          PaperInfo::render);
        event.registerAboveAll("titan_blood_tokens", TitanBloodTokens::render);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(CONFIG_UP_KEY);
        event.register(CONFIG_DOWN_KEY);
        event.register(WRAP_GLYPH_KEY);
    }

    public static CreativeModeTab MAIN_TAB;
    @SubscribeEvent
    public static void creativeModeTabRegister(CreativeModeTabEvent.Register event) {
        MAIN_TAB = event.registerCreativeModeTab(
        new ResourceLocation(MODID, "main_tab"),            builder -> builder
                .icon(GLYPH_ON_A_PAPER.get()::getDefaultInstance)
                .title(Component.literal("Boiled Witchcraft"))

                .displayItems(((pParameters, pOutput) -> {
                    pOutput.accept(PENCIL               .get().getDefaultInstance());
                    pOutput.accept(COPPER_N_GOLD_INGOTS .get().getDefaultInstance());
                    pOutput.accept(ROYAL_BRONZE_INGOT   .get().getDefaultInstance());
                    pOutput.accept(SPRAYER_ITEM         .get().getDefaultInstance());
                    pOutput.accept(DTB_BUCKET           .get().getDefaultInstance());
        })));
    }
}
