package xyz.oreodev.custommob.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.oreodev.custommob.util.skill.Skill;

public class initial implements CommandExecutor {
    private Skill skill;

    public initial() {
        this.skill = new Skill();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        skill.initialize();
        return false;
    }
}
