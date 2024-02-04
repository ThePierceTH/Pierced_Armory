package net.pierceth.pierceth_greatsword.world.item;

import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.pierceth.pierceth_greatsword.world.item.PierceTHItems;

public class PierceTHCreativeTabs {
    public static final CreativeModeTab ITEMS = new CreativeModeTab(PiercethGreatsword.MODID + ".item") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PierceTHItems.PONGUARD_SLICER.get());
        }
    };
}
