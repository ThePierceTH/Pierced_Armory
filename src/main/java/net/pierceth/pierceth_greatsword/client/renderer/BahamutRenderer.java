package net.pierceth.pierceth_greatsword.client.renderer;

import net.pierceth.pierceth_greatsword.client.model.BahamutModel;
import net.pierceth.pierceth_greatsword.common.world.item.BahamutItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BahamutRenderer extends GeoItemRenderer<BahamutItem> {
    public BahamutRenderer() {
        super(new BahamutModel());
    }
}
