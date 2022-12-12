package xyz.oreodev.custommob.util.skill;

import org.bukkit.entity.LivingEntity;

import java.util.List;

public interface Skill {
    public abstract int getCooldown();
    public abstract int getEffectTime();
    public abstract int getParticleCnt();
    public abstract int getOffX();
    public abstract int getOffY();
    public abstract int getOffZ();
    public abstract String getMsg();
    public abstract String getMsgColor();
    public abstract List<LivingEntity> getSkillCooldownCasterEntities();
    public abstract List<LivingEntity> getSkillCooldownVictimEntities();
}
