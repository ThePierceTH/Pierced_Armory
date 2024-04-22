package net.pierceth.pierceth_greatsword.skill;

import java.util.List;
import java.util.UUID;

import net.minecraft.world.item.ItemStack;
import net.pierceth.pierceth_greatsword.data.AnimConfig;
import net.pierceth.pierceth_greatsword.world.capabilities.item.VOSWeaponCapability;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.buffer.Unpooled;
import net.minecraft.client.player.Input;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.client.CPExecuteSkill;
import yesman.epicfight.network.server.SPSkillExecutionFeedback;
import yesman.epicfight.skill.*;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.BasicAttackEvent;
import yesman.epicfight.world.entity.eventlistener.ComboCounterHandleEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;
import yesman.epicfight.world.entity.eventlistener.SkillConsumeEvent;

public class DirectionalBasicAttack extends BasicAttack {
    private static final UUID EVENT_UUID = UUID.fromString("52c3ecc9-3167-4217-bd7d-2fa889e3ec1f");

    public DirectionalBasicAttack(Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        SkillConsumeEvent event = new SkillConsumeEvent(executer, this, this.resource, true);
        executer.getEventListener().triggerEvents(EventType.SKILL_CONSUME_EVENT, event);

        if (!event.isCanceled()) {
            event.getResourceType().consumer.consume(this, executer, event.getAmount());
        }

        if (executer.getEventListener().triggerEvents(EventType.BASIC_ATTACK_EVENT, new BasicAttackEvent(executer))) {
            return;
        }

        CapabilityItem cap = executer.getHoldingItemCapability(InteractionHand.MAIN_HAND);
        if (!(cap instanceof VOSWeaponCapability))
            return;

        StaticAnimation attackMotion = null;
        ServerPlayer player = executer.getOriginal();
        SkillContainer skillContainer = executer.getSkill(this);
        SkillDataManager dataManager = skillContainer.getDataManager();
        int comboCounter = dataManager.getDataValue(SkillDataKeys.COMBO_COUNTER.get());

        if (player.isPassenger()) {
            Entity entity = player.getVehicle();

            if ((entity instanceof PlayerRideableJumping ridable && ridable.canJump()) && cap.availableOnHorse() && cap.getMountAttackMotion() != null) {
                comboCounter %= cap.getMountAttackMotion().size();
                attackMotion = cap.getMountAttackMotion().get(comboCounter).get();
                comboCounter++;
            }
        } else {
            int fw = args.readInt();
            int sw = args.readInt();
            List<AnimationProvider<?>> combo = cap.getAutoAttckMotion(executer);
            List<AnimConfig> configs = ((VOSWeaponCapability) cap).getAnimConfigs(executer);
            int comboSize = combo.size();
            boolean dashAttack = player.isSprinting();

            if (dashAttack) {
                // Dash Attack
                System.out.println("Dash Attack");
                comboCounter = comboCounterValid(AnimConfig.DASH_COMBO, configs, comboCounter);
            }
            else if(sw == -1) {
                // Right Attack
                System.out.println("Right Attack");
                comboCounter = comboCounterValid(AnimConfig.RIGHT_LIGHT_COMBO, configs, comboCounter);
            }
            else if(sw == 1) {
                // Left Attack
                System.out.println("Left Attack");
                comboCounter = comboCounterValid(AnimConfig.LEFT_LIGHT_COMBO, configs, comboCounter);
            }
            else if(fw == -1) {
                // Back Attack
                System.out.println("Back Attack");
                comboCounter = comboCounterValid(AnimConfig.BACK_LIGHT_COMBO, configs, comboCounter);
            }
            else {
                // Normal Attack
                System.out.println("Normal Attack");
                comboCounter = comboCounterValid(AnimConfig.LIGHT_COMBO, configs, comboCounter);
            }


            System.out.println("Combo Counter: " + comboCounter);
            attackMotion = combo.get(comboCounter).get();
            comboCounter++;
        }

        setComboCounterWithEvent(ComboCounterHandleEvent.Causal.ACTION_ANIMATION_RESET, executer, skillContainer, attackMotion, comboCounter);

        if (attackMotion != null) {
            executer.playAnimationSynchronized(attackMotion, 0);
        }

        executer.updateEntityState();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void executeOnClient(LocalPlayerPatch executer, FriendlyByteBuf args) {

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public FriendlyByteBuf gatherArguments(LocalPlayerPatch executer, ControllEngine controllEngine) {
        Input input = executer.getOriginal().input;
        float pulse = Mth.clamp(0.3F + EnchantmentHelper.getSneakingSpeedBonus(executer.getOriginal()), 0.0F, 1.0F);
        input.tick(false, pulse);

        int forward = input.up ? 1 : 0;
        int backward = input.down ? -1 : 0;
        int left = input.left ? 1 : 0;
        int right = input.right ? -1 : 0;

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(forward);
        buf.writeInt(backward);
        buf.writeInt(left);
        buf.writeInt(right);

        return buf;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Object getExecutionPacket(LocalPlayerPatch executer, FriendlyByteBuf args) {
        int forward = args.readInt();
        int backward = args.readInt();
        int left = args.readInt();
        int right = args.readInt();
        int vertic = forward + backward;
        int horizon = left + right;

        CPExecuteSkill packet = new CPExecuteSkill(executer.getSkill(this).getSlotId());
        packet.getBuffer().writeInt(Integer.compare(vertic, 0));
        packet.getBuffer().writeInt(Integer.compare(horizon, 0));

        return packet;
    }

    private int getComboInitialIndex(AnimConfig animcConfig, List<AnimConfig> animConfigs) {
        int origIndex = animConfigs.indexOf(animcConfig);

        System.out.println("AnimConfig Index: " + origIndex);

        int totalSize = 0;
        for (int i = 0; i < origIndex; i++) {
            totalSize += animConfigs.get(i).getComboSize();
        }

        return totalSize;
    }

    private int comboCounterValid(AnimConfig animConfig, List<AnimConfig> animConfigs, int comboCounter) {
        int initialIndex = getComboInitialIndex(animConfig, animConfigs);
        int finalIndex = initialIndex + animConfig.getComboSize() - 1;

        System.out.println("Starting Index: " + initialIndex);
        System.out.println("Ending Index: " + finalIndex);

        return comboCounter >= initialIndex && comboCounter <= finalIndex ? comboCounter : initialIndex;
    }
}
