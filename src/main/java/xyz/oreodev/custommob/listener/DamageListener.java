package xyz.oreodev.custommob.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        double damage = e.getDamage();
        Bukkit.broadcastMessage("Total Health of " + entity.getName() + ChatColor.WHITE + " : " + ((LivingEntity) entity).getHealth());
        Bukkit.broadcastMessage(e.getDamager().getName() + " Gained " + damage + " damage to " + entity.getName());
    }
}
