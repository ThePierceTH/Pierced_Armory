package net.pierceth.pierceth_greatsword.events;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.data.loot.PiercethLootTables;

@Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID)
public class WorldEvents {
    @SubscribeEvent
    public static void onLootTableRegistry(final LootTableLoadEvent event) {
        PiercethLootTables.modifyVanillaLootPools(event);
    }
}
