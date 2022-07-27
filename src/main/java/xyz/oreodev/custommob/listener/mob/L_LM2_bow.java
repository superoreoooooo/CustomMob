package xyz.oreodev.custommob.listener.mob;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import xyz.oreodev.custommob.CustomMobMain;

public class L_LM2_bow implements Listener {

    private final CustomMobMain plugin;

    public L_LM2_bow(CustomMobMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        if (e.getEntity().getMetadata("bow").get(0).equals(new FixedMetadataValue(plugin, "bow"))) {
            Bukkit.broadcastMessage("boom");
        }
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if (e.getEntity().getCustomName().contains("BOW")) {
            Arrow arrow = (Arrow) e.getProjectile();
            arrow.setMetadata("bow", new FixedMetadataValue(plugin, "bow"));
            arrow.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, arrow.getLocation(), 5);
            arrow.getLocation().getWorld().playSound(arrow.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
            arrow.setCritical(false);
            arrow.setDamage(25.0D);
        }
    }
}
