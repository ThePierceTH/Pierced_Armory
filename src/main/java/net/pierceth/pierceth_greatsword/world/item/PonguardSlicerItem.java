package net.pierceth.pierceth_greatsword.world.item;

import java.util.UUID;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.pierceth.pierceth_greatsword.renderer.BahamutRenderer;
import net.pierceth.pierceth_greatsword.renderer.PonguardSlicerRenderer;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import yesman.epicfight.world.item.WeaponItem;

public class PonguardSlicerItem extends WeaponItem implements GeoAnimatable {
        protected static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("16295ED8-B092-4A75-9A94-BCD8D56668BB");
        private final float attackDamage;
        private final float attackSpeed;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

        @SuppressWarnings("deprecation")
        public PonguardSlicerItem(Item.Properties build, Tier tier) {
            super(tier, 0, 0.0F, build);
            this.attackDamage = 7.0F + tier.getAttackDamageBonus();
            this.attackSpeed = -2.85F - (0.05F * tier.getLevel());
        }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity pTarget, LivingEntity pAttacker) {
            pTarget.setSecondsOnFire(2);
        return super.hurtEnemy(itemStack, pTarget, pAttacker);
    }

    @Override
        public int getEnchantmentValue() {
            return 5;
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            if (slot == EquipmentSlot.MAINHAND) {
                Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
                builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, Operation.ADDITION));
                builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, Operation.ADDITION));
                return builder.build();
            }

            return super.getAttributeModifiers(slot, stack);
}

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new PonguardSlicerRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}
