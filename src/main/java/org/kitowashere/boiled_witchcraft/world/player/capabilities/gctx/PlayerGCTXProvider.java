package org.kitowashere.boiled_witchcraft.world.player.capabilities.gctx;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerGCTXProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerGCTX> PLAYER_CONTEXT = CapabilityManager.get(new CapabilityToken<>() { });

    private PlayerGCTX context = null;
    private final LazyOptional<PlayerGCTX> optional = LazyOptional.of(this::createPlayerGCTX);

    private PlayerGCTX createPlayerGCTX() {
        if (context==null) context = new PlayerGCTX();
        return this.context;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap==PLAYER_CONTEXT) return optional.cast();
        else return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerGCTX().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerGCTX().loadNBTData(nbt);
    }
}
