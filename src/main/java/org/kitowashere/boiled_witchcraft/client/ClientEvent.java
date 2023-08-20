package org.kitowashere.boiled_witchcraft.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.kitowashere.boiled_witchcraft.client.models.MagicFireBallModel;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MagicFireBallModel.LAYER_LOCATION, MagicFireBallModel::createBodyLayer);
    }
}
