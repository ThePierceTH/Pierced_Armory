package net.pierceth.pierceth_greatsword.common.init;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.Constants;
import net.pierceth.pierceth_greatsword.compat.efm.skill.weaponinnate.DirectionalInnateSkill;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class VOSSkillDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation(EpicFightMod.MODID, "skill_data_keys"), Constants.MOD_ID);
    public static final RegistryObject<SkillDataKey<Integer>> INNATE_COMBO_COUNTER = DATA_KEYS.register("innate_combo_counter", () -> SkillDataKey.createIntKey(0, false, DirectionalInnateSkill.class));
}