package xyz.oreodev.custommob.util.skill;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.custommob.CustomMobMain;
import xyz.oreodev.custommob.util.skill.skills.bind;

import java.lang.reflect.Field;
import java.util.List;

public class SkillExecuter {
    private final CustomMobMain plugin = JavaPlugin.getPlugin(CustomMobMain.class);
    private final String worldName = plugin.getWorldName();

    public static List<LivingEntity> skillCasters;

    public void addCasters() {
        for (LivingEntity livingEntity : Bukkit.getWorld(worldName).getLivingEntities()) {
            if (livingEntity.hasMetadata("skill")) {
                switch(livingEntity.getMetadata("skill").get(0).asString()) {
                    case "test":
                        break;
                    case "bind":
                        if (!skillCasters.contains(livingEntity)) skillCasters.add(livingEntity);
                        break;
                }
            }
        }
    }

    public void initialize() {
        
    }
}
