package xyz.oreodev.custommob.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class mainListener implements Listener {
    @EventHandler
    public void onDrop(EntityDeathEvent e) {
        if (!e.getEntity().getCustomName().equalsIgnoreCase(e.getEntity().getName())) return;
        e.getDrops().forEach(ItemStack -> ItemStack.setType(Material.AIR));
    }
}