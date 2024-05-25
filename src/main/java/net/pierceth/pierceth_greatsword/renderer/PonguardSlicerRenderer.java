package net.pierceth.pierceth_greatsword.renderer;

import net.pierceth.pierceth_greatsword.world.item.BahamutItem;
import net.pierceth.pierceth_greatsword.world.item.PonguardSlicerItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PonguardSlicerRenderer extends GeoItemRenderer<PonguardSlicerItem> {
    public PonguardSlicerRenderer() {
        super(new PonguardSlicerModel());
    }
}
