package org.kitowashere.boiled_witchcraft.client.gui.screen.sprayer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.client.gui.menu.SprayerMenu;
import org.kitowashere.boiled_witchcraft.client.gui.screen.FluidTankWidget;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;
import static org.kitowashere.boiled_witchcraft.client.gui.menu.SprayerMenu.MAX_RANGE;
import static org.kitowashere.boiled_witchcraft.client.gui.overlay.TitanBloodTokens.TOKEN_SIZE;

public class SprayerScreen extends AbstractContainerScreen<SprayerMenu> {

    private static final ResourceLocation SPRAYER_LOCATION=new ResourceLocation(MODID,"textures/gui/sprayer.png");

    public static final int BLOOD_SLOT_HEIGHT = 9;
    public static final int BLOOD_SLOT_WIDTH = 18;

    private int addButtonCoolDown;
    private int subButtonCoolDown;

    public SprayerScreen(SprayerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        titleLabelX = 32;
        this.inventoryLabelX = width * 10;
        this.inventoryLabelY = height * 10;
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new FluidTankWidget(leftPos + 9, topPos + 7, 14, 52, menu.tankHandler));

        addRenderableWidget(new SprayerButton(
            leftPos + 135, topPos + 62, 33,  16,
            leftPos + 176, topPos, leftPos + 256, topPos, 100
        ) {
            @Override protected void setCoolDownTicks(int ticks) { menu.setCoolDownTime(ticks); }
            @Override int getCoolDownTick() { return menu.getCoolDownTime(); }
            @Override void nextCoolDownTick(PoseStack pPoseStack) { SprayerScreen.this.nextCoolDownTick(pPoseStack); }
            @Override boolean enabled() { return true; }
            @Override public void press() { menu.trySpray(); }
        });

        addRenderableWidget(new SprayerButton(
            leftPos + 29, topPos + 62, 13, 16,
            leftPos, topPos, leftPos + 211, topPos, 5
        ) {
            @Override protected void setCoolDownTicks(int ticks) { subButtonCoolDown = ticks; }
            @Override int getCoolDownTick() { return subButtonCoolDown; }
            @Override void nextCoolDownTick(PoseStack pPoseStack) { --subButtonCoolDown; }
            @Override boolean enabled() { return /*menu.getRange() > 0*/ true; }
            @Override public void press() { menu.previousRange(); }
        });
        addRenderableWidget(new SprayerButton(
            leftPos + 62, topPos + 62, 13, 16,
            leftPos, topPos, leftPos + 231, topPos, 5
        ) {
            @Override protected void setCoolDownTicks(int ticks) { addButtonCoolDown = ticks; }
            @Override int getCoolDownTick() { return addButtonCoolDown; }
            @Override void nextCoolDownTick(PoseStack pPoseStack) { --addButtonCoolDown; }
            @Override boolean enabled() { return /*menu.getRange() < MAX_RANGE*/ true; }
            @Override public void press() { menu.nextRange(); }
        });
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, SPRAYER_LOCATION);
        blit(pPoseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderRange(pPoseStack);
        renderBloodAmount(pPoseStack);
    }

    private void renderBloodAmount(PoseStack pPoseStack) {

    }

    private void renderRange(PoseStack pPoseStack) {
        drawCenteredString(
            pPoseStack, font, menu.getRange() + ":" + menu.getRange(),
            leftPos + 51 , topPos + 69 + font.lineHeight, 0xffffff
        );
    }

    public void nextCoolDownTick(PoseStack pPoseStack) {
        menu.nextCoolDownTick();

        blit(pPoseStack, leftPos + 65,  topPos + 1, 176, 16, 22, 27);
        blit(pPoseStack, leftPos + 121, topPos + 1, 176, 43, 22, 27);
    }
}
