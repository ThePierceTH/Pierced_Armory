package net.pierceth.pierceth_greatsword.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.particle.DustParticle;
import net.pierceth.pierceth_greatsword.particle.PierceTHParticles;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid= PiercethGreatsword.MODID, value=Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ClientModBusEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onParticleRegistry(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(PierceTHParticles.DUST.get(), DustParticle.Provider::new);
    }
}
