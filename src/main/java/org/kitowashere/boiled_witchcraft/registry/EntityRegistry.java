package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.world.entities.*;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<ThrowableMagicEntity>> TFM = ENTITIES.register("throwable_fire_magic",
            () -> EntityType.Builder.of(ThrowableFireMagic::new, MobCategory.MISC)
                    .sized(0.375f, 0.375f)
                    .fireImmune()
                    .build(new ResourceLocation(MODID, "textures/throwable_magic/fire.png").toString())
    );

    public static final RegistryObject<EntityType<ThrowableMagicEntity>> TIM = ENTITIES.register("throwable_ice_magic",
            () -> EntityType.Builder.of(ThrowableIceMagic::new, MobCategory.MISC)
                    .sized(0.375f, 0.375f)
                    .build(new ResourceLocation(MODID, "textures/throwable_magic/ice.png").toString())
    );

    public static final RegistryObject<EntityType<ThrowableMagicEntity>> TLM = ENTITIES.register("throwable_light_magic",
            () -> EntityType.Builder.of(ThrowableLightMagic::new, MobCategory.MISC)
                    .sized(0.375f, 0.375f)
                    .build(new ResourceLocation(MODID, "textures/throwable_magic/light.png").toString())
    );

    public static final RegistryObject<EntityType<ThrowableMagicEntity>> TPM = ENTITIES.register("throwable_plant_magic",
            () -> EntityType.Builder.of(ThrowablePlantMagic::new, MobCategory.MISC)
                    .sized(0.375f, 0.375f)
                    .build(new ResourceLocation(MODID, "textures/throwable_magic/plant.png").toString())
    );
}
