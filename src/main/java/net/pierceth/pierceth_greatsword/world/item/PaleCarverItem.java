package net.pierceth.pierceth_greatsword.world.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.pierceth.pierceth_greatsword.renderer.BahamutRenderer;
import net.pierceth.pierceth_greatsword.renderer.PaleCarverRenderer;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import yesman.epicfight.world.item.WeaponItem;

import java.util.function.Consumer;

public class PaleCarverItem extends WeaponItem implements GeoAnimatable {

    private final float attackDamage;
    private final float attackSpeed;

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public PaleCarverItem(Item.Properties build, Tiers tier) {
        super(tier, 0, 0, build);
        this.attackDamage = 8.0F;
        this.attackSpeed= 1.0F;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new PaleCarverRenderer();

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
