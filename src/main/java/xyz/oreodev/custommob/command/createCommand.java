package xyz.oreodev.custommob.command;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftHumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.oreodev.custommob.mobs.L1_cow;

public class createCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Location loc = player.getLocation();
            L1_cow cow = new L1_cow(loc);
            ((CraftWorld)loc.getWorld()).getHandle().addFreshEntity(cow, CreatureSpawnEvent.SpawnReason.CUSTOM);
        }
        return true;
    }
}
