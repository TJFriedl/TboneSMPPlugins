package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.testingcases.Counter;
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
    public static PackageInitializer pckg;
    public Counter count;

    public static boolean playerReset; // We use this so console doesn't throw errors for resetting player data

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException{
        Player p = e.getPlayer();
        String username = p.getName();

        PackageInitializer pckgInit = new PackageInitializer("//home//container//plugins//playerFiles//" + username, p);

        // Init for fileWorker, playerInfo
        pckgInit.checkForPlayerLevelsFile();
        this.fw = pckgInit.fw;
        this.pInfo = pckgInit.pInfo;

        // Init for tomesFileworker
        pckgInit.checkForPlayerTomesFile();
        this.tfw = pckgInit.tfw;

        // Init for classWorker, classInfo
        pckgInit.checkForClassInit();
        this.cw = pckgInit.cw;
        this.cInfo = pckgInit.cInfo;
        cInfo.applyBuffs(); // Initializes default buffs for a player once they join...

        playerReset = false;
        pckg = pckgInit;

        // THIS IS ALL FOR A TEST... MEANT TO BE DELETED LATER ON
        File f = new File("//home//container//plugins//playerFiles//" + username + "//"
                + username.toLowerCase() + "_count.txt");
        if (f.exists()) {
            Counter count = new Counter(f);
            count.init();
            this.count = count;
            Counter.count = 0;
        }
        else {
            Counter count = new Counter(f);
            this.count = count;
            Counter.count = this.count.parseCount();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) throws IOException {
        if (playerReset) { return; }
        fw.saveToFile(pInfo);
        tfw.saveToFile();
        cw.saveToFile(cInfo);
        this.count.save();
    }

}
