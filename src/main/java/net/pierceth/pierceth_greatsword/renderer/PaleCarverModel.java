package net.pierceth.pierceth_greatsword.renderer;

import net.minecraft.resources.ResourceLocation;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.world.item.BahamutItem;
import net.pierceth.pierceth_greatsword.world.item.PaleCarverItem;
import software.bernie.geckolib.model.GeoModel;

public class PaleCarverModel extends GeoModel<PaleCarverItem> {
    @Override
    public ResourceLocation getModelResource(PaleCarverItem bahamutItem) {
        return new ResourceLocation(PiercethGreatsword.MODID, "geo/pale_carver.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PaleCarverItem bahamutItem) {
        return new ResourceLocation(PiercethGreatsword.MODID, "textures/item/pale_carver.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PaleCarverItem bahamutItem) {
        return null;
    }
}
