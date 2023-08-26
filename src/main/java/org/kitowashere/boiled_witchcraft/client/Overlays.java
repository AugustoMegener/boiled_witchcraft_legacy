package org.kitowashere.boiled_witchcraft.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.kitowashere.boiled_witchcraft.core.GlyphType;


public class Overlays {
    public static final IGuiOverlay GLYPH_SELECTOR =
        (ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) -> {

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, GlyphType.fromString("fire").texture());

        GuiComponent.blit(poseStack, 0, screenHeight / 3,
                0, 0, 0, 32, 32, 32, 32
        );

        /*GuiComponent.drawCenteredString(
                poseStack,
                new Font(),
                Component.literal("Fire Glyph"),
                0, 0, 0
        );*/
    };
}
