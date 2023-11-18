package org.kitowashere.boiled_witchcraft.world.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
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

import static net.minecraft.world.item.Items.BUCKET;
import static net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER;
import static net.minecraftforge.fluids.FluidType.BUCKET_VOLUME;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.SPRAYER_BLOCK_ENTITY;
import static org.kitowashere.boiled_witchcraft.registry.FluidRegistry.SOURCE_DTB;

public class SprayerBlockEntity extends FluidHandlerBlockEntity implements MenuProvider {
    public ItemStackHandler bucketSlot = new ItemStackHandler() {
        @Override
        public void setStackInSlot(int slot, @NotNull ItemStack stack) {
            super.setStackInSlot(slot, stack);
            if (
                stack.getItem() instanceof BucketItem bucket && bucket.getFluid() == SOURCE_DTB.get()
            ) {
                tank.fill(new FluidStack(bucket.getFluid(), BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
                setStackInSlot(0, new ItemStack(BUCKET));
            }

        }
    };

    public SprayerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SPRAYER_BLOCK_ENTITY.get(), pPos, pBlockState);
        super.tank = new FluidTank(BUCKET_VOLUME * 4);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        bucketSlot.deserializeNBT(tag.getCompound("inventory"));

    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inventory", bucketSlot.serializeNBT());
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
        if (capability == ITEM_HANDLER) return LazyOptional.of(() -> bucketSlot).cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block." + MODID + ".sprayer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SprayerMenu(pContainerId, pPlayerInventory, this);
    }
}
