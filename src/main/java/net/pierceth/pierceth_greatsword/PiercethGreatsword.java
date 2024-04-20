package net.pierceth.pierceth_greatsword;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.pierceth.pierceth_greatsword.gameasset.PierceTHAnimations;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pierceth.pierceth_greatsword.particle.PierceTHParticles;
import net.pierceth.pierceth_greatsword.world.item.PierceTHCreativeTabs;
import org.slf4j.Logger;
import net.pierceth.pierceth_greatsword.world.item.PierceTHItems;
import yesman.epicfight.skill.SkillDataKeys;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("pierceth_greatsword")
public class PiercethGreatsword
{
    public static final String MODID = "pierceth_greatsword";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PiercethGreatsword() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        PierceTHItems.ITEMS.register(bus);
        PierceTHCreativeTabs.TABS.register(bus);
        PierceTHParticles.PARTICLES.register(bus);
        
        bus.addListener(PierceTHAnimations::registerAnimations);
        SkillDataKeys.DATA_KEYS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event){

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
}


