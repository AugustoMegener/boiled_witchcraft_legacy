package org.kitowashere.boiled_witchcraft.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.kitowashere.boiled_witchcraft.client.ClientGCTX;
import org.kitowashere.boiled_witchcraft.core.glyph.context.GlyphContext;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;

public class GlyphSelector {
    private static boolean enabled = false;

    public static void setEnabled(Boolean value) { enabled = value; }

    public static void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        if (enabled) {
            GuiComponent.drawString(poseStack, gui.getFont(), GlyphTypeRegistry.translatableName(ClientGCTX.getSelectedGlyph()), 0, (screenHeight / 3)-16, 0xd9bdc8);

            RenderSystem.setShader(GameRenderer::getPositionShader);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.setShaderTexture(0, GlyphTypeRegistry.getTexture(ClientGCTX.getSelectedGlyph()));

            GuiComponent.blit(poseStack, 0, screenHeight / 3,
                    0, 0, 0, 32, 32, 32, 32
            );

            for (GlyphContext i : ClientGCTX.getGlyphMagic().CONTEXT_KIT) {
                i.render(gui, poseStack, partialTick, screenWidth, screenHeight);
            }
        }
    }
}