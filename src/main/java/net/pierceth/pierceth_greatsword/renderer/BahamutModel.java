package net.pierceth.pierceth_greatsword.renderer;

import net.minecraft.resources.ResourceLocation;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.world.item.BahamutItem;
import software.bernie.geckolib.model.GeoModel;

public class BahamutModel extends GeoModel<BahamutItem> {
    @Override
    public ResourceLocation getModelResource(BahamutItem bahamutItem) {
        return new ResourceLocation(PiercethGreatsword.MODID, "geo/bahamut.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BahamutItem bahamutItem) {
        return new ResourceLocation(PiercethGreatsword.MODID, "textures/item/bahamut.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BahamutItem bahamutItem) {
        return null;
    }
}
