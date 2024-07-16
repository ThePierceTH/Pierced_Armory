package net.pierceth.pierceth_greatsword.client.renderer;

import net.pierceth.pierceth_greatsword.client.model.VOSWeaponModel;
import net.pierceth.pierceth_greatsword.common.world.item.VOSWeaponItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class VOSWeaponRenderer extends GeoItemRenderer<VOSWeaponItem> {
    public VOSWeaponRenderer() {
        super(new VOSWeaponModel());
    }
}
