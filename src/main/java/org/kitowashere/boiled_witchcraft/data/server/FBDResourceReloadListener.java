package org.kitowashere.boiled_witchcraft.data.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static org.kitowashere.boiled_witchcraft.registry.FluidTypeRegistry.FLUID_TYPES;

public class FBDResourceReloadListener extends SimpleJsonResourceReloadListener {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final HashMap<FluidType, Double> RESOURCES = new HashMap<>();

    public FBDResourceReloadListener() {
        super(new GsonBuilder().setPrettyPrinting().create(), "fluid/titan_blood_density");
    }

    @Override
    protected void apply(
        @NotNull Map<ResourceLocation, JsonElement> pObject,
        @NotNull ResourceManager pResourceManager,
        @NotNull ProfilerFiller pProfiler
    ) {
        RESOURCES.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : pObject.entrySet()) {
            JsonElement json = entry.getValue();

            String[] resourceId = json.getAsJsonObject().get("id").getAsString().split(":");
            ResourceLocation resourceLocation = new ResourceLocation(resourceId[0], resourceId[1]);

            @Nullable FluidType fluid = ForgeRegistries.FLUID_TYPES.get().getValue(resourceLocation);
            double density = json.getAsJsonObject().get("dilution").getAsDouble();

            if (fluid != null && density != 0) { RESOURCES.put(fluid, density); }
        }
    }

    public static double getFluidDensity(FluidType fluidType) {
        return RESOURCES.get(fluidType) != null ? RESOURCES.get(fluidType) : 0;
    }

    public static double getFluidDensity(Fluid fluid) {
        return getFluidDensity(fluid.getFluidType());
    }

    public static double getFluidDensity(FluidStack fluidStack) {
        return getFluidDensity(fluidStack.getFluid());
    }
}
