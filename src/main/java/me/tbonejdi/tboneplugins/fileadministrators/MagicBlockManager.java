package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;

public class MagicBlockManager {

    private Main main;
    private FileConfiguration config = null;
    private File configFile = null;
    public static ArrayList<Location> tables;

    public MagicBlockManager(Main main) {
        this.main = main;
        saveDefaultConfig();
        saveConfig();
        tables = new ArrayList<>();
        populateHashSet();
    }

    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.main.getDataFolder(), "magicblocks.yml");

        this.config = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.main.getResource("magicblocks.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();

        return this.config;
    }

    public void saveConfig() {
        if (this.config == null || this.configFile == null) return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.main.getDataFolder(), "magicblocks.yml");

        if (!this.configFile.exists()) {

            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void populateHashSet() {

        tables = (ArrayList<Location>) getConfig().get("magictables");

        if (tables == null) return;

        for (Location location : tables) {
            updateTableData(location);
            createTextEntity(location);
        }

    }

    public static void createTextEntity(Location location) {
        Location updatedLoc = new Location(location.getWorld(), location.getBlockX() + 0.5, location.getBlockY() - 0.75,
                location.getBlockZ() + 0.5 );
        ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(updatedLoc, EntityType.ARMOR_STAND);
        stand.setVisible(false);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setCustomNameVisible(true);
        stand.setCustomName("§6§lMagic Workbench");
    }

    public static void removeTextEntity(Location location) {
        Entity[] entities = location.getChunk().getEntities();
        Location updatedLoc = new Location(Bukkit.getWorlds().get(0), location.getBlockX() + 0.5, location.getBlockY() - 0.75,
                location.getBlockZ() + 0.5);
        for (Entity e : entities) {
            if (e.getLocation().equals(updatedLoc) && e instanceof ArmorStand) {

                if (e.getCustomName().equals("§6§lMagic Workbench")) {
                    e.remove();
                    return;
                }

            }
        }
    }

    public static void updateTableData(Location location) {
        location.getBlock().setMetadata("MagicCraftingTable",
                new FixedMetadataValue(Main.mainClassCall, "magic-craft"));
    }


}
