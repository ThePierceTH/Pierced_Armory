package net.pierceth.pierceth_greatsword;

import com.google.common.graph.Network;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.pierceth.pierceth_greatsword.client.CameraEngine;
import net.pierceth.pierceth_greatsword.gameasset.PierceTHAnimations;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pierceth.pierceth_greatsword.network.NetworkManager;
import net.pierceth.pierceth_greatsword.particle.PierceTHParticles;
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
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        PierceTHSkills.registerSkills();

        PierceTHItems.ITEMS.register(bus);
        PierceTHParticles.PARTICLES.register(bus);
               // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event){
        NetworkManager.register();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        new CameraEngine();
    }
}


