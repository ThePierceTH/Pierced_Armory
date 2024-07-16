package net.pierceth.pierceth_greatsword.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.pierceth.pierceth_greatsword.CommonClass;
import net.pierceth.pierceth_greatsword.common.world.item.VOSWeaponItem;
import software.bernie.geckolib.model.GeoModel;

import java.util.Objects;

public class VOSWeaponModel extends GeoModel<VOSWeaponItem> {

    @Override
    public ResourceLocation getModelResource(VOSWeaponItem animatable) {
        return CommonClass.customLocation("geo/" + getWeaponName(animatable) + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VOSWeaponItem animatable) {
        return CommonClass.customLocation("textures/item/" + getWeaponName(animatable) + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(VOSWeaponItem animatable) {
        return CommonClass.customLocation("animations/" + getWeaponName(animatable) + ".animation.json");
    }

    private String getWeaponName(VOSWeaponItem weapon) {
        ResourceLocation name = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(weapon));
        return name.getPath();
    }
}
