package org.kitowashere.boiled_witchcraft.registry;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.DTB_BLOCK;
import static org.kitowashere.boiled_witchcraft.registry.FluidTypeRegistry.DTB_FLUID_TYPE;
import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.DTB_BUCKET;

public class FluidRegistry {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_DTB = FLUIDS.register(
            "diluted_titan_blood_source",
            () -> new ForgeFlowingFluid.Source(FluidRegistry.DTB_PROPERTIES)
    );

    public static final RegistryObject<FlowingFluid> FLOWING_DTB = FLUIDS.register(
            "flowing_diluted_titan_blood",
            () -> new ForgeFlowingFluid.Flowing(FluidRegistry.DTB_PROPERTIES)
    );

    public static final ForgeFlowingFluid.Properties DTB_PROPERTIES = new ForgeFlowingFluid.Properties(
            DTB_FLUID_TYPE, SOURCE_DTB, FLOWING_DTB)
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(DTB_BLOCK).bucket(DTB_BUCKET);
}
