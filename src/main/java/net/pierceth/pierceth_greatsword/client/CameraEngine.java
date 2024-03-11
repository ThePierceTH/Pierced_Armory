package net.pierceth.pierceth_greatsword.client;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.event.ViewportEvent;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
//import net.pierceth.pierceth_greatsword.mixin.client.CameraMixin;
import net.pierceth.pierceth_greatsword.util.ShakesScreen;

@OnlyIn(Dist.CLIENT)
public class CameraEngine {
    private static CameraEngine instance;
    public static CameraEngine getInstance(){
        return instance;
    }
    public static int lastTremorTick = -1;
    public static float[] randomTremorOffsets = new float[3];

    private int cameraShakeTime = 0;
    private float cameraShakeStrength = 0;
    private float frequency = 0;

    public CameraEngine(){
        instance = this;
    }

    public void shakeCamera(int time, float strength, float frequency){
        if(strength > this.cameraShakeStrength) {
            this.cameraShakeStrength = strength;
            this.cameraShakeTime = time;
            this.frequency = frequency;
        }
    }
    public void shakeCamera(int time, float strength){
        this.shakeCamera(time, strength, 3);
    }

    public void reset(){
        this.cameraShakeStrength = 0;
        this.frequency = 0;
    }

    @Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID, value = Dist.CLIENT)
    public static class Events {
        //@SubscribeEvent
        //public void computeCameraAngles(ViewportEvent.ComputeCameraAngles event) {
           // Entity player = Minecraft.getInstance().getCameraEntity();
            //float partialTick = Minecraft.getInstance().getPartialTick();
            //float tremorAmount = instance.cameraShakeStrength;
            //if (player != null) {
                //double shakeDistanceScale = 64;
                //double distance = Double.MAX_VALUE;
                //if (tremorAmount == 0) {
                    //AABB aabb = player.getBoundingBox().inflate(shakeDistanceScale);
                    //for (Mob screenShaker : Minecraft.getInstance().level.getEntitiesOfClass(Mob.class, aabb, (mob -> mob instanceof ShakesScreen))) {
                        //ShakesScreen shakesScreen = (ShakesScreen) screenShaker;
                        //if (shakesScreen.canFeelShake(player) && screenShaker.distanceTo(player) < distance) {
                            //distance = screenShaker.distanceTo(player);
                            //tremorAmount = Math.min((1F - (float) Math.min(1, distance / shakesScreen.getShakeDistance())) * Math.max(shakesScreen.getScreenShakeAmount(partialTick), 0F), 2.0F);
                        //}
                   // }
                //}
                //if (tremorAmount > 0) {
                    //if (instance.lastTremorTick != player.tickCount) {
                       // RandomSource rng = player.level().random;
                       // instance.randomTremorOffsets[0] = rng.nextFloat();
                       // instance.randomTremorOffsets[1] = rng.nextFloat();
                       // instance.randomTremorOffsets[2] = rng.nextFloat();
                       // instance.lastTremorTick = player.tickCount;
                   // }
                   // double intensity = tremorAmount * Minecraft.getInstance().options.screenEffectScale().get();
                    //((CameraMixin) (event.getCamera())).invokeMove(instance.randomTremorOffsets[0] * 0.2F * intensity, instance.randomTremorOffsets[1] * 0.2F * intensity, instance.randomTremorOffsets[2] * 0.5F * intensity);
             //   }
           // }
        //}
    }
}