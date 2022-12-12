package xyz.oreodev.custommob.util.skill.skills;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.custommob.CustomMobMain;
import xyz.oreodev.custommob.util.skill.Skill;

import java.util.List;

public class bind implements Skill {
    private final CustomMobMain plugin = JavaPlugin.getPlugin(CustomMobMain.class);

    private final String configLoc = "skills.bind.";
    public static List<LivingEntity> Bind_SkillCooldownCasterEntities;
    public static List<LivingEntity> Bind_SkillCooldownVictimEntities;

    @Override
    public int getCooldown() {
        return plugin.getConfig().getInt(configLoc + "cooldown");
    }

    @Override
    public int getEffectTime() {
        return plugin.getConfig().getInt(configLoc + "effecttime");
    }

    @Override
    public int getParticleCnt() {
        return plugin.getConfig().getInt(configLoc + "particlecnt");
    }

    @Override
    public int getOffX() {
        return plugin.getConfig().getInt(configLoc + "offX");
    }

    @Override
    public int getOffY() {
        return plugin.getConfig().getInt(configLoc + "offY");
    }

    @Override
    public int getOffZ() {
        return plugin.getConfig().getInt(configLoc + "offZ");
    }

    @Override
    public String getMsg() {
        return plugin.getConfig().getString(configLoc + "msg");
    }

    @Override
    public String getMsgColor() {
        return plugin.getConfig().getString(configLoc + "msgcolor");
    }

    @Override
    public List<LivingEntity> getSkillCooldownCasterEntities() {
        return Bind_SkillCooldownCasterEntities;
    }

    @Override
    public List<LivingEntity> getSkillCooldownVictimEntities() {
        return Bind_SkillCooldownVictimEntities;
    }
}
