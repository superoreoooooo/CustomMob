package xyz.oreodev.custommob.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import xyz.oreodev.custommob.CustomMobMain;
import xyz.oreodev.custommob.Entity.Lv1.Boss.LB1_farmer;

import static xyz.oreodev.custommob.Entity.Lv1.Boss.LB1_farmer.farmerUUID;

public class bossBar {
    public int task = -1;
    public int t2 = -1;

    private final CustomMobMain plugin;
    private BossBar bossBar;

    public double bossHealth = 0;

    public bossBar(CustomMobMain plugin) {
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
        bossBar = Bukkit.createBossBar("BOSSNAME", BarColor.RED, BarStyle.SOLID, BarFlag.DARKEN_SKY);
        bossBar.setVisible(true);
    }

    public void initialize() {
        set();
        run();

    }

    public void run() {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (Bukkit.getEntity(farmerUUID) != null) {
                    if (Bukkit.getEntity(farmerUUID) instanceof LivingEntity) {
                        LivingEntity e = (LivingEntity) Bukkit.getEntity(farmerUUID);
                        bossHealth = e.getHealth();
                    } else {
                        bossHealth = 0;
                    }
                }
                bossBar.setProgress(bossHealth / 5);
            }
        }, 0, 20);
    }

    public void set() {
        t2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10)) {
                        if (entity.getType().equals(EntityType.VILLAGER)) {
                            if (entity.getName().contains("FARMER")) {
                                if (!getBossBar().getPlayers().contains(player)) {
                                    addPlayer(player);
                                }
                                else {
                                    if (getBossBar().getPlayers().contains(player)) getBossBar().removePlayer(player);
                                }
                            }
                        }
                    }
                }
            }
        }, 0, 20);
    }
}
