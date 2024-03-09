package net.pierceth.pierceth_greatsword.gameasset;


import net.pierceth.pierceth_greatsword.PiercethGreatsword;
//import net.pierceth.pierceth_greatsword.skill.passive.HoundGreatsword;
import net.pierceth.pierceth_greatsword.skill.passive.RoyalGreatswordMastery;
import net.pierceth.pierceth_greatsword.skill.weaponinnate.DragonClawSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.world.item.PierceTHCreativeTabs;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.data.reloader.SkillManager;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Set;

@Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public class PierceTHSkills {
    public static Skill ROYAL_GREATSWORD_MASTERY;
    public static Skill HOUND_GREATSWORD;
    public static Skill DRAGON_CLAW;

    public static void registerSkills(){
        SkillManager.register(RoyalGreatswordMastery::new, PassiveSkill.createPassiveBuilder().setCategory(SkillCategories.PASSIVE), PiercethGreatsword.MODID, "royal_greatsword");
        //SkillManager.register(HoundGreatsword::new, PassiveSkill.createPassiveBuilder().setCreativeTab(PierceTHCreativeTabs.ITEMS), PiercethGreatsword.MODID, "hound_greatsword");
        SkillManager.register(DragonClawSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(new ResourceLocation(PiercethGreatsword.MODID, "biped/skill/dragon_claw")), PiercethGreatsword.MODID, "dragon_claw");
       }
    @SubscribeEvent
    public static void buildSkillEvent(SkillBuildEvent onBuild) {
        ROYAL_GREATSWORD_MASTERY = onBuild.build(PiercethGreatsword.MODID, "royal_greatsword");
        //HOUND_GREATSWORD = onBuild.build(PiercethGreatsword.MODID, "hound_greatsword");
        WeaponInnateSkill DragonClaw = onBuild.build(PiercethGreatsword.MODID, "dragon_claw");
        DragonClaw.newProperty()
                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.adder(20.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(10.0F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(ExtraDamageInstance.SWEEPING_EDGE_ENCHANTMENT.create()))
                .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE))
                .registerPropertiesToAnimation();
        DRAGON_CLAW = DragonClaw;
        }
}
