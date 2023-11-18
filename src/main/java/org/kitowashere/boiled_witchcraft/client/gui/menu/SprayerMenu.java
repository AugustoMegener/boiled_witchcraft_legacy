package org.kitowashere.boiled_witchcraft.client.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.world.blocks.entities.SprayerBlockEntity;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.FLUID_HANDLER;
import static net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER;
import static org.kitowashere.boiled_witchcraft.registry.BlockRegistry.SPRAYER;
import static org.kitowashere.boiled_witchcraft.registry.MenuTypeRegistry.SPRAYER_MENU;

public class SprayerMenu extends AbstractContainerMenu {
    private final SprayerBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    public Slot bucketSlot;
    public IFluidHandler tankHandler;

    public SprayerMenu(int pContainerId, Inventory inv, @NotNull FriendlyByteBuf buf) {
        this(pContainerId, inv, (SprayerBlockEntity) inv.player.level.getBlockEntity(buf.readBlockPos()));
    }
    public SprayerMenu(int pContainerId, Inventory inv, SprayerBlockEntity blockEntity) {
        super(SPRAYER_MENU.get(), pContainerId);
        this.blockEntity = blockEntity;

        assert blockEntity.getLevel() != null;
        levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        createPlayerHotBar(inv);
        createPlayerInventory(inv);
        createContainer(this.blockEntity);
    }

    private void createContainer(SprayerBlockEntity be) {
        be.getCapability(ITEM_HANDLER).ifPresent(iItemHandler ->
            bucketSlot = addSlot(new SlotItemHandler(iItemHandler, 0, 44, 53))
        );
        be.getCapability(FLUID_HANDLER).ifPresent(iFluidHandler ->
            tankHandler = iFluidHandler
        );
    }

    private void createPlayerInventory(Inventory inv) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void createPlayerHotBar(Inventory inv) {
        for(int k = 0; k < 9; ++k) { this.addSlot(new Slot(inv, k, 8 + k * 18, 142)); }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(levelAccess, pPlayer, SPRAYER.get());
    }
}
