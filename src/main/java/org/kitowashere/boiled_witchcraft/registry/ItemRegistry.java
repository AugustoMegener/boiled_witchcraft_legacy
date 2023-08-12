package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.items.*;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class ItemRegistry {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> PENCIL = ITEMS.register("pencil", Pencil::new);
        public static final RegistryObject<Item> GLYPH_ON_A_PAPER = ITEMS.register("glyph_paper", GlyphOnAPaper::new);
}
