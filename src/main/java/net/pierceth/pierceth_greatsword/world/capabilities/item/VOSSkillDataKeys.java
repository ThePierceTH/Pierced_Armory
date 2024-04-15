package net.pierceth.pierceth_greatsword.world.capabilities.item;

import net.minecraftforge.registries.RegistryObject;
import net.pierceth.pierceth_greatsword.skill.DirectionalBasicAttack;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.SkillDataKeys;

public class VOSSkillDataKeys {
    public static final RegistryObject<SkillDataKey<Integer>> DIRECTIONAL_COMBO_COUNTER = SkillDataKeys.DATA_KEYS.register("directional_combo_counter", () -> SkillDataKey.createIntKey(0, false, DirectionalBasicAttack.class));
}
