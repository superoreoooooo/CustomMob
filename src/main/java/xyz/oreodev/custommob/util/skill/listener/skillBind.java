package xyz.oreodev.custommob.util.skill.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.oreodev.custommob.util.skill.Skill;

public class skillBind implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (Skill.bindPlayers.contains(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.BLUE + "BINDED!");
        }
    }
}
