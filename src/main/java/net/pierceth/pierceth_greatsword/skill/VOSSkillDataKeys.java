package net.pierceth.pierceth_greatsword.skill;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.skill.weaponinnate.DirectionalInnateSkill;
import net.pierceth.pierceth_greatsword.skill.weaponinnate.DragonClawSkill;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class VOSSkillDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation(EpicFightMod.MODID, "skill_data_keys"), EpicFightMod.MODID);
    public static final RegistryObject<SkillDataKey<Integer>> INNATE_COMBO_COUNTER = DATA_KEYS.register("innate_combo_counter", () -> SkillDataKey.createIntKey(0, false, DirectionalInnateSkill.class));
}
