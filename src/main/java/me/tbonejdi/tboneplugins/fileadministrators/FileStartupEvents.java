package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;
import java.util.HashMap;

public class FileStartupEvents implements Listener {
//    public static PlayerInfo pInfo; // Will try to grab information from the onEnable() call
//    public static PlayerFileWorker fw; // Same as above, it should instantiate from onEnable()
//    public static TomesFileWorker tfw;
//    public static ClassWorker cw;
//    public static ClassInfo cInfo;
//    public static PackageInitializer pckg;

    // HashMap stores loads and updates data from files : HashMap<Username, MacroDataObject>
    public static HashMap<String, PackageInitializer> playerData = new HashMap<>(); // This might be epic

    public static boolean playerReset; // We use this so console doesn't throw errors for resetting player data

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException{
        Player p = e.getPlayer();
        String username = p.getName();

        PackageInitializer pckgInit = new PackageInitializer("//home//container//plugins//playerFiles//" + username, p);

        // Init for fileWorker, playerInfo
        pckgInit.checkForPlayerLevelsFile();
//        this.fw = pckgInit.fw;
//        this.pInfo = pckgInit.pInfo;

        // Init for tomesFileworker
        pckgInit.checkForPlayerTomesFile();
//        this.tfw = pckgInit.tfw;

        // Init for classWorker, classInfo
        pckgInit.checkForClassInit();
//        this.cw = pckgInit.cw;
//        this.cInfo = pckgInit.cInfo;
        pckgInit.cInfo.applyBuffs(); // Initializes default buffs for a player once they join...

        playerReset = false;
//        pckg = pckgInit;

        playerData.put(username, pckgInit);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) throws IOException {
        PackageInitializer pckg = playerData.get(e.getPlayer().getName());

        if (playerReset) { return; }
        pckg.fw.saveToFile(pckg.pInfo);
        pckg.tfw.saveToFile();
        pckg.cw.saveToFile(pckg.cInfo);

        playerData.remove(e.getPlayer().getName()); // Removes current player from the Map
    }

}
