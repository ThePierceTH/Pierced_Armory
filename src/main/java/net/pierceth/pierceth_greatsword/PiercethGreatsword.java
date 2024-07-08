package net.pierceth.pierceth_greatsword;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.pierceth.pierceth_greatsword.compat.efm.gameasset.PierceTHAnimations;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Constants.MOD_ID)
public class PiercethGreatsword {

    public PiercethGreatsword() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        CommonClass.init(bus);
        
        bus.addListener(PierceTHAnimations::registerAnimations);
//        SkillDataKeys.DATA_KEYS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event){

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
}


