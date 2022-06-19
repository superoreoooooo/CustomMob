package xyz.oreodev.custommob.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.Entity.Lv1.Boss.*;
import xyz.oreodev.custommob.Entity.Lv1.Boss.LB1_farmer;
import xyz.oreodev.custommob.Entity.Lv1.Boss.TestBoss;

public class bossSpawner {
    public void summonMob(CommandSender sender, String mobName, Location location) {
        if (sender instanceof Player player) {
            if (location == null) {
                player.sendMessage(ChatColor.RED + "ERROR_SPAWNING_NULL_LOC");
                return;
            }
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer serverPlayer = craftPlayer.getHandle();
            MinecraftServer server = serverPlayer.getServer();
            ServerLevel serverLevel = serverPlayer.getLevel();


            switch (mobName) {
                case "test":
                    TestBoss boss = new TestBoss(location);
                    boss.setVillagerData(new VillagerData(VillagerType.PLAINS, VillagerProfession.FARMER, 1));
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(boss, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case "LB1_FARMER": case "farmer":
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
