package org.kitowashere.boiled_witchcraft.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.items.Registry.ITEMS;

public class Registry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> GLYPH = BLOCKS.register("glyph", () -> new Glyph());
    public static final RegistryObject<Item> GLYPH_ITEM =
        ITEMS.register("fire_glyph",
            () -> new BlockItem(GLYPH.get(), new Item.Properties())
        );

    public static final RegistryObject<Block> GFM = BLOCKS.register("grounded_fire_magic", () -> new GroundedFireMagic());
    public static final RegistryObject<Item> GFM_ITEM =
            ITEMS.register("grounded_fire_magic",
                    () -> new BlockItem(GFM.get(), new Item.Properties())
            );

    public static final RegistryObject<Block> GIM = BLOCKS.register("grounded_ice_magic", () -> new GroundedIceMagic());
    public static final RegistryObject<Item> GIM_ITEM =
            ITEMS.register("grounded_ice_magic",
                    () -> new BlockItem(GIM.get(), new Item.Properties())
            );
}
