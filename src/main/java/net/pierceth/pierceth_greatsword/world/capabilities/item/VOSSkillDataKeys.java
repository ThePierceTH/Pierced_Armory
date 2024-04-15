package net.pierceth.pierceth_greatsword.world.capabilities.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.skill.DirectionalBasicAttack;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class VOSSkillDataKeys {
	public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation(EpicFightMod.MODID, "skill_data_keys"), EpicFightMod.MODID);
	
    public static final RegistryObject<SkillDataKey<Integer>> DIRECTIONAL_COMBO_COUNTER = DATA_KEYS.register("directional_combo_counter", () -> SkillDataKey.createIntKey(0, false, DirectionalBasicAttack.class));
}
