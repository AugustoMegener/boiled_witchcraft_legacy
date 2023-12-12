package org.kitowashere.boiled_witchcraft.client.gui.screen.sprayer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

abstract class SprayerButton extends AbstractButton {

    private static final ResourceLocation SPRAYER_LOCATION = new ResourceLocation(MODID,"textures/gui/sprayer.png");

    private final int buttonX;
    private final int buttonY;

    private final int coverX;
    private final int coverY;

    private final int coolDown;


    public SprayerButton(
            int pX, int pY, int pWidth, int pHeight, int buttonX, int buttonY, int coverX, int coverY, int coolDown
    ) {
        super(pX, pY, pWidth, pHeight, Component.empty());

        this.buttonX = buttonX;
        this.buttonY = buttonY;

        this.coverX = coverX;
        this.coverY = coverY;

        this.coolDown = coolDown;
    }

    @Override protected void updateWidgetNarration(@NotNull NarrationElementOutput pNarrationElementOutput) {}

    @Override
    public void renderWidget(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.setShaderTexture(0, SPRAYER_LOCATION);

        if (enabled()) {
            blit(pPoseStack, getX(),  getY(), getButtonX(), getButtonY(),  getWidth(), getHeight());
        }
        if (getCoolDownTick() > 0) {
            blit(pPoseStack, getX(),  getY(), getCoverX(), getCoverY(),  getWidth(), getHeight());
            nextCoolDownTick(pPoseStack);
        }
    }

    @Override
    public void onPress() {
        setCoolDownTicks(coolDown);
        press();
    }

    abstract boolean enabled();
    abstract void press();

    abstract void setCoolDownTicks(int ticks);
    abstract int getCoolDownTick();
    abstract void nextCoolDownTick(PoseStack pPoseStack);

    public int getButtonX() {
        return buttonX;
    }

    public int getButtonY() {
        return buttonY;
    }

    public int getCoverX() {
        return coverX;
    }

    public int getCoverY() {
        return coverY;
    }
}