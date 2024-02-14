package net.pierceth.pierceth_greatsword.mixin;

import net.pierceth.pierceth_greatsword.gameasset.PierceTHAnimations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ImpactGuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;

@Mixin(value = ImpactGuardSkill.class)
public class MixinImpactGuard {

    @Inject(method = "createEnergizingGuardBuilder", at = @At("RETURN"), cancellable = true, remap = false)
    private static void createEnergizingGuardBuilder(CallbackInfoReturnable<GuardSkill.Builder> ci) {
        ci.setReturnValue(GuardSkill.createGuardBuilder()
                .addAdvancedGuardMotion(WeaponCategories.LONGSWORD, (item, player) -> Animations.LONGSWORD_GUARD_HIT)
                .addAdvancedGuardMotion(WeaponCategories.SPEAR, (item, player) -> item.getStyle(player) == Styles.TWO_HAND ? Animations.SPEAR_GUARD_HIT : null)
                .addAdvancedGuardMotion(WeaponCategories.TACHI, (item, player) -> Animations.LONGSWORD_GUARD_HIT)
                .addAdvancedGuardMotion(WeaponCategories.GREATSWORD, (item, player) -> item.getStyle(player) == Styles.TWO_HAND ?
                        PierceTHAnimations.ROYAL_GREATSWORD_GUARD_HIT : // <<< Royal GS Guard Hit Here
                        Animations.GREATSWORD_GUARD_HIT) // <<< This is Normal GS
        );
    }
}
