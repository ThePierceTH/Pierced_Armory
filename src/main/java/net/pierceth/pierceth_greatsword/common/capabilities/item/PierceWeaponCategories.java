package net.pierceth.pierceth_greatsword.common.capabilities.item;

import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum PierceWeaponCategories implements WeaponCategory {
    BAHAMUT, PONGUARD, MIDAS, CARVER;

    final int id;

    PierceWeaponCategories() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }
}
