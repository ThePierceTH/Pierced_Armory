package net.pierceth.pierceth_greatsword.renderer;

import net.minecraft.resources.ResourceLocation;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.world.item.MidasGauntletItem;
import software.bernie.geckolib.model.GeoModel;

public class MidasGauntletModel extends GeoModel<MidasGauntletItem> {
    @Override
    public ResourceLocation getModelResource(MidasGauntletItem animatable) {
        return new ResourceLocation(PiercethGreatsword.MODID,"geo/midas_gauntlet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MidasGauntletItem animatable) {
        return new ResourceLocation(PiercethGreatsword.MODID,"textures/item/midas_gauntlet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MidasGauntletItem animatable) {
        return null;
    }
}
