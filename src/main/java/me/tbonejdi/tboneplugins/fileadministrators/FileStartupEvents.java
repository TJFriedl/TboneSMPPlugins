package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;

public class FileStartupEvents implements Listener {
    public static PlayerInfo pInfo; // Will try to grab information from the onEnable() call
    public static PlayerFileWorker fw; // Same as above, it should instantiate from onEnable()
    public static TomesFileWorker tfw;
    public static ClassWorker cw;
    public static ClassInfo cInfo;

    public static boolean playerReset; // We use this so console doesn't throw errors for resetting player data

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException{
        Player p = e.getPlayer();
        String username = p.getName();

        PackageInitializer pckgInit = new PackageInitializer("//home//container//plugins//playerFiles//" + username, p);

        pckgInit.checkForPlayerLevelsFile(); // Does the player file exist?
        this.fw = pckgInit.fw;
        this.pInfo = pckgInit.pInfo;

        pckgInit.checkForPlayerTomesFile();
        this.tfw = pckgInit.tfw;

        pckgInit.checkForClassInit();
        this.cw = pckgInit.cw;
        this.cInfo = pckgInit.cInfo;

        playerReset = false;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) throws IOException {
        if (playerReset) { return; }
        fw.saveToFile(pInfo);
        tfw.saveToFile();
        cw.saveToFile(cInfo);
    }

}
