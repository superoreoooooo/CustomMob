package xyz.oreodev.custommob.util.bossBar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import xyz.oreodev.custommob.CustomMobMain;
import xyz.oreodev.custommob.Entity.enums.enumBoss;

import static xyz.oreodev.custommob.Entity.Lv1.Boss.LB1_farmer.farmerUUID;

public class farmerBossBar {
    public int task = -1;
    public int t2 = -1;

    private final CustomMobMain plugin;
    private BossBar bossBar;

    public double bossHealth = 0;

    public farmerBossBar(CustomMobMain plugin) {
        this.plugin = plugin;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void setBossBar(BossBar bossBar) {
        this.bossBar = bossBar;
    }

    public void addPlayer(Player player) {
        bossBar.addPlayer(player);
    }

    public void createBossBar() {
        bossBar = Bukkit.createBossBar(ChatColor.RED + "FARMER", BarColor.RED, BarStyle.SOLID, BarFlag.DARKEN_SKY);
        bossBar.setVisible(true);
    }

    public void initialize() {
        setBossBarVisible();
        setBossBarAsBossHealth();
    }

    public void setBossBarAsBossHealth() {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (farmerUUID != null) {
                    if (Bukkit.getEntity(farmerUUID) != null) {
                        if (Bukkit.getEntity(farmerUUID) instanceof LivingEntity) {
                            LivingEntity e = (LivingEntity) Bukkit.getEntity(farmerUUID);
                            bossHealth = e.getHealth();
                            //Bukkit.getServer().broadcastMessage("Health : " + bossHealth);
                        } else {
                            bossHealth = 0;
                        }
                    }
                    bossBar.setProgress(bossHealth / 500);
                }
            }
        }, 0, 20);
    }

    public void setBossBarVisible() {
        t2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10)) {
                        if (entity.getType().equals(EntityType.VILLAGER)) {
                            //player.sendMessage(entity.getName());
                            if (entity.getName().contains(enumBoss.FARMER.toString())) {
                                if (!getBossBar().getPlayers().contains(player)) {
                                    addPlayer(player);
                                }
                                player.sendMessage("!");
                            }
                            else {
                                if (getBossBar().getPlayers().contains(player)) getBossBar().removePlayer(player);
                                player.sendMessage("?");
                            }
                        }
                    }
                }
            }
        }, 0, 20);
    }
}
