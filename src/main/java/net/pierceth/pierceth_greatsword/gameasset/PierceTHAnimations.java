package net.pierceth.pierceth_greatsword.gameasset;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.client.CameraEngine;
import net.pierceth.pierceth_greatsword.particle.PierceTHParticles;
import org.checkerframework.checker.units.qual.A;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.LevelUtil;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import yesman.epicfight.model.armature.CreeperArmature;
import yesman.epicfight.model.armature.DragonArmature;
import yesman.epicfight.model.armature.EndermanArmature;
import yesman.epicfight.model.armature.HoglinArmature;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.model.armature.IronGolemArmature;
import yesman.epicfight.model.armature.PiglinArmature;
import yesman.epicfight.model.armature.RavagerArmature;
import yesman.epicfight.model.armature.SpiderArmature;
import yesman.epicfight.model.armature.VexArmature;
import yesman.epicfight.model.armature.WitherArmature;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.SourceTags;

import java.util.Random;
import java.util.Set;

@Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID, bus = Bus.MOD)
public class PierceTHAnimations {
    // Define the new Animations
    public static StaticAnimation ROYAL_GREATSWORD_DASH;
    public static StaticAnimation ROYAL_GREATSWORD_AIR_SLASH;
    public static StaticAnimation BIPED_HOLD_ROYAL_GREATSWORD;
    public static StaticAnimation BIPED_WALK_ROYAL_GREATSWORD;
    public static StaticAnimation BIPED_RUN_ROYAL_GREATSWORD;
    public static StaticAnimation ROYAL_GREATSWORD_AUTO1;
    public static StaticAnimation ROYAL_GREATSWORD_AUTO2;
    public static StaticAnimation ROYAL_GREATSWORD_AUTO3;
    public static StaticAnimation ROYAL_GREATSWORD_AUTO4;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD_HIT;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD_ACTIVE_HIT1;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD_ACTIVE_HIT2;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD_BREAK;
    public static StaticAnimation DRAGON_CLAW;
    public static StaticAnimation BIPED_WALK_HOUND_GREATSWORD;
    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(PiercethGreatsword.MODID, PierceTHAnimations::build);
    }

    // Assign the new Animations
    private static void build() {

        HumanoidArmature biped = Armatures.BIPED;

        BIPED_RUN_ROYAL_GREATSWORD = new MovementAnimation(true, "biped/living/run_greatsword", biped)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.05F, ReusableSources.DUST_CLOUD, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -1.0F, 1.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F));
        BIPED_WALK_ROYAL_GREATSWORD = new MovementAnimation(true, "biped/living/walk_greatsword", biped);
        BIPED_HOLD_ROYAL_GREATSWORD = new StaticAnimation(true, "biped/living/hold_greatsword", biped);
        ROYAL_GREATSWORD_DASH = (new BasicAttackAnimation(0.15F, 0.55F, 0.9F, 1.2F, null, biped.toolR, "biped/combat/greatsword_dash", biped))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.FINISHER))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, false)
                .addEvents(
                        AnimationEvent.TimeStampedEvent.create(0.7F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -2.5F, -4.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(0.8F, ReusableSources.SCREENSHAKE, AnimationEvent.Side.CLIENT).params((int)5, (float)3.0, (float)20.0),
                        AnimationEvent.TimeStampedEvent.create(0.7F, ReusableSources.DUST_CLOUD, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -2.5F, 0.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F));;
        ROYAL_GREATSWORD_AIR_SLASH = new AirSlashAnimation(0.1F, 0.0F, 0.55F, 0.75F, 0.75F, false, null, biped.toolR, "biped/combat/greatsword_airslash", biped)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.4F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.24F, -2.0F), Armatures.BIPED.toolR, 1.1D, 0.55F));
        ROYAL_GREATSWORD_AUTO1 = new BasicAttackAnimation(0.15F, 0.25F, 0.65F, 0.65F, null, biped.toolR, "biped/combat/greatsword_auto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        ROYAL_GREATSWORD_AUTO2 = new BasicAttackAnimation(0.1F, 0.25F, 1.5F, 0.65F, null, biped.toolR, "biped/combat/greatsword_auto2", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.55F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, 0.1F, -1.0F), Armatures.BIPED.toolR, 1.1D, 0.55F),
                           AnimationEvent.TimeStampedEvent.create(0.55F, ReusableSources.SCREENSHAKE, AnimationEvent.Side.CLIENT).params((int)5, (float)1.5, (float)20.0));
        ROYAL_GREATSWORD_AUTO3 = new BasicAttackAnimation(0.25F, 0.35F, 0.65F, 0.65F, null, biped.toolR, "biped/combat/greatsword_auto3", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        ROYAL_GREATSWORD_AUTO4 = new BasicAttackAnimation(0.15F, 0.6F, 1.2F, 1.0F, null, biped.toolR, "biped/combat/greatsword_auto4", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.1F)
                .addEvents( AnimationEvent.TimeStampedEvent.create(0.65F, ReusableSources.DUST_CLOUD, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.5F, 0.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F));

        DRAGON_CLAW = new BasicAttackAnimation(0.15F, 1.35F, 10.0F, 3.0F, null, biped.toolR, "biped/skill/dragon_claw", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.95F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
                .addEvents(
                        AnimationEvent.TimeStampedEvent.create(1.4F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-1.0F, 0.0F, -1.5F), Armatures.BIPED.rootJoint, 1.1D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(1.35F, ReusableSources.SCREENSHAKE, AnimationEvent.Side.CLIENT).params((int)10, (float)6.0, (float)30.0),
                        AnimationEvent.TimeStampedEvent.create(1.35F, ReusableSources.DUST_CLOUD, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.5F, 0.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(1.45F, ReusableSources.DUST_CLOUD, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.5F, -3.0F), Armatures.BIPED.rootJoint, -1.5D, 0.55F));
        ROYAL_GREATSWORD_GUARD = new StaticAnimation(0.15F, true, "biped/skill/guard_greatsword", biped);
        ROYAL_GREATSWORD_GUARD_HIT = new GuardAnimation(0.05F, "biped/skill/guard_greatsword_hit", biped);
        ROYAL_GREATSWORD_GUARD_ACTIVE_HIT1 = new GuardAnimation(0.05F, 0.2F, "biped/skill/guard_greatsword_hit_active1", biped);
        ROYAL_GREATSWORD_GUARD_ACTIVE_HIT2 = new GuardAnimation(0.05F, 0.8F, "biped/skill/guard_greatsword_hit_active2", biped)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.01F, ReusableSources.SCREENSHAKE, AnimationEvent.Side.CLIENT).params((int)5, (float)6.0, (float)20.0));
        ROYAL_GREATSWORD_GUARD_BREAK = new LongHitAnimation(0.05F, "biped/skill/guard_greatsword_break", biped)
                .addEvents( AnimationEvent.TimeStampedEvent.create(0.01F, ReusableSources.SCREENSHAKE, AnimationEvent.Side.CLIENT).params((int)5, (float)6.0, (float)20.0),
                        AnimationEvent.TimeStampedEvent.create(1.0F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.24F, -1.0F), Armatures.BIPED.toolR, 1.1D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(1.0F, ReusableSources.SCREENSHAKE, AnimationEvent.Side.CLIENT).params((int)10, (float)8.0, (float)30.0));

        //BIPED_WALK_HOUND_GREATSWORD = new MovementAnimation(true, "biped/living/walk_hound_greatsword", biped);
        }

    public static class ReusableSources {
        public static final AnimationEvent.AnimationEventConsumer SCREENSHAKE = (entitypatch, animation, params) -> {
            int duration = (int)params[0];
            float strength = (float)params[1];
            float freq = (float)params[2];
            CameraEngine.getInstance().shakeCamera(duration, strength, freq);
        };

        public static final AnimationEvent.AnimationEventConsumer DUST_CLOUD = (entitypatch, animation, params) -> {
            Random random = new Random();
            Vec3 position = entitypatch.getOriginal().position();
            OpenMatrix4f modelTransform = entitypatch.getArmature().getBindedTransformFor(animation.getPoseByTime(entitypatch, (float)params[3], 1.0F), (Joint) params[1])
                    .mulFront(
                            OpenMatrix4f.createTranslation((float)position.x, (float)position.y, (float)position.z)
                                    .mulBack(OpenMatrix4f.createRotatorDeg(180.0F, Vec3f.Y_AXIS)
                                            .mulBack(entitypatch.getModelMatrix(1.0F))));

            Level level = entitypatch.getOriginal().level;
            Vec3 cloudPos = OpenMatrix4f.transform(modelTransform, ((Vec3f)params[0]).toDoubleVector());

            int n = 20;

            for (int i = 0; i < n; i++) {
                float speedx = (float)(random.nextDouble() * 0.5f) - 0.25f;
                float speedy = 0.1F;
                float speedz = (float)(random.nextDouble() * 0.5f) - 0.25f;

                level.addParticle(PierceTHParticles.DUST.get(),
                        true,
                        cloudPos.x,
                        cloudPos.y,
                        cloudPos.z,
                        speedx,
                        speedy,
                        speedz);
            }
        };
    }
}
