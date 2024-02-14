package net.pierceth.pierceth_greatsword.gameasset;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.damagesource.SourceTags;

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
    public static StaticAnimation DRAGON_CLAW;
    public static StaticAnimation BIPED_WALK_HOUND_GREATSWORD;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD_HIT;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD_ACTIVE_HIT1;
    public static StaticAnimation ROYAL_GREATSWORD_GUARD_ACTIVE_HIT2;
    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(PiercethGreatsword.MODID, PierceTHAnimations::build);
    }

    // Assign the new Animations
    private static void build() {

        HumanoidArmature biped = Armatures.BIPED;

        BIPED_RUN_ROYAL_GREATSWORD = new MovementAnimation(true, "biped/living/run_greatsword", biped);
        BIPED_WALK_ROYAL_GREATSWORD = new MovementAnimation(true, "biped/living/walk_greatsword", biped);
        BIPED_HOLD_ROYAL_GREATSWORD = new StaticAnimation(true, "biped/living/hold_greatsword", biped);
        ROYAL_GREATSWORD_DASH = (new BasicAttackAnimation(0.1F, 0.35F, 0.9F, 0.8F, null, biped.toolR, "biped/combat/greatsword_dash", biped))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(SourceTags.FINISHER))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, false);
        ROYAL_GREATSWORD_AIR_SLASH = new AirSlashAnimation(0.1F, 0.0F, 0.55F, 0.75F, 0.75F, false, null, biped.toolR, "biped/combat/greatsword_airslash", biped)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.4F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.24F, -2.0F), Armatures.BIPED.toolR, 1.1D, 0.55F));
        ROYAL_GREATSWORD_AUTO1 = new BasicAttackAnimation(0.15F, 0.25F, 0.65F, 0.65F, null, biped.toolR, "biped/combat/greatsword_auto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        ROYAL_GREATSWORD_AUTO2 = new BasicAttackAnimation(0.1F, 0.25F, 1.5F, 0.65F, null, biped.toolR, "biped/combat/greatsword_auto2", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.55F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, 0.1F, -1.0F), Armatures.BIPED.toolR, 1.1D, 0.55F));
        ROYAL_GREATSWORD_AUTO3 = new BasicAttackAnimation(0.25F, 0.35F, 0.65F, 0.65F, null, biped.toolR, "biped/combat/greatsword_auto3", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        ROYAL_GREATSWORD_AUTO4 = new BasicAttackAnimation(0.15F, 0.6F, 1.2F, 1.0F, null, biped.toolR, "biped/combat/greatsword_auto4", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        DRAGON_CLAW = new BasicAttackAnimation(.15F, 1.35F, 10.0F, 3.0F, null, biped.toolR, "biped/skill/dragon_claw", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.9F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.4F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(-1.0F, 0.0F, -1.5F), Armatures.BIPED.rootJoint, 1.1D, 0.55F));
        ROYAL_GREATSWORD_GUARD = new StaticAnimation(0.15F, true, "biped/skill/guard_greatsword", biped);
        ROYAL_GREATSWORD_GUARD_HIT = new GuardAnimation(0.05F, "biped/skill/guard_greatsword_hit", biped);
        ROYAL_GREATSWORD_GUARD_ACTIVE_HIT1 = new GuardAnimation(0.05F, 0.2F, "biped/skill/guard_greatsword_hit_active1", biped);
        ROYAL_GREATSWORD_GUARD_ACTIVE_HIT2 = new GuardAnimation(0.05F, 0.2F, "biped/skill/guard_greatsword_hit_active2", biped);

        BIPED_WALK_HOUND_GREATSWORD = new MovementAnimation(true, "biped/living/walk_hound_greatsword", biped);
        }
}
