package org.kitowashere.boiled_witchcraft;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.kitowashere.boiled_witchcraft.networking.ModMessages;

import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.BLOCK_ENTITIES;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.BLOCKS;
import static org.kitowashere.boiled_witchcraft.registry.EntityRegistry.ENTITIES;
import static org.kitowashere.boiled_witchcraft.registry.FluidRegistry.FLUIDS;
import static org.kitowashere.boiled_witchcraft.registry.FluidTypeRegistry.FLUID_TYPES;
import static org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry.GLYPH_TYPES;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.ITEMS;
import static org.kitowashere.boiled_witchcraft.registry.MenuTypeRegistry.MENUS;

@Mod(BoiledWitchcraft.MODID)
public class BoiledWitchcraft {
    public static final String MODID = "boiled_witchcraft";

    public BoiledWitchcraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GLYPH_TYPES.register(modEventBus);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        FLUID_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        MENUS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { event.enqueueWork(ModMessages::register); }
}