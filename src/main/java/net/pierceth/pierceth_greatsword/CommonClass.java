package net.pierceth.pierceth_greatsword;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLLoader;
import net.pierceth.pierceth_greatsword.common.init.VOSParticles;
import net.pierceth.pierceth_greatsword.common.init.VOSCreativeTabs;
import net.pierceth.pierceth_greatsword.common.init.VOSItems;
import net.pierceth.pierceth_greatsword.common.init.VOSSkillDataKeys;

public class CommonClass {

    public static void init(IEventBus modEventBus) {
        VOSItems.ITEMS.register(modEventBus);
        VOSCreativeTabs.TABS.register(modEventBus);
        VOSParticles.PARTICLES.register(modEventBus);
        VOSSkillDataKeys.DATA_KEYS.register(modEventBus);
    }

    public static boolean isDevEnv() {
        return !FMLLoader.isProduction();
    }

    public static ResourceLocation customLocation(String name) {
        return new ResourceLocation(Constants.MOD_ID, name);
    }
}
