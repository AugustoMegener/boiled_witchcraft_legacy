package org.kitowashere.boiled_witchcraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.kitowashere.boiled_witchcraft.client.models.ThrowableMagicModel;
import org.kitowashere.boiled_witchcraft.entities.ThrowableMagicEntity;

import static org.kitowashere.boiled_witchcraft.client.models.ThrowableMagicModel.LAYER_LOCATION;

public class ThrowableMagicRenderer<T extends ThrowableMagicEntity> extends EntityRenderer<T> {
    private ModelPart baked_layer;

    public ThrowableMagicRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        baked_layer = pContext.bakeLayer(LAYER_LOCATION);
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        ThrowableMagicModel model = new ThrowableMagicModel(baked_layer);
        model.prepareMobModel(pEntity, 0, 0, pPartialTick);

        model.renderToBuffer(pPoseStack, pBuffer.getBuffer(model.renderType(pEntity.TEXTURE)), pPackedLight, OverlayTexture.NO_OVERLAY, 1 ,1 ,1, 1);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return pEntity.TEXTURE;
    }
}
