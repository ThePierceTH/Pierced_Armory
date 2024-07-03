package net.pierceth.pierceth_greatsword.renderer;

import net.pierceth.pierceth_greatsword.world.item.BahamutItem;
import net.pierceth.pierceth_greatsword.world.item.PaleCarverItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PaleCarverRenderer extends GeoItemRenderer<PaleCarverItem> {
    public PaleCarverRenderer() {
        super(new PaleCarverModel());
    }
}
