package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Handles events for when the server file system should be envoked and files should be loaded in for players.
 */
public class FileStartupEvents implements Listener {

    // HashMap stores loads and updates data from files : HashMap<Username, MacroDataObject>
    public static HashMap<String, PackageInitializer> playerData = new HashMap<>();
    public static HashMap<String, PlayerStates> playerStates = new HashMap<>();

    /**
     * Load player information in the server memory when a player joins.
     * @param e
     * @throws IOException
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException{
        initPlayerData(e.getPlayer());
    }

    /**
     * Save player information from server back to the server file system when a player leaves.
     * @param e
     * @throws IOException
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) throws IOException {
        PackageInitializer pckg = playerData.get(e.getPlayer().getName());

        // If a player is 'resetting' their data, don't save to avoid error.
        if (playerStates.get(e.getPlayer().getName()).isPlayerReset) { return; }
        pckg.fw.saveToFile(pckg.pInfo);
        pckg.tfw.saveToFile();
        pckg.cw.saveToFile(pckg.cInfo);

        playerData.remove(e.getPlayer().getName()); // Removes current player from the Map
        playerStates.remove(e.getPlayer().getName());
    }

    /**
     * Initialize a player's server data by grabbing data from the player-specific data directory.
     * @param p
     * @throws IOException
     */
    public static void initPlayerData(Player p) throws IOException {
        String username = p.getName();

        // Grab the data directory specific to the player joining.
        PackageInitializer pckgInit = new
                PackageInitializer(Main.mainClassCall.getDataFolder()+ "//playerFiles//" + username, p);

        // Check to see if a player has a levels file.
        try {
            pckgInit.checkForPlayerLevelsFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Check to see what tomes the player has unlocked.
        pckgInit.checkForPlayerTomesFile();

        // Check to make sure class information is present for player.
        pckgInit.checkForClassInit();
        pckgInit.cInfo.applyBuffs(); // Initializes default buffs for a player once they join...

        // Place class information in player data.
        playerData.put(username, pckgInit);

        // Place current player states onto player data (if any were saved upon quitting).
        PlayerStates states = new PlayerStates();
        playerStates.put(username, states);
    }

    /**
     * Grabs computers current system time.
     * TODO: Not sure why this was added??? Maybe remove or add to different util package.
     * @return Formatting system time string.
     */
    public static String getSystemTime() {
        long systemTime = System.currentTimeMillis();

        Date date = new Date(systemTime);
        System.out.println("Test: current time RAW: " + date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = sdf.format(date);
        System.out.println("Test: current time FORMATTED: " + formattedTime);

        return formattedTime;
    }

}
