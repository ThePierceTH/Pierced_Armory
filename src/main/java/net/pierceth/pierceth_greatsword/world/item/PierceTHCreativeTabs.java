package net.pierceth.pierceth_greatsword.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.pierceth.pierceth_greatsword.world.item.PierceTHItems;

public class PierceTHCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PiercethGreatsword.MODID);

    public static final RegistryObject<CreativeModeTab> ITEMS = TABS.register("items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + PiercethGreatsword.MODID + ".items"))
                    .icon(() -> new ItemStack(PierceTHItems.PONGUARD_SLICER.get()))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .displayItems((params, output) -> {
                        PierceTHItems.ITEMS.getEntries().forEach(it -> {
                            output.accept(it.get());
                        });
                    })
                    .build());
}
