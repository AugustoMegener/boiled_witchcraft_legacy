package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.world.items.GlyphOnAPaper;
import org.kitowashere.boiled_witchcraft.world.items.Pencil;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.FluidRegistry.SOURCE_DTB;

public class ItemRegistry {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> PENCIL = ITEMS.register("pencil", Pencil::new);
        public static final RegistryObject<Item> GLYPH_ON_A_PAPER = ITEMS.register("glyph_paper", GlyphOnAPaper::new);
        public static final RegistryObject<Item> COPPER_N_GOLD_INGOTS = ITEMS.register("copper_gold_ingots",
                () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> ROYAL_BRONZE_INGOT = ITEMS.register("royal_bronze_ingot",
                () -> new Item(new Item.Properties()));

        public static final RegistryObject<BucketItem> DTB_BUCKET = ITEMS.register("diluted_titan_blood_bucket",
                () -> new BucketItem(SOURCE_DTB, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)
        ));
}
