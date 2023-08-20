package org.kitowashere.boiled_witchcraft.client.models;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import org.kitowashere.boiled_witchcraft.entities.MagicFireBallEntity;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class MagicFireBallModel<T extends MagicFireBallEntity> extends AbstractThrowableMagicModel<T>{

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MODID, "textures/throwable_magic/fire"), "main");

    public MagicFireBallModel(ModelPart root) { super(root); }
}
