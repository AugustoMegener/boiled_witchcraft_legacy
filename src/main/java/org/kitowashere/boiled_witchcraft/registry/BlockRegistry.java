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

    public static final RegistryObject<Block> GLYPH = BLOCKS.register("glyph", Glyph::new);
    public static final RegistryObject<Item> GLYPH_ITEM =
        ITEMS.register("glyph",
            () -> new BlockItem(GLYPH.get(), new Item.Properties())
        );

    public static final RegistryObject<Block> GFM = BLOCKS.register("grounded_fire_magic", GroundedFireMagic::new);
    public static final RegistryObject<Item> GFM_ITEM =
            ITEMS.register("grounded_fire_magic",
                    () -> new BlockItem(GFM.get(), new Item.Properties())
            );

    public static final RegistryObject<Block> GIM = BLOCKS.register("grounded_ice_magic", GroundedIceMagic::new);
    public static final RegistryObject<Item> GIM_ITEM =
            ITEMS.register("grounded_ice_magic",
                    () -> new BlockItem(GIM.get(), new Item.Properties())
            );

    public static final RegistryObject<Block> GPM = BLOCKS.register("grounded_plant_magic", GroundedPlantMagic::new);
    public static final RegistryObject<Item> GPM_ITEM =
            ITEMS.register("grounded_plant_magic",
                    () -> new BlockItem(GPM.get(), new Item.Properties())
            );

    public static final RegistryObject<Block> SPARK = BLOCKS.register("spark", Spark::new);
    public static final RegistryObject<Item> SPARK_ITEM =
            ITEMS.register("spark",
                    () -> new BlockItem(SPARK.get(), new Item.Properties())
            );
}
