package net.pierceth.pierceth_greatsword.events;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.common.data.loot.PiercethLootTables;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class WorldEvents {
    @SubscribeEvent
    public static void onLootTableRegistry(final LootTableLoadEvent event) {
        PiercethLootTables.modifyVanillaLootPools(event);
    }
}
