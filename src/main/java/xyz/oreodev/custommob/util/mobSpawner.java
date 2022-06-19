package xyz.oreodev.custommob.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.Entity.Lv1.Mobs.*;
import xyz.oreodev.custommob.Entity.Lv1.Mobs.LM2_wolf;
import xyz.oreodev.custommob.Entity.test.TestMob;

public class mobSpawner {
    public void summonMob(CommandSender sender, String mobName, Location location) {
        if (sender instanceof Player player) {
            if (location == null) {
                player.sendMessage(ChatColor.RED + "ERROR_SPAWNING_LOC_NULL");
                location = player.getLocation();
            }
            switch (mobName) {
                case "test":
                    TestMob testMob = new TestMob(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(testMob, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM1_COW": case "cow":
                    LM1_cow cow = new LM1_cow(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(cow, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM1_PIG": case "pig":
                    LM1_pig pig = new LM1_pig(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(pig, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM1_LLAMA": case "llama":
                    LM1_llama sheep = new LM1_llama(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(sheep, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM1_CHICKEN": case "chicken":
                    LM1_chicken chicken = new LM1_chicken(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(chicken, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LM2_WOLF": case "wolf":
                    LM2_wolf wolf = new LM2_wolf(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(wolf, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "ERROR_SPAWNING_MOB");
            }
        }
    }
}
