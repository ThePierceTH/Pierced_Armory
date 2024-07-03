package net.pierceth.pierceth_greatsword.events;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.particle.DustParticle;
import net.pierceth.pierceth_greatsword.particle.PierceTHParticles;
import net.pierceth.pierceth_greatsword.renderer.MidasShoulderGuardLayer;
import net.pierceth.pierceth_greatsword.renderer.MidasShoulderGuardTwoModel;
import net.pierceth.pierceth_greatsword.renderer.PatchedMidasShouldGuardLayer;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {
    @Mod.EventBusSubscriber(modid= PiercethGreatsword.MODID, value=Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onModifyPatchEntityRender(PatchedRenderersEvent.Modify event) {
            ((PatchedLivingEntityRenderer)event.get(EntityType.PLAYER)).addPatchedLayer(MidasShoulderGuardLayer.class, new PatchedMidasShouldGuardLayer());
        }
        @SubscribeEvent
        public static void onEntityRender(EntityRenderersEvent.RegisterRenderers renderTime) {
        }
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onParticleRegistry(final RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(PierceTHParticles.DUST.get(), DustParticle.Provider::new);
        }
        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){

            event.registerLayerDefinition(MidasShoulderGuardTwoModel.MIDAS_SHOULDER_GUARD, MidasShoulderGuardTwoModel::createBodyLayer );

        }
        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.AddLayers event) {
            for (final String skin : event.getSkins()) {
                final LivingEntityRenderer<Player, PlayerModel<Player>> playerRenderer = event.getSkin(skin);
                if (playerRenderer == null)
                    continue;
                playerRenderer.addLayer(new MidasShoulderGuardLayer<>(playerRenderer));
            }
        }
    }
    @Mod.EventBusSubscriber(modid= PiercethGreatsword.MODID, value=Dist.CLIENT)
    public static class ClientModEvents {
    }
}

