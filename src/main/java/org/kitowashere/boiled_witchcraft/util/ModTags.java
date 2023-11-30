package org.kitowashere.boiled_witchcraft.util;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidType;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class ModTags {
    public static class Fluids {
        public static final TagKey<Fluid> FLUID_DB = FluidTags.create(
                new ResourceLocation(MODID, "diluted_blood")
        );

        public static final TagKey<Fluid> FLUID_1_100_DB = FluidTags.create(
                new ResourceLocation(MODID, "diluted_blood/1-100")
        );

    }
}
