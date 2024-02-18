package net.pierceth.pierceth_greatsword.data.loot;

import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.config.ConfigManager;
import yesman.epicfight.data.loot.function.SetSkillFunction;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.item.EpicFightItems;

@Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID)
public class PiercethLootTables {
    @SubscribeEvent
    public static void modifyVanillaLootPools(final LootTableLoadEvent event) {
        int modifier = ConfigManager.SKILL_BOOK_CHEST_LOOT_MODIFYER.get();
        int dropChance = 100 + modifier;
        int antiDropChance = 100 - modifier;
        float dropChanceModifier = dropChance / (float)(antiDropChance + dropChance);

        if (event.getName().equals(BuiltInLootTables.ABANDONED_MINESHAFT)) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                    .add(LootItem.lootTableItem(EpicFightItems.SKILLBOOK.get()).apply(SetSkillFunction.builder(
                            "pierceth_greatsword:royal_greatsword",
                            //"pierceth_greatsword:hound_greatsword",
                            "pierceth_greatsword:dragon_claw"
                    ))).when(LootItemRandomChanceCondition.randomChance(dropChanceModifier * 0.3F))
                    .build());
        }

        if (event.getName().equals(BuiltInLootTables.STRONGHOLD_LIBRARY)) {
            event.getTable().addPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 5.0F))
                    .add(LootItem.lootTableItem(EpicFightItems.SKILLBOOK.get()).apply(SetSkillFunction.builder(
                            "pierceth_greatsword:royal_greatsword",
                            //"pierceth_greatsword:hound_greatsword",
                            "pierceth_greatsword:dragon_claw"
                    ))).when(LootItemRandomChanceCondition.randomChance(dropChanceModifier * 0.3F))
                    .build());
        }
    }
}
