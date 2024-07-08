package net.pierceth.pierceth_greatsword.client.renderer;

import net.pierceth.pierceth_greatsword.client.model.PonguardSlicerModel;
import net.pierceth.pierceth_greatsword.common.world.item.PonguardSlicerItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PonguardSlicerRenderer extends GeoItemRenderer<PonguardSlicerItem> {
    public PonguardSlicerRenderer() {
        super(new PonguardSlicerModel());
    }
}
