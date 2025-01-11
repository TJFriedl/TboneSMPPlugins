package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * Manages player's general server information. Works similarly to the ClassInfoManager, but instead holds data
 * for generic usages, such as general experience, level, perks, etc.
 */
public class DataManager {

    private Main main;
    private FileConfiguration config = null;
    private File configFile = null;

    /**
     * Construct for data manager. Uses instance of main.
     * @param main
     */
    public DataManager(Main main) {
        this.main = main;
        saveDefaultConfig();
    }

    /**
     * Reloads player's data configuration to server.
     */
    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.main.getDataFolder(), "data.yml");

        this.config = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.main.getResource("data.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        }
    }

    /**
     * Grabs player's current data configuration.
     * @return Player data configuration.
     */
    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();

        return this.config;
    }

    /**
     * Saves player's data configuration back to the server.
     */
    public void saveConfig() {
        // Return if no config file yet exists.
        if (this.config == null || this.configFile == null) return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    /**
     * Save default configuration file back to server file system.
     */
    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.main.getDataFolder(), "data.yml");
        }

        if (!this.configFile.exists()) {

            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
