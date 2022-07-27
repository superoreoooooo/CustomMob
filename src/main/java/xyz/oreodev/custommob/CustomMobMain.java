package xyz.oreodev.custommob;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.custommob.command.complete.mobCmdComplete;
import xyz.oreodev.custommob.command.summon.bossSummonCmd;
import xyz.oreodev.custommob.command.complete.bossCmdComplete;
import xyz.oreodev.custommob.command.summon.mobSummonCmd;
import xyz.oreodev.custommob.listener.DamageListener;
import xyz.oreodev.custommob.listener.boss.L_LB1_farmer;
import xyz.oreodev.custommob.listener.mob.L_LM2_bow;


public final class CustomMobMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "ON");
        getCommand("mob").setExecutor(new mobSummonCmd());
        getCommand("mob").setTabCompleter(new mobCmdComplete());
        getCommand("boss").setExecutor(new bossSummonCmd());
        getCommand("boss").setTabCompleter(new bossCmdComplete());
        Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new L_LB1_farmer(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new L_LM2_bow(this), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "OFF");
    }
}
