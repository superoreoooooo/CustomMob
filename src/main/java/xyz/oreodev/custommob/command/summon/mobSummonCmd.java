package xyz.oreodev.custommob.command.summon;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.oreodev.custommob.Entity.enums.enumMob;
import xyz.oreodev.custommob.util.spawner.mobSpawner;

public class mobSummonCmd implements CommandExecutor {

    mobSpawner spawner = new mobSpawner();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) spawner.summonMob(sender, enumMob.TEST, player.getLocation());
            if (args.length == 1) {
                for (enumMob mob : enumMob.values()) {
                    if (mob.toString().equalsIgnoreCase(args[0])) spawner.summonMob(sender, mob, player.getLocation());
                }
            }
            if (args.length >= 4) {
                for (enumMob mob : enumMob.values()) {
                    if (mob.toString().equalsIgnoreCase(args[0])) spawner.summonMob(sender, mob, new Location(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]),Double.parseDouble(args[3])));
                }
            }
        }
        return true;
    }
}
