package org.kitowashere.boiled_witchcraft.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

public class FluidTankWidget extends AbstractWidget {

    private final IFluidHandler iFluidHandler;

    public FluidTankWidget(int pX, int pY, int pWidth, int pHeight, IFluidHandler blockEntity) {
        super(pX, pY, pWidth, pHeight, Component.empty()); this.iFluidHandler = blockEntity;
    }

    @Override
    public void renderWidget(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        FluidStack stack = iFluidHandler.getFluidInTank(0);
        Fluid fluid = stack.getFluid();
        ResourceLocation textureLocation = IClientFluidTypeExtensions.of(fluid).getStillTexture();

        if (textureLocation != null) {

            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
            if (sprite != null) {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);

                int color = IClientFluidTypeExtensions.of(fluid).getTintColor();

                double sliceSize = (double) getHeight() / iFluidHandler.getTankCapacity(0);
                int fluidLevel = (int) (sliceSize * iFluidHandler.getFluidInTank(0).getAmount());

                GuiComponent.blit(pPoseStack,
                    getX() , getY() + (getHeight() - fluidLevel),
                    0,
                    getWidth(), fluidLevel,
                    sprite,
                    (color >> 16 & 0xff) / 255f,
                    (color >> 8 & 0xff) / 255f,
                    (color & 0xff) / 255f, 1
                );
            }
        }
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput pNarrationElementOutput) { }
}