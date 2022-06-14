package xyz.oreodev.custommob.util;

import net.minecraft.world.entity.Entity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.Entity.Lv1.Mobs.*;
import xyz.oreodev.custommob.Entity.Lv1.Mobs.LM2_wolf;

public class mobSpawner {
    public void summonMob(CommandSender sender, String mobName, Location location) {
        if (sender instanceof Player player) {
            if (location == null) {
                player.sendMessage(ChatColor.RED + "ERROR_SPAWNING_NULL_LOC");
                return;
            }
            switch (mobName) {
                case "LM1_COW":
                    LM1_cow cow = new LM1_cow(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(cow, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM1_PIG":
                    LM1_pig pig = new LM1_pig(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(pig, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM1_SHEEP":
                    LM1_sheep sheep = new LM1_sheep(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(sheep, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM1_CHICKEN":
                    LM1_chicken chicken = new LM1_chicken(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(chicken, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM2_WOLF":
                    LM2_wolf wolf = new LM2_wolf(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(wolf, CreatureSpawnEvent.SpawnReason.CUSTOM);
                default:
                    sender.sendMessage(ChatColor.RED + "ERROR_SPAWNING_MOB");
            }
        }
    }
}
