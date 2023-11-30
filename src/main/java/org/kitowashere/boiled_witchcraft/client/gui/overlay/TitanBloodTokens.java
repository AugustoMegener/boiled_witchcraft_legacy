package org.kitowashere.boiled_witchcraft.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.BrewingStandScreen;
import net.minecraft.client.gui.screens.inventory.FurnaceScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;

import static net.minecraft.client.gui.GuiComponent.blit;
import static net.minecraft.client.gui.GuiComponent.drawCenteredString;
import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class TitanBloodTokens {

    public static int amount = 0;

    public static final ResourceLocation SIMPLE_TOKEN=new ResourceLocation(MODID, "textures/gui/blood_token.png");
    public static final ResourceLocation SMALL_TOKEN=new ResourceLocation(MODID, "textures/gui/small_blood_token.png");

    public static final int TOKEN_SIZE = 1000;
    public static final int TOKEN_SCALE = 16;
    public static final int TOKEN_DISTANCE = 8;

    public static void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        if (amount > 0) {
            RenderSystem.setShaderTexture(0, SIMPLE_TOKEN);

            int fullTokens = amount / TOKEN_SIZE;
            float offAll = amount % TOKEN_SIZE;
            int allTokens = offAll == 0 ? fullTokens : fullTokens + 1;

            int initX = (screenWidth + allTokens * TOKEN_DISTANCE) / 2 - allTokens * TOKEN_DISTANCE;

            for (int i = 0; i < fullTokens; i++) {
                blit(poseStack,  initX + i * TOKEN_DISTANCE, 0,
                    0, 0, 0, TOKEN_SCALE, TOKEN_SCALE, TOKEN_SCALE, TOKEN_SCALE
                );
            }

            if (offAll > 0) {
                RenderSystem.setShaderTexture(0, SMALL_TOKEN);

                int xPos = initX + allTokens * TOKEN_DISTANCE;

                blit(poseStack,
                    xPos, 0, 0, 0, 0, TOKEN_SCALE, TOKEN_SCALE, TOKEN_SCALE, TOKEN_SCALE
                );

                drawCenteredString(
                    poseStack, gui.getFont(), "" + (int) offAll,
                    xPos + TOKEN_SCALE / 2, TOKEN_SCALE / 2, 0x6d80fa
                );
            }
        }
    }
}
