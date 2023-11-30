package org.kitowashere.boiled_witchcraft.core.blood;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class BloodDensityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ITitanBloodHandler> TITAN_BLOOD_HANDLER = CapabilityManager.get(new CapabilityToken<>() {});
    private final LevelChunk chunk;

    private TitanBloodDensity density;
    private final LazyOptional<TitanBloodDensity> optional = LazyOptional.of(this::getDensity);

    public BloodDensityProvider(LevelChunk chunk) {
        this.chunk = chunk;
    }

    public TitanBloodDensity getDensity() {
        if (density == null) density = new TitanBloodDensity(chunk); return density;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == TITAN_BLOOD_HANDLER) return optional.cast();
        else return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("density", getDensity().getAmount());

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getDensity().set(nbt.getInt("density"));
    }
}
