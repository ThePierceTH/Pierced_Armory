package net.pierceth.pierceth_greatsword.common.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

public class PaleCarverItem extends VOSWeaponItem {
    private final float attackDamage;
    private final float attackSpeed;

    public PaleCarverItem(Item.Properties build, Tiers tier) {
        super(tier, 0, 0, build);
        this.attackDamage = 8.0F;
        this.attackSpeed= 1.0F;
    }
}
