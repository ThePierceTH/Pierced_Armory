package net.pierceth.pierceth_greatsword.skill;

import io.netty.buffer.Unpooled;
import net.minecraft.client.player.Input;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.network.client.CPExecuteSkill;
import yesman.epicfight.skill.*;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.BasicAttackEvent;
import yesman.epicfight.world.entity.eventlistener.ComboCounterHandleEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;
import yesman.epicfight.world.entity.eventlistener.SkillConsumeEvent;

import java.util.List;
import java.util.UUID;

public class DirectionalBasicAttack extends BasicAttack {
    private static final UUID EVENT_UUID = UUID.fromString("52c3ecc9-3167-4217-bd7d-2fa889e3ec1f");

    public DirectionalBasicAttack(Builder<? extends Skill> builder) {
        super(builder);
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
        StaticAnimation attackMotion = null;
        ServerPlayer player = executer.getOriginal();
        SkillContainer skillContainer = executer.getSkill(this);
        SkillDataManager dataManager = skillContainer.getDataManager();
        int comboCounter = dataManager.getDataValue(SkillDataKeys.COMBO_COUNTER.get());

        if (player.isPassenger()) {
            Entity entity = player.getVehicle();

            if ((entity instanceof PlayerRideableJumping ridable && ridable.canJump()) && cap.availableOnHorse() && cap.getMountAttackMotion() != null) {
                comboCounter %= cap.getMountAttackMotion().size();
                attackMotion = cap.getMountAttackMotion().get(comboCounter);
                comboCounter++;
            }
        } else {
            int fw = args.readInt();
            int sw = args.readInt();

            List<StaticAnimation> combo = cap.getAutoAttckMotion(executer);
            int comboSize = combo.size();
            boolean dashAttack = player.isSprinting();

            if (dashAttack) {
                // Dash Attack
                comboCounter = comboSize - 2;
            }
            else if(sw == -1) {
                // Right Attack
                comboCounter = 0;
            }
            else if(sw == 1) {
                // Left Attack
                comboCounter = 0;
            }
            else if(fw == -1) {
                // Back Attack
                comboCounter = 0;
            }
            else {
                // Normal Attack
                comboCounter %= comboSize - 2;
            }

            attackMotion = combo.get(comboCounter);
            comboCounter = dashAttack ? 0 : comboCounter + 1;
        }

        setComboCounterWithEvent(ComboCounterHandleEvent.Causal.ACTION_ANIMATION_RESET, executer, skillContainer, attackMotion, comboCounter);

        if (attackMotion != null) {
            executer.playAnimationSynchronized(attackMotion, 0);
        }

        executer.updateEntityState();
    }
}
