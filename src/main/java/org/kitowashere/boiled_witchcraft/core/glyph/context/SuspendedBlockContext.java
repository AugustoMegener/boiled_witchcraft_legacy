package org.kitowashere.boiled_witchcraft.core.glyph.context;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class SuspendedBlockContext extends PillarContext {

    protected int height = 2;

    @Override
    public Component translatableName() {
        return Component.translatable("contexts." + MODID + ".suspended_block");
    }

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        int initialY = (screenHeight / 3) + 32 + (18*MAX_HEIGHT);

        RenderSystem.setShaderTexture(0, new ResourceLocation(MODID, "textures/gui/surface.png"));
        GuiComponent.blit(poseStack, 0, initialY,
                0, 0, 0, 32, 32, 32, 32
        );

        RenderSystem.setShaderTexture(0, new ResourceLocation(MODID, "textures/gui/cube.png"));
        GuiComponent.blit(poseStack, 0, initialY-(18*(height-1)),
                0, 0, 0, 32, 32, 32, 32
        );
    }
}
