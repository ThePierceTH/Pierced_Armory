package net.pierceth.pierceth_greatsword.gameasset;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.particle.PierceTHParticles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackPhaseProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.StunType;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID, bus = Bus.MOD)
public class PierceTHAnimations {
    // Define the new Animations

    /** Royal Greatsword **/
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

    /** Bahamut **/

    public static StaticAnimation HOLD_BAHAMUT;
    public static StaticAnimation WALK_BAHAMUT;
    public static StaticAnimation RUN_BAHAMUT;
    public static StaticAnimation BAHAMUT_DASH;
    public static StaticAnimation BAHAMUT_AIRSLASH;
    public static StaticAnimation BAHAMUT_AUTO1;
    public static StaticAnimation BAHAMUT_AUTO2;
    public static StaticAnimation BAHAMUT_AUTO3;
    public static StaticAnimation BAHAMUT_AUTO4;
    public static StaticAnimation BAHAMUT_RAUTO1;
    public static StaticAnimation BAHAMUT_RAUTO2;
    public static StaticAnimation BAHAMUT_LAUTO1;
    public static StaticAnimation BAHAMUT_LAUTO2;
    public static StaticAnimation BAHAMUT_BAUTO1;
    public static StaticAnimation BAHAMUT_BAUTO2;

    /** Midas **/

    public static StaticAnimation HOLD_MIDAS;
    public static StaticAnimation WALK_MIDAS;
    public static StaticAnimation RUN_MIDAS;
    public static StaticAnimation MIDAS_AUTO1;
    public static StaticAnimation MIDAS_AUTO2;
    public static StaticAnimation MIDAS_AUTO3;
    public static StaticAnimation MIDAS_AUTO4;
    public static StaticAnimation MIDAS_DASH;
    public static StaticAnimation MIDAS_AIRSLASH;

    /** Carver **/

    public static StaticAnimation HOLD_CARVER;
    public static StaticAnimation WALK_CARVER;
    public static StaticAnimation RUN_CARVER;
    public static StaticAnimation CARVER_AUTO1;
    public static StaticAnimation CARVER_AUTO2;
    public static StaticAnimation CARVER_AUTO3;
    public static StaticAnimation CARVER_DASH;

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
        ROYAL_GREATSWORD_DASH = (new BasicAttackAnimation(0.15F, 0.55F, 0.9F, 1.5F, null, biped.toolR, "biped/combat/greatsword_dash", biped))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                //.addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.01F, 0.95F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, entitypatch, speed, elapsedTime) -> {
                    if (elapsedTime >= 0.55F && elapsedTime < 1.0F) {
                        float dpx = (float) entitypatch.getOriginal().getX();
                        float dpy = (float) entitypatch.getOriginal().getY();
                        float dpz = (float) entitypatch.getOriginal().getZ();
                        BlockState block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx,dpy,dpz));

                        while ((block.getBlock() instanceof BushBlock || block.isAir()) && !block.is(Blocks.VOID_AIR)) {
                            dpy--;
                            block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx,dpy,dpz));
                        }

                        float distanceToGround = (float) Math.max(Math.abs(entitypatch.getOriginal().getY() - dpy)-1, 0.0F);

                        return 1 - (1 / (-distanceToGround - 1.F) + 1.0f);
                    }

                    return 1.0F;
                })
                .addEvents(
                        //AnimationEvent.TimeStampedEvent.create(0.75F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -2.5F, -4.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(0.75F, PierceTHAnimations.ReusableSources.FRACTURE_GROUND_SPEED_BASED, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -2.5F, -4.0F), Armatures.BIPED.rootJoint, 1.1D, 10D, 0.55F),
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

                //---//---//--//
            /** Bahamut **/
        //---//---//--//

        HOLD_BAHAMUT = new StaticAnimation(true, "biped/living/hold_bahamut", biped);
        WALK_BAHAMUT = new MovementAnimation(true, "biped/living/walk_bahamut", biped);
        RUN_BAHAMUT = new MovementAnimation(true, "biped/living/run_bahamut", biped);

        BAHAMUT_DASH = (new BasicAttackAnimation(0.15F, 0.85F, 1.5F, 1.2F, null, biped.toolR, "biped/combat/bahamut_dash", biped))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.7F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F));
        BAHAMUT_AIRSLASH = new BasicAttackAnimation(0.15F, 0.65F, 0.95F, 0.85F,
                null, biped.toolR, "biped/combat/bahamut_airslash", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);

        BAHAMUT_AUTO1 = new BasicAttackAnimation(0.15F, 0.65F, 0.95F, 0.85F,
                null, biped.toolR, "biped/combat/bahamut_auto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        BAHAMUT_AUTO2 = new BasicAttackAnimation(0.15F, "biped/combat/bahamut_auto2", biped,
                new AttackAnimation.Phase(0.45F, 0.65F, 0.65F, 0.9F, 1.5F, 1.0F, biped.toolR, null),
                new AttackAnimation.Phase(1.0F, 1.4F, 1.4F, 1.8F, 2.0F, Float.MAX_VALUE, biped.toolR, null))
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.9F);
        BAHAMUT_AUTO3 = new BasicAttackAnimation(0.15F, "biped/combat/bahamut_auto3", biped,
                new AttackAnimation.Phase(0.45F, 0.65F, 0.65F, 0.9F, 1.5F, 1.0F, biped.toolR, null),
                new AttackAnimation.Phase(1.0F, 1.25F, 1.2F, 1.8F, 2.0F, Float.MAX_VALUE, biped.toolR, null))
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        BAHAMUT_AUTO4 = new BasicAttackAnimation(0.15F, "biped/combat/bahamut_auto4", biped,
                new AttackAnimation.Phase(0.45F, 0.65F, 0.65F, 0.9F, 1.5F, 1.0F, biped.toolR, null),
                new AttackAnimation.Phase(1.0F, 1.5F, 1.5F, 1.8F, 2.0F, Float.MAX_VALUE, biped.toolR, null))
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);

        BAHAMUT_RAUTO1 = new BasicAttackAnimation(0.1F, 0.0F, 0.0F, 0.4F,
                null, biped.toolR, "biped/combat/bahamut_rauto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        BAHAMUT_RAUTO2 = new BasicAttackAnimation(0.15F, 0.35F, 0.95F, 0.85F,
                null, biped.toolR, "biped/combat/bahamut_rauto2", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        BAHAMUT_LAUTO1 = new BasicAttackAnimation(0.1F, 0.0F, 0.0F, 0.4F,
                null, biped.toolR, "biped/combat/bahamut_lauto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        BAHAMUT_LAUTO2 = new BasicAttackAnimation(0.15F, 0.35F, 0.95F, 0.85F,
                null, biped.toolR, "biped/combat/bahamut_rauto2", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
        BAHAMUT_BAUTO1 = new BasicAttackAnimation(0.15F, 0.35F, 0.95F, 1.0F,
                null, biped.toolR, "biped/combat/bahamut_bauto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.8F);
        BAHAMUT_BAUTO2 = new BasicAttackAnimation(0.15F, 0.55F, 0.95F, 0.85F,
                null, biped.toolR, "biped/combat/bahamut_bauto2", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);

                //---//---//--//
            /** Midas **/
        //---//---//--//

        HOLD_MIDAS = new StaticAnimation(true, "biped/living/hold_midas", biped);
        WALK_MIDAS = new MovementAnimation(true, "biped/living/walk_midas", biped);
        RUN_MIDAS = new MovementAnimation(true, "biped/living/run_midas", biped);

        MIDAS_AUTO1 = new BasicAttackAnimation(0.15F, 0.45F, 0.95F, 1.0F,
                null, biped.toolR, "biped/combat/midas_auto1", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(3.5f))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.8F);
        MIDAS_AUTO2 = new BasicAttackAnimation(0.15F, 0.75F, 1.1F, 1.0F,
                null, biped.toolR, "biped/combat/midas_auto2", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.0f))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.8F);
        MIDAS_AUTO3 = new BasicAttackAnimation(0.15F, 1.1F, 1.5F, 1.65F,
                null, biped.toolR, "biped/combat/midas_auto3", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.5f))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 3.2F)
                .addEvents(
                        //AnimationEvent.TimeStampedEvent.create(0.75F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -2.5F, -4.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(1.1F, PierceTHAnimations.ReusableSources.FRACTURE_GROUND_SPEED_BASED, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -1.5F, -1.0F), Armatures.BIPED.rootJoint, 1.1D, 10D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(1.0F, ReusableSources.SCREENSHAKE, AnimationEvent.Side.CLIENT).params((int)5, (float)3.0, (float)20.0),
                        AnimationEvent.TimeStampedEvent.create(1.15F, ReusableSources.DUST_CLOUD, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -1.5F, 0.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F));
        MIDAS_AUTO4 = new BasicAttackAnimation(0.15F, 0.75F, 1.1F, 1.0F,
                null, biped.toolR, "biped/combat/midas_auto4", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(4.0f))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 3.1F);

        MIDAS_DASH = (new BasicAttackAnimation(0.1F, 0.65F, 1.2F, 1.4F, null, biped.toolR, "biped/combat/midas_dash", biped))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(7.0f))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 3.2F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true);
        MIDAS_AIRSLASH = (new BasicAttackAnimation(0.1F, 0.55F, 1.0F, 0.9F, null, biped.toolR, "biped/combat/midas_airslash", biped))
                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(5.0f))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.8F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, entitypatch, speed, elapsedTime) -> {
                    if (elapsedTime >= 0.5F && elapsedTime < 0.75F) {
                        float dpx = (float) entitypatch.getOriginal().getX();
                        float dpy = (float) entitypatch.getOriginal().getY();
                        float dpz = (float) entitypatch.getOriginal().getZ();
                        BlockState block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx,dpy,dpz));

                        while ((block.getBlock() instanceof BushBlock || block.isAir()) && !block.is(Blocks.VOID_AIR)) {
                            dpy--;
                            block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx,dpy,dpz));
                        }

                        float distanceToGround = (float) Math.max(Math.abs(entitypatch.getOriginal().getY() - dpy)-1, 0.0F);

                        return 1 - (1 / (-distanceToGround - 1.F) + 1.0f);
                    }

                    return 1.0F;
                })
                .addEvents(
                        AnimationEvent.TimeStampedEvent.create(0.65F, ReusableSources.DUST_CLOUD, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.5F, 0.0F), Armatures.BIPED.rootJoint, 1.1D, 0.55F),
                        AnimationEvent.TimeStampedEvent.create(0.65F, PierceTHAnimations.ReusableSources.FRACTURE_GROUND_SPEED_BASED, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.5F, -1.0F), Armatures.BIPED.rootJoint, 1.0D, 10D, 0.55F));

                //---//---//--//
            /** Pale Carver **/
        //---//---//--//

        HOLD_CARVER = new StaticAnimation(true, "biped/living/hold_carver", biped);
        WALK_CARVER = new MovementAnimation(true, "biped/living/walk_carver", biped);
        RUN_CARVER = new MovementAnimation(true, "biped/living/run_carver", biped);

        CARVER_AUTO1 = new BasicAttackAnimation(0.1F, 0.75F, 0.9F, 1.2F,
                null, biped.toolR, "biped/combat/carver_auto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 3.2F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true);
        CARVER_AUTO2 = new BasicAttackAnimation(0.1F, 0.7F, 0.9F, 1.2F,
                null, biped.toolR, "biped/combat/carver_auto2", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 3.3F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true);
        CARVER_AUTO3 = new BasicAttackAnimation(0.1F, 1.0F, 1.5F, 1.8F,
                null, biped.toolR, "biped/combat/carver_auto3", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 3.4F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true);

        CARVER_DASH = (new BasicAttackAnimation(0.1F, 0.75F, 1.0F, 1.8F, null, biped.toolR, "biped/combat/carver_dash", biped))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 4.0F)
                .addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true);


    }

    public static class ReusableSources {
        public static final AnimationEvent.AnimationEventConsumer SCREENSHAKE = (entitypatch, animation, params) -> {
            int duration = (int)params[0];
            float strength = (float)params[1];
            float freq = (float)params[2];
            //CameraEngine.getInstance().shakeCamera(duration, strength, freq);
        };

        public static final AnimationEvent.AnimationEventConsumer DUST_CLOUD = (entitypatch, animation, params) -> {
            Random random = new Random();
            Vec3 position = entitypatch.getOriginal().position();
            OpenMatrix4f modelTransform = entitypatch.getArmature().getBindedTransformFor(animation.getPoseByTime(entitypatch, (float)params[3], 1.0F), (Joint) params[1])
                    .mulFront(
                            OpenMatrix4f.createTranslation((float)position.x, (float)position.y, (float)position.z)
                                    .mulBack(OpenMatrix4f.createRotatorDeg(180.0F, Vec3f.Y_AXIS)
                                            .mulBack(entitypatch.getModelMatrix(1.0F))));

            Level level = entitypatch.getOriginal().level();
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

        public static final AnimationEvent.AnimationEventConsumer FRACTURE_GROUND_SPEED_BASED = (entitypatch, animation, params) -> {
            Logger logger = LogManager.getLogger(PiercethGreatsword.MODID);

            Vec3f offset = (Vec3f) params[0];
            Joint rootJoint = (Joint) params[1];
            double minStrength = (double) params[2];
            double maxStrength = (double) params[3];
            float duration = (float) params[4];
            float maxDist = 50;

            float fallDist = 5;//entitypatch.getOriginal().;
            logger.debug("DISTANCE: " + fallDist);
            double strength = (maxStrength - minStrength) * (fallDist/maxDist) + minStrength;
            Animations.ReusableSources.FRACTURE_GROUND_SIMPLE.fire(entitypatch, animation, offset, rootJoint, strength, duration);
        };
    }
}
