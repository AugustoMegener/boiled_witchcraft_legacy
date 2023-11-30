package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.client.gui.menu.SprayerMenu;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class MenuTypeRegistry {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    public static final RegistryObject<MenuType<SprayerMenu>> SPRAYER_MENU = MENUS.register("tank_menu", () -> IForgeMenuType.create((pContainerId, inv, buf) -> new SprayerMenu(pContainerId, inv, buf, data)));
}
