package org.kitowashere.boiled_witchcraft.world.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.FluidHandlerBlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kitowashere.boiled_witchcraft.client.gui.menu.SprayerMenu;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.registry.BlockEntityRegistry.SPRAYER_BLOCK_ENTITY;

public class SprayerBlockEntity extends FluidHandlerBlockEntity implements MenuProvider {
    public ItemStackHandler bucketSlot = new ItemStackHandler();

    public SprayerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SPRAYER_BLOCK_ENTITY.get(), pPos, pBlockState);

    }

    @Override
    public void load(CompoundTag tag) {
        bucketSlot.deserializeNBT(tag.getCompound("inventory"));
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", bucketSlot.serializeNBT());
        super.saveAdditional(tag);
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
