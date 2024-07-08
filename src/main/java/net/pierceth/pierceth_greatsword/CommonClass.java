package net.pierceth.pierceth_greatsword;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLLoader;
import net.pierceth.pierceth_greatsword.client.particle.PierceTHParticles;
import net.pierceth.pierceth_greatsword.common.init.PierceTHCreativeTabs;
import net.pierceth.pierceth_greatsword.common.init.PierceTHItems;

public class CommonClass {

    public static void init(IEventBus modEventBus) {
        PierceTHItems.ITEMS.register(modEventBus);
        PierceTHCreativeTabs.TABS.register(modEventBus);
        PierceTHParticles.PARTICLES.register(modEventBus);
    }

    public static boolean isDevEnv() {
        return !FMLLoader.isProduction();
    }

    public static ResourceLocation customLocation(String name) {
        return new ResourceLocation(Constants.MOD_ID, name);
    }
}
