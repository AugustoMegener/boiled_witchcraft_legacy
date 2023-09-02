package org.kitowashere.boiled_witchcraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.kitowashere.boiled_witchcraft.client.models.ThrowableMagicModel;
import org.kitowashere.boiled_witchcraft.world.entities.ThrowableMagicEntity;

import static org.kitowashere.boiled_witchcraft.client.models.ThrowableMagicModel.LAYER_LOCATION;

public class ThrowableMagicRenderer<T extends ThrowableMagicEntity> extends EntityRenderer<T> {
    private final ModelPart BAKED_LAYER;
    public final ResourceLocation TEXTURE;

    public ThrowableMagicRenderer(EntityRendererProvider.Context pContext, ResourceLocation texture) {
        super(pContext);
        BAKED_LAYER = pContext.bakeLayer(LAYER_LOCATION);
        TEXTURE = texture;
    }

    @Override
    public void render(@NotNull T pEntity, float pEntityYaw, float pPartialTick, @NotNull PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        ThrowableMagicModel model = new ThrowableMagicModel(BAKED_LAYER);
        model.prepareMobModel(pEntity, 0, 0, pPartialTick);

        model.renderToBuffer(pPoseStack, pBuffer.getBuffer(model.renderType(getTextureLocation(pEntity))), pPackedLight, OverlayTexture.NO_OVERLAY, 1 ,1 ,1, 1);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T pEntity) {
        return TEXTURE;
    }
}
