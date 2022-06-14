package xyz.oreodev.custommob;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.custommob.command.bossSummonCmd;
import xyz.oreodev.custommob.command.mobSummonCmd;


public final class CustomMobMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "ON");
        getCommand("mob").setExecutor(new mobSummonCmd());
        getCommand("boss").setExecutor(new bossSummonCmd());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "OFF");
    }
}
