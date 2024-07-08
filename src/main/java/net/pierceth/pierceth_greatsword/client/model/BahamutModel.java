package net.pierceth.pierceth_greatsword.client.model;

import net.minecraft.resources.ResourceLocation;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.common.world.item.BahamutItem;
import software.bernie.geckolib.model.GeoModel;

public class BahamutModel extends GeoModel<BahamutItem> {
    @Override
    public ResourceLocation getModelResource(BahamutItem bahamutItem) {
        return new ResourceLocation(Constants.MOD_ID, "geo/bahamut.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BahamutItem bahamutItem) {
        return new ResourceLocation(Constants.MOD_ID, "textures/item/bahamut.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BahamutItem bahamutItem) {
        return null;
    }
}
