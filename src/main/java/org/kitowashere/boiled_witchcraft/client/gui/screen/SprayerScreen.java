package org.kitowashere.boiled_witchcraft.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.client.gui.menu.SprayerMenu;
import org.kitowashere.boiled_witchcraft.networking.ModMessages;
import org.kitowashere.boiled_witchcraft.networking.packet.STGGPacketC2S;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class SprayerScreen extends AbstractContainerScreen<SprayerMenu> {
    private static final ResourceLocation SPRAYER_LOCATION = new ResourceLocation(MODID,"textures/gui/sprayer.png");

    private int pressedTicks = 0;
    private RangeSlider rangeSlider = new RangeSlider(leftPos + 75, topPos + 63, 86, 17);

    public SprayerScreen(SprayerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new FluidDisplayWidget(leftPos + 10, topPos + 19, 13, 49, menu.tankHandler));
        addRenderableWidget(new SprayButton(leftPos + 34, topPos + 17, 34, 16));
        addRenderableWidget(rangeSlider);
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, SPRAYER_LOCATION);
        blit(pPoseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

     class SprayButton extends AbstractButton {
         public SprayButton(int pX, int pY, int pWidth, int pHeight) {
             super(pX, pY, pWidth, pHeight, Component.empty());
         }

         @Override protected void updateWidgetNarration(@NotNull NarrationElementOutput pNarrationElementOutput) {}

        @Override
        public void onPress() { menu.trySpray(); }

        @Override
        public void renderWidget(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
            if (menu.getCooldownTime() > 0) {
                RenderSystem.setShaderTexture(0, SPRAYER_LOCATION);

                blit(pPoseStack,
                        leftPos + 29,  topPos + 17, 176, 0,  34, 16
                );
                blit(pPoseStack,
                        leftPos + 85,  topPos + 20, 176, 16, 22, 27
                );
                blit(pPoseStack,
                        leftPos + 141, topPos + 20, 176, 43, 22, 27
                );

                menu.nextCooldownTick();
            }
        }
    }

    class RangeSlider extends AbstractSliderButton {

        public static final int MAX_RANGE = 9;
        private int range;

        public RangeSlider(int pX, int pY, int pWidth, int pHeight) {
            super(pX, pY, pWidth, pHeight, Component.empty(), 1);
        }


        @Override
        public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
            RenderSystem.setShaderTexture(0, SPRAYER_LOCATION);

            int x = (int) (leftPos + 67 + 86 * value);
            int y = topPos + 63;

            blit(pPoseStack, x, y, 210, 0, 17, 17);
            drawCenteredString(pPoseStack, minecraft.font, range + ":" + range, x + 9, y + 4, 0xd9bdc8);
        }

        @Override
        protected void updateMessage() {}

        @Override
        protected void applyValue() {
            range = 1 + Math.min(((int) (((MAX_RANGE - 1) * value) / 2) * 2), MAX_RANGE);

            menu.setRange(range);
        }

        public int getRange() { return range; }
    }
}
