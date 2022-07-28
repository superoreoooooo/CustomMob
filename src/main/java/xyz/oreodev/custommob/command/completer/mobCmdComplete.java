package xyz.oreodev.custommob.command.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import xyz.oreodev.custommob.Entity.enums.enumMob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class mobCmdComplete implements TabCompleter {
    List<String> COMMANDS = Stream.of(enumMob.values()).map(Enum::name).collect(Collectors.toList());


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
        Collections.sort(completions);
        return completions;
    }
}
