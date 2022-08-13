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
import java.util.List;

public class Skill {

    private final JavaPlugin plugin = JavaPlugin.getPlugin(CustomMobMain.class);
    public static List<LivingEntity> skillCasters = new ArrayList<>();

    //바인드
    public static List<LivingEntity> bindCooldownCasters = new ArrayList<>();
    public static List<Player> bindPlayers = new ArrayList<>();

    public void addCasters() {
        String worldName = plugin.getConfig().getString("settings.world");
        for (LivingEntity livingEntity : Bukkit.getWorld(worldName).getLivingEntities()) {
            for (skillEntity entity : skillEntity.values()) {
                if (livingEntity.getCustomName() != null)
                if (livingEntity.getCustomName().contains(entity.toString())) {
                    skillCasters.add(livingEntity);
                }
            }
        }
    }

    public void initialize() {
        addCasters();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (LivingEntity livingEntity : skillCasters) {
                    execute(livingEntity, skills.SPIRIT_BIND);
                }
            }
        },0, 5);
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
            case SPIRIT_BIND:
                bindPlayers.add(player);
                bindCooldownCasters.add(livingEntity);
                Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {bindCooldownCasters.remove(livingEntity);}, 200);
                Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {bindPlayers.remove(player);}, 20);
                Bukkit.getConsoleSender().sendMessage("Entity " + livingEntity.getCustomName() + ChatColor.WHITE + " (UUID : " + livingEntity.getUniqueId() + ") casted Skill to Player " + ChatColor.GREEN + player.getName());
                break;
        }
    }

    public boolean checkPlayerStatus(Player player) { //true : skill castable / false : on skill effect
        if (bindPlayers.contains(player)) return false;
        return true;
    }

    public boolean checkCasterStatus(LivingEntity entity) { //앞으로 스킬 만들때마다 쿨다운 추가
        if (bindCooldownCasters.contains(entity)) return false;
        return true;
    }
}
