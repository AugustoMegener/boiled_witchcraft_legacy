package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.entities.MagicFireBallEntity;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<MagicFireBallEntity>> TFM = ENTITIES.register("magic_fire_ball",
            () -> EntityType.Builder.of(MagicFireBallEntity::new, MobCategory.MISC).build(new ResourceLocation(MODID, "textures/throwable_magic/fire").toString())
    );
}
