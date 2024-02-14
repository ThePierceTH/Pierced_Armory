package net.pierceth.pierceth_greatsword.mixin;

import net.pierceth.pierceth_greatsword.gameasset.PierceTHAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;

@Mixin(value = ParryingSkill.class)
public class MixinParry {

    @Inject(method = "createActiveGuardBuilder", at = @At("RETURN"), cancellable = true, remap = false)
    private static void createActiveGuardBuilder(CallbackInfoReturnable<GuardSkill.Builder> ci) {
        ci.setReturnValue(GuardSkill.createGuardBuilder()
                .addAdvancedGuardMotion(CapabilityItem.WeaponCategories.SWORD, (itemCap, playerpatch) -> itemCap.getStyle(playerpatch) == Styles.ONE_HAND ?
                        new StaticAnimation[] { Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2 } :
                        new StaticAnimation[] { Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3 })
                .addAdvancedGuardMotion(WeaponCategories.LONGSWORD, (itemCap, playerpatch) ->
                        new StaticAnimation[] { Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2 })
                .addAdvancedGuardMotion(WeaponCategories.UCHIGATANA, (itemCap, playerpatch) ->
                        new StaticAnimation[] { Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2 })
                .addAdvancedGuardMotion(WeaponCategories.TACHI, (itemCap, playerpatch) ->
                        new StaticAnimation[] { Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2 })
                .addAdvancedGuardMotion(WeaponCategories.GREATSWORD, (itemCap, playerpatch) -> itemCap.getStyle(playerpatch) == Styles.ONE_HAND ?
                        new StaticAnimation[] { PierceTHAnimations.ROYAL_GREATSWORD_GUARD_ACTIVE_HIT1, PierceTHAnimations.ROYAL_GREATSWORD_GUARD_ACTIVE_HIT2 } : null) // <<< Royal GS Parry Hits Here
        );
    }
}
