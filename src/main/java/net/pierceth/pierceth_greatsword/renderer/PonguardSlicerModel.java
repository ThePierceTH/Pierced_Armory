package net.pierceth.pierceth_greatsword.renderer;

import net.minecraft.resources.ResourceLocation;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.world.item.PonguardSlicerItem;
import software.bernie.geckolib.model.GeoModel;

public class PonguardSlicerModel extends GeoModel<PonguardSlicerItem> {
    @Override
    public ResourceLocation getModelResource(PonguardSlicerItem ponguardslicerItem) {
        return new ResourceLocation(PiercethGreatsword.MODID, "geo/ponguard_slicer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PonguardSlicerItem ponguardslicerItem) {
        return new ResourceLocation(PiercethGreatsword.MODID, "textures/item/ponguard_slicer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PonguardSlicerItem ponguardSlicerItem) {
        return null;
    }
}
