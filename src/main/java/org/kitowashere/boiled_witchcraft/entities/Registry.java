package org.kitowashere.boiled_witchcraft.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class Registry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<Spark>> SPARK = ENTITIES.register(
            "spark",
            () -> EntityType.Builder.of(Spark::new, MobCategory.MISC).build(MODID + "spark")
    );
}
