package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.items.Pencil;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class ItemRegistry {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        public static final RegistryObject<Item> PENCIL = ITEMS.register("pencil", () -> new Pencil());
}
