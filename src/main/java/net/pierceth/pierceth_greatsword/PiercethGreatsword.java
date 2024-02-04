package net.pierceth.pierceth_greatsword;

import com.mojang.logging.LogUtils;
import net.pierceth.pierceth_greatsword.gameasset.PierceTHAnimations;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.pierceth.pierceth_greatsword.gameasset.PierceTHSkills;
import net.pierceth.pierceth_greatsword.world.item.PierceTHItems;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("pierceth_greatsword")
public class PiercethGreatsword
{
    public static final String MODID = "pierceth_greatsword";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PiercethGreatsword() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(PierceTHAnimations::registerAnimations);

        PierceTHSkills.registerSkills();

        PierceTHItems.ITEMS.register(bus);
               // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}


