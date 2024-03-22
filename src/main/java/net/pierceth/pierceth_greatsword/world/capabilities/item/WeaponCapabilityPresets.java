package net.pierceth.pierceth_greatsword.world.capabilities.item;

import java.util.Map;
import java.util.function.Function;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.gameasset.PierceTHAnimations;
import com.google.common.collect.Maps;

import net.pierceth.pierceth_greatsword.gameasset.PierceTHSkills;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraft.world.item.Item;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

@Mod.EventBusSubscriber(modid = PiercethGreatsword.MODID , bus = Bus.MOD)
public class WeaponCapabilityPresets {

    // Example
    public static final Function<Item, CapabilityItem.Builder> GREATSWORD = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(WeaponCategories.GREATSWORD)
                .styleProvider((playerpatch) ->
                {
                    if (CheckPlayer(playerpatch)) {
                        return Styles.TWO_HAND;
                    }
                    if (((PlayerPatch) playerpatch).getSkill(PierceTHSkills.ROYAL_GREATSWORD_MASTERY) != null)
                        {
                            return Styles.ONE_HAND;
                        }
                    else
                    {
                        return Styles.TWO_HAND;
                    }
                })
                .collider(ColliderPreset.GREATSWORD)
                .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                .hitSound(EpicFightSounds.BLADE_HIT.get())
                .canBePlacedOffhand(false)
                .newStyleCombo(Styles.ONE_HAND, PierceTHAnimations.ROYAL_GREATSWORD_AUTO1, PierceTHAnimations.ROYAL_GREATSWORD_AUTO2, PierceTHAnimations.ROYAL_GREATSWORD_AUTO3, PierceTHAnimations.ROYAL_GREATSWORD_AUTO4, PierceTHAnimations.ROYAL_GREATSWORD_DASH, PierceTHAnimations.ROYAL_GREATSWORD_AIR_SLASH)
                .newStyleCombo(Styles.TWO_HAND, Animations.GREATSWORD_AUTO1, Animations.GREATSWORD_AUTO2, Animations.GREATSWORD_DASH, Animations.GREATSWORD_AIR_SLASH)
                .innateSkill(Styles.TWO_HAND, (itemStack) -> EpicFightSkills.STEEL_WHIRLWIND)
                .innateSkill(Styles.ONE_HAND, (itemStack) -> PierceTHSkills.DRAGON_CLAW)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.IDLE, PierceTHAnimations.BIPED_HOLD_ROYAL_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.WALK, PierceTHAnimations.BIPED_WALK_ROYAL_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CHASE, PierceTHAnimations.BIPED_WALK_ROYAL_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.RUN, PierceTHAnimations.BIPED_RUN_ROYAL_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.KNEEL, PierceTHAnimations.BIPED_HOLD_ROYAL_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SNEAK, PierceTHAnimations.BIPED_HOLD_ROYAL_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.ONE_HAND, LivingMotions.BLOCK, PierceTHAnimations.ROYAL_GREATSWORD_GUARD);
        //.livingMotionModifier(Styles.SHEATH, LivingMotions.WALK, PierceTHAnimations.BIPED_WALK_HOUND_GREATSWORD);

        return builder;
    };

            //---//---//--//
        /** Bahamut **/
    //---//---//--//

    public static final Function<Item, CapabilityItem.Builder> BAHAMUT = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(PierceWeaponCategories.BAHAMUT)
                .styleProvider((playerpatch) -> Styles.TWO_HAND)
                .collider(ColliderPreset.SPEAR)
                .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                .hitSound(EpicFightSounds.BLADE_HIT.get())
                .canBePlacedOffhand(false)
                .newStyleCombo(Styles.TWO_HAND, PierceTHAnimations.BAHAMUT_AUTO1, PierceTHAnimations.BAHAMUT_AUTO2, PierceTHAnimations.BAHAMUT_DASH, Animations.GREATSWORD_AIR_SLASH)
                .innateSkill(Styles.TWO_HAND, (itemStack) -> PierceTHSkills.DRAGON_CLAW)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, PierceTHAnimations.HOLD_BAHAMUT)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, PierceTHAnimations.WALK_BAHAMUT)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, PierceTHAnimations.RUN_BAHAMUT)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_GREATSWORD)
                .livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);

        return builder;
    };


    private static boolean CheckPlayer(LivingEntityPatch<?> playerpatch) {
        return playerpatch.getOriginal().getType() != EntityType.PLAYER;
    }

    private static final Map<String, Function<Item, CapabilityItem.Builder>> PRESETS = Maps.newHashMap();

    // Register The Capabilities
    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(PiercethGreatsword.MODID,"greatsword"), GREATSWORD);
        event.getTypeEntry().put(new ResourceLocation(EpicFightMod.MODID,"greatsword"), GREATSWORD);
        event.getTypeEntry().put(new ResourceLocation(PiercethGreatsword.MODID,"bahamut"), BAHAMUT);
        }
    }
