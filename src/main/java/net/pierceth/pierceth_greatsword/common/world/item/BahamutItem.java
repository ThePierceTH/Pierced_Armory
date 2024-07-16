package net.pierceth.pierceth_greatsword.common.world.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.pierceth.pierceth_greatsword.Constants;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.item.EpicFightItemTier;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BahamutItem extends VOSWeaponItem {
    @OnlyIn(Dist.CLIENT)
    private List<Component> tooltipExpand;
    private float attackDamage;
    private double attackSpeed;

    public BahamutItem(Properties build) {
        super(EpicFightItemTier.UCHIGATANA, 0, -2.0F, build);
        if (EpicFightMod.isPhysicalClient()) {
            this.tooltipExpand = new ArrayList<Component>();
            this.tooltipExpand.add(Component.literal(""));
            this.tooltipExpand.add(Component.translatable("item." + Constants.MOD_ID + ".bahamut.tooltip"));
        }

        this.attackDamage = 5.0F;
        this.attackSpeed = -3.0F;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.QUARTZ;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }

        return super.getAttributeModifiers(slot, stack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        for (Component txtComp : tooltipExpand) {
            tooltip.add(txtComp);
        }
    }
}
