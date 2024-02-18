package net.pierceth.pierceth_greatsword.skill.passive;



import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;

import java.util.UUID;

public class HoundGreatsword extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("5cc03144-24b0-4d57-a49b-28a6989db9e5");
    public HoundGreatsword(Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        //super.onInitiate(container);
    }
    @Override
    public void onRemoved(SkillContainer container) {
        //super.onRemoved(container);
}
}
