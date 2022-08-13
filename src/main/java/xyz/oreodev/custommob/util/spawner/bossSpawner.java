package xyz.oreodev.custommob.util.spawner;

import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.Entity.Lv1.Boss.LB1_farmer;
import xyz.oreodev.custommob.Entity.Lv2.Boss.LB2_spirit;
import xyz.oreodev.custommob.Entity.enums.enumBoss;
import xyz.oreodev.custommob.Entity.test.TestBoss;
import xyz.oreodev.custommob.util.skill.Skill;

public class bossSpawner {
    private Skill skill;

    public bossSpawner() {
        this.skill = new Skill();
    }

    public void summonMob(CommandSender sender, enumBoss bossName, Location location) {
        if (sender instanceof Player player) {
            if (location == null) {
                player.sendMessage(ChatColor.RED + "ERROR_SPAWNING_NULL_LOC");
                return;
            }
            switch (bossName) {
                case TEST:
                    TestBoss boss = new TestBoss(location);
                    boss.setVillagerData(new VillagerData(VillagerType.PLAINS, VillagerProfession.FARMER, 1));
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(boss, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    break;
                case FARMER:
                    LB1_farmer farmer = new LB1_farmer(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(farmer, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    player.sendMessage(ChatColor.RED + "BOSS_SUMMONED");
                    break;
                case SPIRIT:
                    LB2_spirit spirit = new LB2_spirit(location);
                    ((CraftWorld)location.getWorld()).getHandle().addFreshEntity(spirit, CreatureSpawnEvent.SpawnReason.CUSTOM);
                    player.sendMessage(ChatColor.BLUE + "BOSS_SUMMONED");
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "ERROR_SPAWNING_BOSS");
            }
            skill.initialize();
        }
    }
}
