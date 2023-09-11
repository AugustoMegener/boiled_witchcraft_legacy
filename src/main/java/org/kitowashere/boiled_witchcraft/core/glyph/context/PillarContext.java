package org.kitowashere.boiled_witchcraft.core.glyph.context;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class PillarContext extends GlyphContext {
    protected final int MAX_HEIGHT = 5;
    protected int height = 3;

    public int getHeight() { return height; }

    @Override
    public int guiHeight() {
        return 32 + (18*MAX_HEIGHT);
    }

    @Override
    public int guiWidth() {
        return 32;
    }

    @Override
    public Component translatableName() {
        return Component.translatable("contexts." + MODID + ".pillar");
    }

    @Override
    public Component[] toComponents() {
        return new Component[]{ Component.literal("Height: " + height) };
    }

    @Override
    public void fromBytes(FriendlyByteBuf buf) {
        height = buf.readInt();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(height);
    }

    @Override
    public void configure(InputConfigType input) {
        switch (input) {
            case UP -> height++;
            case DOWN -> height--;
        }
        if (height>5) height = 1;
        if (height<1) height = MAX_HEIGHT;
    }

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        RenderSystem.setShaderTexture(0, new ResourceLocation(MODID, "textures/gui/cube.png"));

        int initialY = (screenHeight / 3) + guiHeight();

        for (int i = 0; i < height; i++) {
            GuiComponent.blit(poseStack, 0, initialY-(18*i),
                    0, 0, 0, 32, 32, 32, 32
            );
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();

        nbt.putInt("height", height);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        height = Math.min(nbt.getInt("height"), MAX_HEIGHT);
    }
}
