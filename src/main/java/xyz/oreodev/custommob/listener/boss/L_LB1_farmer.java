package xyz.oreodev.custommob.listener.boss;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class L_LB1_farmer implements Listener {
    @EventHandler
    public void cancelInteractToBoss(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        if (e.getRightClicked().getType() == EntityType.VILLAGER && e.getRightClicked().getCustomName().contains("FARMER")) {
            e.setCancelled(true);
            player.sendMessage("cancelled!");
        }
    }
}
