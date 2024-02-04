package net.pierceth.pierceth_greatsword.skill.passive;



import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;

import java.util.UUID;

public class RoyalGreatswordMastery extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("b8b65fc8-375e-4daa-8742-60eec25d3be0");
    public RoyalGreatswordMastery(Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
    }
    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
}
}
