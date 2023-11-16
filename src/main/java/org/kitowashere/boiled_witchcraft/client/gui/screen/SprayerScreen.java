package org.kitowashere.boiled_witchcraft.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.client.gui.menu.SprayerMenu;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class SprayerScreen extends AbstractContainerScreen<SprayerMenu> {
    private static final ResourceLocation SPRAYER_STAND_LOCATION = new ResourceLocation(MODID,"textures/gui/sprayer.png");
    public SprayerScreen(SprayerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        addWidget(new FluidDisplayWidget(9, 10, 14, 67, pMenu.tankHandler));
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, SPRAYER_STAND_LOCATION);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        blit(pPoseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
