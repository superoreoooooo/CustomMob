package xyz.oreodev.custommob;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.oreodev.custommob.command.completer.mobCmdComplete;
import xyz.oreodev.custommob.command.summon.bossSummonCmd;
import xyz.oreodev.custommob.command.completer.bossCmdComplete;
import xyz.oreodev.custommob.command.summon.mobSummonCmd;
import xyz.oreodev.custommob.listener.DamageListener;
import xyz.oreodev.custommob.listener.boss.L_LB1_farmer;
import xyz.oreodev.custommob.listener.boss.L_LB2_spirit;
import xyz.oreodev.custommob.listener.mainListener;
import xyz.oreodev.custommob.listener.mob.L_LM2_bow;
import xyz.oreodev.custommob.util.skill.Skill;
import xyz.oreodev.custommob.util.skill.listener.skillBind;

import java.io.File;


public final class CustomMobMain extends JavaPlugin {

    public final String bar = "====================================================";
    public final String prefix = "[CustomMob] ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + prefix + bar);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + prefix + "ON");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + prefix + bar);

        getCommand("mob").setExecutor(new mobSummonCmd());
        getCommand("mob").setTabCompleter(new mobCmdComplete());
        getCommand("boss").setExecutor(new bossSummonCmd());
        getCommand("boss").setTabCompleter(new bossCmdComplete());

        Bukkit.getServer().getPluginManager().registerEvents(new mainListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new L_LB1_farmer(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new L_LM2_bow(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new L_LB2_spirit(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new skillBind(), this);
    }

    @Override
    public void onDisable() {
    }
}
