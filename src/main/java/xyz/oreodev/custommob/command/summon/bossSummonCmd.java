package xyz.oreodev.custommob.command.summon;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.oreodev.custommob.Entity.enums.enumBoss;
import xyz.oreodev.custommob.util.spawner.bossSpawner;

public class bossSummonCmd implements CommandExecutor {

    bossSpawner spawner = new bossSpawner();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) spawner.summonMob(sender, enumBoss.TEST, player.getLocation());
            if (args.length == 1) {
                for (enumBoss mob : enumBoss.values()) {
                    if (mob.toString().equalsIgnoreCase(args[0])) spawner.summonMob(sender, mob, player.getLocation());
                }
            }
            if (args.length >= 4) {
                for (enumBoss mob : enumBoss.values()) {
                    if (mob.toString().equalsIgnoreCase(args[0])) spawner.summonMob(sender, mob, new Location(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]),Double.parseDouble(args[3])));
                }
            }
        }
        return true;
    }
}
