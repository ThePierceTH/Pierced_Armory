package net.pierceth.pierceth_greatsword.renderer;

import net.pierceth.pierceth_greatsword.world.item.BahamutItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BahamutRenderer extends GeoItemRenderer<BahamutItem> {
    public BahamutRenderer() {
        super(new BahamutModel());
    }
}
