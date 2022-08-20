package xyz.oreodev.custommob.util.skill.enums;

public enum skillEntity {
    TEST(skills.TEST),
    SPIRIT(skills.SPIRIT_BIND);


    private final skills skill;

    skillEntity(skills skill) {
        this.skill = skill;
    }

    public skills getSkill() {
        return skill;
    }
}
