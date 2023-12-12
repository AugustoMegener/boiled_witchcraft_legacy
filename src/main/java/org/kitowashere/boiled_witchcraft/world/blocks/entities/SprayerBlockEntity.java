package org.kitowashere.boiled_witchcraft.world.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.FluidHandlerBlockEntity;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kitowashere.boiled_witchcraft.client.gui.menu.SprayerMenu;
import org.kitowashere.boiled_witchcraft.networking.ModMessages;
import org.kitowashere.boiled_witchcraft.networking.packet.STGGPacketC2S;
import org.kitowashere.boiled_witchcraft.core.blood.ITitanBloodHandler;

import static net.minecraft.world.item.Items.BUCKET;
import static net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER;
import static net.minecraftforge.fluids.FluidType.BUCKET_VOLUME;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.data.server.FBDResourceReloadListener.getFluidAmount;
import static org.kitowashere.boiled_witchcraft.data.server.FBDResourceReloadListener.getFluidDensity;
import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.SPRAYER_BLOCK_ENTITY;
import static org.kitowashere.boiled_witchcraft.core.blood.BloodDensityProvider.TITAN_BLOOD_HANDLER;

public class SprayerBlockEntity extends FluidHandlerBlockEntity implements MenuProvider {

    private int coolDown;
    private int range = 1;

    public ItemStackHandler bucketSlot = new ItemStackHandler() {
        @Override
        public void setStackInSlot(int slot, @NotNull ItemStack stack) {
            super.setStackInSlot(slot, stack);
            if (
                stack.getItem() instanceof BucketItem bucket && getFluidDensity(bucket.getFluid()) != 0
            ) {
                if (
                    tank.fill(new FluidStack(bucket.getFluid(), BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE) > 0
                ) setStackInSlot(0, new ItemStack(BUCKET));
            }

            setChanged();
        }
    };

    public ContainerData data = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> coolDown;
                case 1 -> Math.max(range, 1);
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> coolDown = pValue;
                case 1 -> range = Math.max(pValue, 1);
                default -> {}
            }

            setChanged();
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    private int getBloodAmount() {
        return (int) (tank.getFluidAmount() * getFluidDensity(tank.getFluid()));
    }

    private int getBloodPerChunk() {
        return getBloodAmount() / range ^ 2;
    }

    public SprayerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SPRAYER_BLOCK_ENTITY.get(), pPos, pBlockState);
        super.tank = new FluidTank(BUCKET_VOLUME * 4);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag.getCompound("tank"));
        bucketSlot.deserializeNBT(tag.getCompound("inventory"));
        range = tag.getInt("range");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag.getCompound("tank"));
        tag.put("inventory", bucketSlot.serializeNBT());
        tag.putInt("range", range);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
        if (capability == ITEM_HANDLER) return LazyOptional.of(() -> bucketSlot).cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block." + MODID + ".sprayer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(
            int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer
    ) {
        return new SprayerMenu(pContainerId, pPlayerInventory, this, data);
    }

    public boolean canSpray() {
        return getBloodPerChunk() > 0;
    }

    public void spray() {
        if (level != null && canSpray()) {
            LazyOptional<ITitanBloodHandler> capability =
                level.getChunkAt(getBlockPos()).getCapability(TITAN_BLOOD_HANDLER);

            capability.ifPresent(handler -> handler.add(
                tank.drain(
                    getFluidAmount(getBloodPerChunk(), tank.getFluid()),
                    IFluidHandler.FluidAction.EXECUTE
                ).getAmount()
            ));
        }

        if (level.isClientSide) { ModMessages.sendToServer(new STGGPacketC2S(level, getBlockPos())); }
    }

    public static <T extends BlockEntity> void serverTick(Level level, BlockPos blockPos, BlockState state, T be) {
        if (be instanceof SprayerBlockEntity sprayer) {
            ContainerData data = sprayer.data;

            if (data.get(0) > 0) {
                data.set(0, data.get(0) - 1);
            }
        }
    }

    public void setRange(int range) {
        this.range = range;
    }
}
