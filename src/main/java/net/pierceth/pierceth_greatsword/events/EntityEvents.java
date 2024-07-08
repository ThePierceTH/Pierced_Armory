package net.pierceth.pierceth_greatsword.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.compat.efm.gameasset.PierceTHSkills;
import net.pierceth.pierceth_greatsword.common.capabilities.item.VOSWeaponCapability;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeSkill;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.EntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.skill.CapabilitySkill;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class EntityEvents {
    @SubscribeEvent
    public static void equipChangeEvent(LivingEquipmentChangeEvent event) {
        Logger logger = LogManager.getLogger(Constants.MOD_ID);

        EntityPatch<Entity> entitypatch = EpicFightCapabilities.getEntityPatch(event.getEntity(), EntityPatch.class);

        logger.debug("Equip Changed");

        if (entitypatch instanceof PlayerPatch) {
            logger.debug("Is Player");

            CapabilityItem itemCap = ((PlayerPatch) entitypatch).getHoldingItemCapability(InteractionHand.MAIN_HAND);

            if (itemCap instanceof VOSWeaponCapability)
                logger.debug("Is VOS Capability");

            PlayerPatch<?> player = (PlayerPatch<?>) entitypatch;
            CapabilitySkill skillCapability = player.getSkillCapability();

            if (itemCap != null && itemCap instanceof VOSWeaponCapability) {
                if (((VOSWeaponCapability) itemCap).isDirectional()) {
                    logger.debug("Is Directional");
                    skillCapability.skillContainers[SkillSlots.BASIC_ATTACK.universalOrdinal()].setSkill(PierceTHSkills.DIRECTIONAL_BASIC_ATTACK);
                    EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.BASIC_ATTACK, PierceTHSkills.DIRECTIONAL_BASIC_ATTACK.toString(), SPChangeSkill.State.ENABLE), (ServerPlayer) event.getEntity());
                } else {
                    logger.debug("Not Directional");
                    skillCapability.skillContainers[SkillSlots.BASIC_ATTACK.universalOrdinal()].setSkill(EpicFightSkills.BASIC_ATTACK);
                    EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.BASIC_ATTACK, EpicFightSkills.BASIC_ATTACK.toString(), SPChangeSkill.State.ENABLE), (ServerPlayer) event.getEntity());
                }
            }
            else {
                logger.debug("Not Directional");
                skillCapability.skillContainers[SkillSlots.BASIC_ATTACK.universalOrdinal()].setSkill(EpicFightSkills.BASIC_ATTACK);
                EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.BASIC_ATTACK, EpicFightSkills.BASIC_ATTACK.toString(), SPChangeSkill.State.ENABLE), (ServerPlayer) event.getEntity());
            }
        }
    }
}
