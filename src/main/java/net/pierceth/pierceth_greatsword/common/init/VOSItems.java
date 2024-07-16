package net.pierceth.pierceth_greatsword.common.init;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.common.world.item.BahamutItem;
import net.pierceth.pierceth_greatsword.common.world.item.MidasGauntletItem;
import net.pierceth.pierceth_greatsword.common.world.item.PaleCarverItem;
import net.pierceth.pierceth_greatsword.common.world.item.PonguardSlicerItem;
import net.pierceth.pierceth_greatsword.common.world.item.custom.JarArmorItem;


public class VOSItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final RegistryObject<Item> PONGUARD_SLICER = ITEMS.register("ponguard_slicer", () -> new PonguardSlicerItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC), Tiers.NETHERITE));
    public static final RegistryObject<Item> BAHAMUT = ITEMS.register("bahamut", () -> new BahamutItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MIDAS_GAUNTLET = ITEMS.register("midas_gauntlet", () -> new MidasGauntletItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC), Tiers.NETHERITE));
    public static final RegistryObject <Item> PALE_CARVER = ITEMS.register("pale_carver",() -> new PaleCarverItem(new Item.Properties().fireResistant().rarity(Rarity.EPIC), Tiers.NETHERITE));
    //public static final RegistryObject<Item> LAWLIETH_SLICER = ITEMS.register("lawlieth_slicer", () -> new LawliethSlicerItem(new Item.Properties().tab(PierceTHCreativeTabs.ITEMS).fireResistant().rarity(Rarity.EPIC), Tiers.NETHERITE));
    public static final RegistryObject<Item> JAR_HAT = ITEMS.register("jar_hat",
            () -> new JarArmorItem(PierceArmorMaterials.LIVING_SHARD, ArmorItem.Type.HELMET, new Item.Properties()));
}