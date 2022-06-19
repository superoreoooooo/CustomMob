package xyz.oreodev.custommob;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.custommob.command.bossSummonCmd;
import xyz.oreodev.custommob.command.mobSummonCmd;
import xyz.oreodev.custommob.listener.DamageListener;
import xyz.oreodev.custommob.listener.boss.L_LB1_farmer;
import xyz.oreodev.custommob.util.bossBar;


public final class CustomMobMain extends JavaPlugin {

    public bossBar bossBar;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "ON");
        getCommand("mob").setExecutor(new mobSummonCmd());
        getCommand("boss").setExecutor(new bossSummonCmd());
        Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new L_LB1_farmer(), this);

        bossBar = new bossBar(this);
        bossBar.createBossBar();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "OFF");
    }
}
