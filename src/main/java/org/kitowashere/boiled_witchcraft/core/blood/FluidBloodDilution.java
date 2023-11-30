package org.kitowashere.boiled_witchcraft.core.blood;

import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.transformer.ext.IExtensionRegistry;

public class FluidBloodDilution  {

    private final double dilutionFraction;
    private final Fluid fluid;

    public FluidBloodDilution(Fluid fluid, double dilutionFraction) {
        this.fluid = fluid;
        this.dilutionFraction = dilutionFraction;
    }
}
