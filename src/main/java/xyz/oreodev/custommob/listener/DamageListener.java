package xyz.oreodev.custommob.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        double damage = e.getDamage();
        Bukkit.broadcastMessage(e.getDamager().getName() + " Gained " + damage + " damage to " + entity.getName());
    }
}
