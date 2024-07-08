package net.pierceth.pierceth_greatsword.mixin;

import net.pierceth.pierceth_greatsword.compat.efm.gameasset.PierceTHAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;

@Mixin(value = GuardSkill.class)
public class MixinGuard {

    @Inject(method = "createGuardBuilder", at = @At("RETURN"), cancellable = true, remap = false)
    private static void createGuardBuilder(CallbackInfoReturnable<GuardSkill.Builder> ci) {
        ci.setReturnValue(new GuardSkill.Builder()
                .setCategory(SkillCategories.GUARD)
                .setActivateType(Skill.ActivateType.ONE_SHOT)
                .setResource(Skill.Resource.STAMINA)
                .addGuardMotion(WeaponCategories.AXE, (item, player) -> Animations.SWORD_GUARD_HIT)
                .addGuardMotion(WeaponCategories.GREATSWORD, (item, player) -> item.getStyle(player) == Styles.ONE_HAND ?
                        PierceTHAnimations.ROYAL_GREATSWORD_GUARD_HIT : // <<< Royal GS Guard Hit Here
                        Animations.GREATSWORD_GUARD_HIT) // <<< This is Normal GS
                .addGuardMotion(WeaponCategories.UCHIGATANA, (item, player) -> Animations.UCHIGATANA_GUARD_HIT)
                .addGuardMotion(WeaponCategories.LONGSWORD, (item, player) -> Animations.LONGSWORD_GUARD_HIT)
                .addGuardMotion(WeaponCategories.SPEAR, (item, player) -> item.getStyle(player) == Styles.TWO_HAND ? Animations.SPEAR_GUARD_HIT : null)
                .addGuardMotion(WeaponCategories.SWORD, (item, player) -> item.getStyle(player) == Styles.ONE_HAND ? Animations.SWORD_GUARD_HIT : Animations.SWORD_DUAL_GUARD_HIT)
                .addGuardMotion(WeaponCategories.TACHI, (item, player) -> Animations.LONGSWORD_GUARD_HIT)
                .addGuardBreakMotion(WeaponCategories.AXE, (item, player) -> Animations.BIPED_COMMON_NEUTRALIZED)
                .addGuardBreakMotion(WeaponCategories.GREATSWORD, (item, player) -> item.getStyle(player) == Styles.ONE_HAND ?
                        PierceTHAnimations.ROYAL_GREATSWORD_GUARD_BREAK : // <<< Royal GS Guard Break Here
                        Animations.GREATSWORD_GUARD_BREAK)  // <<< This is Normal GS
                .addGuardBreakMotion(WeaponCategories.UCHIGATANA, (item, player) -> Animations.BIPED_COMMON_NEUTRALIZED)
                .addGuardBreakMotion(WeaponCategories.LONGSWORD, (item, player) -> Animations.BIPED_COMMON_NEUTRALIZED)
                .addGuardBreakMotion(WeaponCategories.SPEAR, (item, player) -> Animations.BIPED_COMMON_NEUTRALIZED)
                .addGuardBreakMotion(WeaponCategories.SWORD, (item, player) -> Animations.BIPED_COMMON_NEUTRALIZED)
                .addGuardBreakMotion(WeaponCategories.TACHI, (item, player) -> Animations.BIPED_COMMON_NEUTRALIZED)
        );
    }
}
