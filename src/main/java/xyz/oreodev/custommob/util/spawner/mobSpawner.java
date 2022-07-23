package xyz.oreodev.custommob.util.spawner;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.Entity.Lv1.Mobs.*;
import xyz.oreodev.custommob.Entity.Lv2.Mobs.*;
import xyz.oreodev.custommob.Entity.enums.enumMob;
import xyz.oreodev.custommob.Entity.test.TestMob;

public class mobSpawner {
    public void summonMob(CommandSender sender, enumMob mob, Location location) {
        if (sender instanceof Player player) {
            if (location == null) {
                player.sendMessage(ChatColor.RED + "ERROR_SPAWNING_LOC_NULL");
                location = player.getLocation();
            }
            switch (mob) {
                case TEST:
                    TestMob testMob = new TestMob(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(testMob, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case COW:
                    LM1_cow cow = new LM1_cow(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(cow, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case PIG:
                    LM1_pig pig = new LM1_pig(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(pig, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case LLAMA:
                    LM1_llama sheep = new LM1_llama(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(sheep, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case CHICKEN:
                    LM1_chicken chicken = new LM1_chicken(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(chicken, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case WOLF:
                    LM2_wolf wolf = new LM2_wolf(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(wolf, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case BOW:
                    LM2_bow bow = new LM2_bow(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(bow, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "ERROR_SPAWNING_MOB");
            }
        }
    }
}
