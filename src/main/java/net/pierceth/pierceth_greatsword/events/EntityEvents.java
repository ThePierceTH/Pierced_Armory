package net.pierceth.pierceth_greatsword.events;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pierceth.pierceth_greatsword.PiercethGreatsword;
import net.pierceth.pierceth_greatsword.gameasset.PierceTHSkills;
import net.pierceth.pierceth_greatsword.world.capabilities.item.VOSWeaponCapability;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.EntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.provider.ItemCapabilityProvider;
import yesman.epicfight.world.capabilities.skill.CapabilitySkill;

@Mod.EventBusSubscriber(modid= PiercethGreatsword.MODID)
public class EntityEvents {
    @SubscribeEvent
    public static void equipChangeEvent(LivingEquipmentChangeEvent event) {
        Logger logger = LogManager.getLogger(PiercethGreatsword.MODID);

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
                } else {
                    logger.debug("Not Directional");
                    skillCapability.skillContainers[SkillSlots.BASIC_ATTACK.universalOrdinal()].setSkill(EpicFightSkills.BASIC_ATTACK);
                }
            }
            else {
                logger.debug("Not Directional");
                skillCapability.skillContainers[SkillSlots.BASIC_ATTACK.universalOrdinal()].setSkill(EpicFightSkills.BASIC_ATTACK);
            }
        }
    }
}
