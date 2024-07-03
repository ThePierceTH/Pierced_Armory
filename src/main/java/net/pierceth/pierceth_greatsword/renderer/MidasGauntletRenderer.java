package net.pierceth.pierceth_greatsword.renderer;

import net.pierceth.pierceth_greatsword.world.item.MidasGauntletItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MidasGauntletRenderer extends GeoItemRenderer<MidasGauntletItem> {
    public MidasGauntletRenderer() {
        super(new MidasGauntletModel());
    }
}
