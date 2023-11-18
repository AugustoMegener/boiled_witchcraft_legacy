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
    private static final ResourceLocation SPRAYER_LOCATION = new ResourceLocation(MODID,"textures/gui/sprayer.png");
    public SprayerScreen(SprayerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new FluidDisplayWidget(leftPos + 10, topPos + 19, 13, 49, menu.tankHandler));
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, SPRAYER_LOCATION);
        blit(pPoseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
