package org.kitowashere.boiled_witchcraft.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.kitowashere.boiled_witchcraft.entities.MagicFireBallEntity;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class MagicFireBallRenderer extends EntityRenderer<MagicFireBallEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/throwable_magic/fire");

    public MagicFireBallRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        pContext.bakeLayer(new ModelLayerLocation(new ResourceLocation(MODID, "throwable_magic"), "main"));
    }

    @Override
    public ResourceLocation getTextureLocation(MagicFireBallEntity pEntity) {
        return TEXTURE;
    }
}
