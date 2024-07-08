package net.pierceth.pierceth_greatsword.client.model;

import net.minecraft.resources.ResourceLocation;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.common.world.item.PonguardSlicerItem;
import software.bernie.geckolib.model.GeoModel;

public class PonguardSlicerModel extends GeoModel<PonguardSlicerItem> {
    @Override
    public ResourceLocation getModelResource(PonguardSlicerItem ponguardslicerItem) {
        return new ResourceLocation(Constants.MOD_ID, "geo/ponguard_slicer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PonguardSlicerItem ponguardslicerItem) {
        return new ResourceLocation(Constants.MOD_ID, "textures/item/ponguard_slicer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PonguardSlicerItem ponguardSlicerItem) {
        return null;
    }
}
