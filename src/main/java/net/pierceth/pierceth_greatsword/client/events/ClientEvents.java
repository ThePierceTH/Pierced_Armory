package net.pierceth.pierceth_greatsword.client.events;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.client.model.MidasShoulderGuardModel;
import net.pierceth.pierceth_greatsword.client.particle.DustParticle;
import net.pierceth.pierceth_greatsword.client.renderer.layer.MidasShoulderGuardLayer;
import net.pierceth.pierceth_greatsword.common.init.VOSParticles;
import net.pierceth.pierceth_greatsword.compat.efm.client.renderer.layer.PatchedMidasShoulderGuardLayer;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onModifyPatchEntityRender(PatchedRenderersEvent.Modify event) {
            ((PatchedLivingEntityRenderer)event.get(EntityType.PLAYER)).addPatchedLayer(MidasShoulderGuardLayer.class, new PatchedMidasShoulderGuardLayer());
        }

        @SubscribeEvent
        public static void onEntityRender(EntityRenderersEvent.RegisterRenderers event) {

        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onParticleRegistry(final RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(VOSParticles.DUST.get(), DustParticle.Provider::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(MidasShoulderGuardModel.MIDAS_SHOULDER_GUARD, MidasShoulderGuardModel::createBodyLayer );
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

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {

    }
}
