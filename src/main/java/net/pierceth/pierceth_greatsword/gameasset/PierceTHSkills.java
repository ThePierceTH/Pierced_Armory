package net.pierceth.pierceth_greatsword.gameasset;


import java.util.Set;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
//import net.pierceth.pierceth_greatsword.skill.passive.HoundGreatsword;
import net.pierceth.pierceth_greatsword.skill.DirectionalBasicAttack;
import net.pierceth.pierceth_greatsword.skill.passive.RoyalGreatswordMastery;
//import net.pierceth.pierceth_greatsword.skill.weaponinnate.DirectionalInnateSkill;
import net.pierceth.pierceth_greatsword.skill.weaponinnate.DragonClawSkill;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent.ModRegistryWorker;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

@Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class PierceTHSkills {

    public static Skill DIRECTIONAL_BASIC_ATTACK;
    public static Skill DIRECTIONAL_INNATE_SKILL;
    public static Skill ROYAL_GREATSWORD_MASTERY;
    public static Skill DRAGON_CLAW;

    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent onBuild) {
    	ModRegistryWorker registryWorker = onBuild.createRegistryWorker(PiercethGreatsword.MODID);
    	
        DIRECTIONAL_BASIC_ATTACK = registryWorker.build("directional_basic_attack", DirectionalBasicAttack::new, DirectionalBasicAttack.createBasicAttackBuilder());
        // TODO: Remove Comment
        //DIRECTIONAL_INNATE_SKILL = registryWorker.build("directional_basic_attack", DirectionalInnateSkill::new, DirectionalInnateSkill.createWeaponInnateBuilder());

        ROYAL_GREATSWORD_MASTERY = registryWorker.build("royal_greatsword", RoyalGreatswordMastery::new, PassiveSkill.createPassiveBuilder().setCategory(SkillCategories.PASSIVE));
        WeaponInnateSkill DragonClaw = registryWorker.build("dragon_claw", DragonClawSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(() -> (AttackAnimation)PierceTHAnimations.DRAGON_CLAW));
        DragonClaw.newProperty()
                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(10.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE));
        DRAGON_CLAW = DragonClaw;
        }
}
