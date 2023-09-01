package org.kitowashere.boiled_witchcraft;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.kitowashere.boiled_witchcraft.networking.ModMessages;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;

import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.BLOCK_ENTITIES;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.BLOCKS;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.ITEMS;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.ENTITIES;

import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.PENCIL;

@Mod(BoiledWitchcraft.MODID)
public class BoiledWitchcraft {
    public static final String MODID = "boiled_witchcraft";

    public BoiledWitchcraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::commonSetup);

        GlyphTypeRegistry.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModMessages::register);
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(PENCIL);
        }
    }
}
