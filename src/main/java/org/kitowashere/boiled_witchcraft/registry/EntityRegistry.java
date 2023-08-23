package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.kitowashere.boiled_witchcraft.entities.ThrowableFireMagic;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<ThrowableFireMagic>> TFM = ENTITIES.register("magic_fire_ball",
            () -> EntityType.Builder.of(ThrowableFireMagic::new, MobCategory.MISC)
                    .sized(0.375f, 0.375f)
                    .fireImmune()
                    .build(new ResourceLocation(MODID, "textures/throwable_magic/fire").toString())
    );
}
