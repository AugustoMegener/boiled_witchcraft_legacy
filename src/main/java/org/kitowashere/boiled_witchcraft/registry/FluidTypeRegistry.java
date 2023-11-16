package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;
import org.kitowashere.boiled_witchcraft.world.fluids.BaseFluidType;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class FluidTypeRegistry {
    public static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING = new ResourceLocation("block/water_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MODID);

    public static final RegistryObject<BaseFluidType> DTB_FLUID_TYPE = FLUID_TYPES.register(
            "diluted_titan_blood_fluid",
            () -> new BaseFluidType(WATER_STILL, WATER_FLOWING, WATER_STILL, 0x8465ec, new Vector3f(0.5176f, 0.3961f, 0.9255f), FluidType.Properties.create()
                    .lightLevel(2)
                    .density(1)
                    .viscosity(1)
            )
    );
}