package xyz.oreodev.custommob.util.skill.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.oreodev.custommob.util.skill.Skill;

public class skillTest implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (Skill.testPlayers.contains(e.getPlayer())) {
            e.getPlayer().sendMessage(ChatColor.AQUA + "TEST!");
        }
    }
}
