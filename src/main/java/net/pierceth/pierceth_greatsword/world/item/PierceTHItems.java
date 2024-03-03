package net.pierceth.pierceth_greatsword.world.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import yesman.epicfight.world.item.GreatswordItem;


public class PierceTHItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PiercethGreatsword.MODID);
    public static final RegistryObject<Item> PONGUARD_SLICER = ITEMS.register("ponguard_slicer", () -> new PonguardSlicerItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC), Tiers.NETHERITE));
    //public static final RegistryObject<Item> LAWLIETH_SLICER = ITEMS.register("lawlieth_slicer", () -> new LawliethSlicerItem(new Item.Properties().tab(PierceTHCreativeTabs.ITEMS).fireResistant().rarity(Rarity.EPIC), Tiers.NETHERITE));
}
