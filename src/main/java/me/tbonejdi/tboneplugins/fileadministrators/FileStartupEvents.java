package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;
import java.util.HashMap;

public class FileStartupEvents implements Listener {

    // HashMap stores loads and updates data from files : HashMap<Username, MacroDataObject>
    public static HashMap<String, PackageInitializer> playerData = new HashMap<>();
    public static HashMap<String, PlayerStates> playerStates = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException{
        Player p = e.getPlayer();
        String username = p.getName();

        PackageInitializer pckgInit = new
                PackageInitializer("//home//container//plugins//playerFiles//" + username, p);

        pckgInit.checkForPlayerLevelsFile();

        pckgInit.checkForPlayerTomesFile();

        pckgInit.checkForClassInit();
        pckgInit.cInfo.applyBuffs(); // Initializes default buffs for a player once they join...

        playerData.put(username, pckgInit);

        PlayerStates states = new PlayerStates();
        playerStates.put(username, states);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) throws IOException {
        PackageInitializer pckg = playerData.get(e.getPlayer().getName());

        if (playerStates.get(e.getPlayer().getName()).isPlayerReset) { return; }
        pckg.fw.saveToFile(pckg.pInfo);
        pckg.tfw.saveToFile();
        pckg.cw.saveToFile(pckg.cInfo);

        playerData.remove(e.getPlayer().getName()); // Removes current player from the Map
        playerStates.remove(e.getPlayer().getName());
    }

}
