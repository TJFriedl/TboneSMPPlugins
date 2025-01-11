package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * Represents the object for the server's class information manager. Allows for mechanisms such as reloading a player
 * configuration, saving, returning, and updating.
 */
public class ClassInfoManager {

    private Main main;
    private FileConfiguration config = null;
    private File configFile = null;
    private Player player;
    public ClassInfoManager(Main main, Player player) {
        this.main = main;
        this.player = player;
        saveDefaultConfig();
        saveConfig();
    }

    /**
     * Refreshes a player's configuration to the server file system, reflective of the server's current data.
     */
    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.main.getDataFolder() + "//playerfiles//" + player.getUniqueId(), "classdata.yml");

        this.config = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.main.getResource("classdata.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        } else {
            player.sendMessage("Error loading classdata.yml file");
        }
    }

    /**
     * Grabs server configuration.
     *
     * @return server configuration.
     */
    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();

        return this.config;
    }

    /**
     * Saves player's configuration data to the server filesystem.
     */
    public void saveConfig() {
        if (this.config == null || this.configFile == null) return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    /**
     * Saves player's configuration file if one has not been created already.
     */
    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.main.getDataFolder() + "//playerfiles//" + player.getUniqueId(), "classdata.yml");

        if (!this.configFile.exists()) {

            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
