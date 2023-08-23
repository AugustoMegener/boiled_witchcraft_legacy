package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.blocks.*;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.ITEMS;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> GLYPH = BLOCKS.register("glyph", GlyphBlock::new);
    public static final RegistryObject<Item> GLYPH_ITEM =
        ITEMS.register("glyph",
            () -> new BlockItem(GLYPH.get(), new Item.Properties())
        );

    public static final RegistryObject<Block> SFM = BLOCKS.register("grounded_fire_magic", SurfacedFireMagic::new);
    public static final RegistryObject<Item> GFM_ITEM =
            ITEMS.register("grounded_fire_magic",
                    () -> new BlockItem(SFM.get(), new Item.Properties())
            );

    public static final RegistryObject<Block> SIM = BLOCKS.register("grounded_ice_magic", SurfacedIceMagic::new);
    public static final RegistryObject<Item> GIM_ITEM =
            ITEMS.register("grounded_ice_magic",
                    () -> new BlockItem(SIM.get(), new Item.Properties())
            );

    public static final RegistryObject<Block> SPM = BLOCKS.register("grounded_plant_magic", SurfacedPlantMagic::new);
    public static final RegistryObject<Item> GPM_ITEM =
            ITEMS.register("grounded_plant_magic",
                    () -> new BlockItem(SPM.get(), new Item.Properties())
            );

    public static final RegistryObject<Block> SLM = BLOCKS.register("spark", SurfacedLightMagic::new);
    public static final RegistryObject<Item> SPARK_ITEM =
            ITEMS.register("spark",
                    () -> new BlockItem(SLM.get(), new Item.Properties())
            );
}
