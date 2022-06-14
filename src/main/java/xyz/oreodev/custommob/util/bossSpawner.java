package xyz.oreodev.custommob.util;

import net.minecraft.world.entity.Entity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.Entity.Lv1.Boss.*;
import xyz.oreodev.custommob.Entity.Lv1.Boss.LB1_farmer;

public class bossSpawner {
    public void summonMob(CommandSender sender, String mobName, Location location) {
        if (sender instanceof Player player) {
            if (location == null) {
                player.sendMessage(ChatColor.RED + "ERROR_SPAWNING_NULL_LOC");
                return;
            }
            switch (mobName) {
                case "LB1_FARMER":
                    LB1_farmer farmer = new LB1_farmer(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(farmer, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    player.sendMessage(ChatColor.RED + "BOSS SUMMONED!");
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "ERROR_SPAWNING_BOSS");
            }
        }
    }
}
