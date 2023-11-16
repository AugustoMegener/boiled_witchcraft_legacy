package org.kitowashere.boiled_witchcraft.client.gui.overlay;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.kitowashere.boiled_witchcraft.core.glyph.context.GlyphContext;
import org.kitowashere.boiled_witchcraft.registry.GlyphTypeRegistry;
import org.kitowashere.boiled_witchcraft.world.items.GlyphOnAPaper;

import java.util.concurrent.atomic.AtomicInteger;

import static org.kitowashere.boiled_witchcraft.registry.ItemRegistry.GLYPH_ON_A_PAPER;

public class PaperInfo {
    public static void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;

        if (localPlayer!=null) {
            AtomicInteger height = new AtomicInteger(screenHeight / 3);
            
            for (InteractionHand i : InteractionHand.values()) {
                Font font = gui.getFont();
                ItemStack item = localPlayer.getItemInHand(i);

                if (item.is(GLYPH_ON_A_PAPER.get())) {
                    GlyphOnAPaper.getGlyphType(item).ifPresent(glyphType -> {
                        Component title = GlyphTypeRegistry.translatableName(glyphType);

                        GuiComponent.drawString(poseStack, font, title, screenWidth-font.width(title), height.getAndAdd(font.lineHeight*2), 0x834dc4);

                        GlyphOnAPaper.getGlyphMagic(item).ifPresent(magic -> {
                            for (GlyphContext j : magic.CONTEXT_KIT) {
                                displayCtx(gui, poseStack, screenWidth, height, j);
                                height.addAndGet(font.lineHeight/3);
                            }
                        });
                    });

                    height.addAndGet(font.lineHeight*3);
                }
            }
        }
    }

    private static void displayCtx(ForgeGui gui, PoseStack poseStack, int screenWidth, AtomicInteger height, GlyphContext context) {
        Font font = gui.getFont();

        Component title = context.translatableName();
        GuiComponent.drawString(poseStack, font, title, screenWidth-font.width(title), height.getAndAdd(font.lineHeight), 0xd9bdc8);
        height.addAndGet(font.lineHeight/4);

        for (Component i : context.toComponents()) {
            GuiComponent.drawString(poseStack, font, i, screenWidth-font.width(i), height.getAndAdd(font.lineHeight), 0xffffff);
            height.addAndGet(font.lineHeight/5);
        }
    }
}
