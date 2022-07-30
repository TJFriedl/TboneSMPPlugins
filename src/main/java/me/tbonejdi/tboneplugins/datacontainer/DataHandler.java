package me.tbonejdi.tboneplugins.datacontainer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DataHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerStates.playerState.put(e.getPlayer().getName(), new PlayerStates());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PlayerStates.playerState.remove(e.getPlayer().getName());
    }
}
