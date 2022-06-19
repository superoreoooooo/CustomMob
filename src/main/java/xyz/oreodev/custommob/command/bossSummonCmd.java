package xyz.oreodev.custommob.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.Entity.Lv1.Mobs.LM1_cow;
import xyz.oreodev.custommob.util.bossSpawner;

public class bossSummonCmd implements CommandExecutor {

    bossSpawner spawner = new bossSpawner();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) spawner.summonMob(player, "test", player.getLocation());
            if (args.length >= 4) {
                player.sendMessage(args[0] + args[1] + args[2] + args[3]);
                spawner.summonMob(player, args[0], new Location(player.getWorld(), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
            }
        }
        return true;
    }
}
