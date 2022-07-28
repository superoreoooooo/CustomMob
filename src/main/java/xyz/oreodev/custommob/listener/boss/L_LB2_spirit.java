package xyz.oreodev.custommob.listener.boss;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.oreodev.custommob.CustomMobMain;
import xyz.oreodev.custommob.Entity.Lv2.Boss.LB2_spirit;

public class L_LB2_spirit implements Listener {

    BukkitScheduler restraintSchedule = Bukkit.getScheduler();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!checkRestraintDelay(e.getPlayer())) return;
        e.setCancelled(true);
        for (Player player : LB2_spirit.restraint) {
            e.getPlayer().sendMessage(player.getName());
        }
    }

    public boolean checkRestraintDelay(Player player) {
        if (LB2_spirit.restraint.contains(player)) return true;
        else {
            restraintSchedule.runTaskLaterAsynchronously(JavaPlugin.getPlugin(CustomMobMain.class), () -> LB2_spirit.restraint.remove(player), 20);
            return false;
        }
    }
}
