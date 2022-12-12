package xyz.oreodev.custommob.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.oreodev.custommob.util.skill.Skillold;

public class initial implements CommandExecutor {
    private Skillold skill;

    public initial() {
        this.skill = new Skillold();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        skill.initialize();
        return false;
    }
}
