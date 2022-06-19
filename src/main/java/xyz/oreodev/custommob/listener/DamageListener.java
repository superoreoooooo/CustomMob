package xyz.oreodev.custommob.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!e.getDamager().getType().equals(EntityType.PLAYER)) return;
        Player player = (Player) e.getDamager();
        Entity entity = e.getEntity();
        double damage = e.getDamage();
        player.sendMessage("Gained " + damage + " damage to " + entity.getName());
    }
}
