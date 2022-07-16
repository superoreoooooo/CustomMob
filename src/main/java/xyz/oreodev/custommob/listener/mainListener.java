package xyz.oreodev.custommob.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import xyz.oreodev.custommob.util.bossBar.farmerBossBar;

public class mainListener implements Listener {
    private farmerBossBar farmerBossBar;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("HELLO");
        if (!farmerBossBar.getBossBar().getPlayers().contains(e.getPlayer())) farmerBossBar.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onRevive(PlayerRespawnEvent e) {
        e.getPlayer().sendMessage("WHOA!");
        if (!farmerBossBar.getBossBar().getPlayers().contains(e.getPlayer())) farmerBossBar.addPlayer(e.getPlayer());
    }
}