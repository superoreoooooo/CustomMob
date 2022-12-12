package xyz.oreodev.custommob.util.skill;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.custommob.CustomMobMain;
import xyz.oreodev.custommob.util.skill.enums.skillEntity;
import xyz.oreodev.custommob.util.skill.enums.skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Skillold {
    private final CustomMobMain plugin = JavaPlugin.getPlugin(CustomMobMain.class);
    public static HashMap<LivingEntity, skillEntity> skillCasters = new HashMap<>();

    //테스트
    public static List<LivingEntity> testCooldownCasters = new ArrayList<>();
    public static List<Player> testPlayers = new ArrayList<>();

    //바인드
    public static List<LivingEntity> bindCooldownCasters = new ArrayList<>();
    public static List<Player> bindPlayers = new ArrayList<>();

    public static boolean SkillApiStatus = false;

    public void addCasters() {
        String worldName = plugin.getWorldName();
        for (LivingEntity livingEntity : Bukkit.getWorld(worldName).getLivingEntities()) {
            for (skillEntity skillEntity : skillEntity.values()) {
                if (livingEntity.getCustomName() != null)
                if (livingEntity.getCustomName().contains(skillEntity.toString())) {
                    skillCasters.put(livingEntity, skillEntity);
                }
            }
        }
    }

    public void initialize() {
        if (SkillApiStatus) return;
        addCasters();
        SkillApiStatus = true;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (LivingEntity livingEntity : skillCasters.keySet()) {
                execute(livingEntity, skillCasters.get(livingEntity).getSkill());
            }
        },0, 2);
    }

    public void execute(LivingEntity livingEntity, skills skill) {
        if (!checkCasterStatus(livingEntity)) return;
        for (Entity entity : livingEntity.getWorld().getNearbyEntities(livingEntity.getLocation(), 10, 10, 10)) {
            if (entity instanceof Player player) {
                if (!checkPlayerStatus(player)) return;
                Location targetLoc = player.getLocation();
                player.getWorld().spawnParticle(skill.getParticle(), targetLoc, skill.getCount(), skill.getOffX(), skill.getOffY(), skill.getOffZ());
                player.sendTitle(skill.getMsg(), "");
                doEffect(player, livingEntity, skill);
            }
        }
    }

    public void doEffect(Player player, LivingEntity livingEntity, skills skill) {
        switch (skill) {
            case TEST -> {
                testPlayers.add(player);
                testCooldownCasters.add(livingEntity);
                Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {testPlayers.remove(player);}, 200);
                Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {testCooldownCasters.remove(livingEntity);}, 200);
            }
            case SPIRIT_BIND -> {
                bindPlayers.add(player);
                bindCooldownCasters.add(livingEntity);
                Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {bindPlayers.remove(player);}, 20);
                Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {bindCooldownCasters.remove(livingEntity);}, 200);
            }
        }
        Bukkit.getConsoleSender().sendMessage("Entity " + livingEntity.getCustomName() + ChatColor.WHITE + " (UUID : " + livingEntity.getUniqueId() + ") casted Skill " + skill + "to Player " + ChatColor.GREEN + player.getName());
    }

    public boolean checkPlayerStatus(Player player) { //true : skill castable / false : on skill effect
        if (testPlayers.contains(player)) return false;
        if (bindPlayers.contains(player)) return false;
        return true;
    }

    public boolean checkCasterStatus(LivingEntity entity) { //앞으로 스킬 만들때마다 쿨다운 추가
        if (testCooldownCasters.contains(entity)) return false;
        if (bindCooldownCasters.contains(entity)) return false;
        return true;
    }
}
