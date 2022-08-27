package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.*;
import java.util.HashSet;

public class MagicCraftingContainer implements Listener {

    public static HashSet<Location> tableLocations;
    private static BufferedWriter writer;
    private static BufferedReader reader;
    private static File file;


    public static void updateTableData(Location location) {
        location.getBlock().setMetadata("MagicCraftingTable",
                new FixedMetadataValue(Main.mainClassCall, "magic-craft"));
    }

    public static void initializeWorld() throws IOException {

        String directory = "//home//container//plugins//playerFiles//";
        File dir = new File(directory);
        File tableFile = new File(directory + "magictables.txt");
        writer = new BufferedWriter(new FileWriter(tableFile, true));
        reader = new BufferedReader(new FileReader(tableFile));
        tableLocations = new HashSet<>();
        file = tableFile;

        if (!(dir.exists())) {
            dir.mkdirs();
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "New Crafting File Created...");
        }
        else if (!(tableFile.exists())) {
            tableFile.createNewFile();
            return;
        }

        String line = reader.readLine();
        while(line != null) {
            Location loc = parseLocation(line);
            tableLocations.add(loc);
            line = reader.readLine();
        }

        for (Location loc : tableLocations) {
            updateTableData(loc);
            createTextEntity(loc);
        }

    }

    public static Location parseLocation(String dataString) {
        double x = 0, y = 0, z = 0;
        World world = null;

        for (int i = 0; i < dataString.length(); i++) {
            if (dataString.charAt(i-1) == 'e' && dataString.charAt(i) == '=') {
                world = parseWorld(i+1, dataString);
            }
            if (dataString.charAt(i) == 'x') { x = parseCoord(i+2, dataString); }
            else if (dataString.charAt(i) == 'y') { y = parseCoord(i+2, dataString); }
            else if (dataString.charAt(i) == 'z') {
                z = parseCoord(i+2, dataString);
                break;
            }
        }

        Location location = new Location(world, x, y, z);

        return location;
    }

    private static double parseCoord(int index, String dataString) {
        boolean negative = false;
        double coord;
        String number = "";

        if (dataString.charAt(index + 1) == '-') {
            negative = true;
            index++;
        }
        while (dataString.charAt(index) != '.') {
            number += dataString.charAt(index);
            index++;
        }

        coord = Double.parseDouble(number);
        if (negative) { coord *= -1.0; }
        return coord;
    }
    private static World parseWorld(int index, String dataString) {
        String worldName = "";

        while (dataString.charAt(index) != '}') {
            worldName += dataString.charAt(index);
        }

        World world = Bukkit.getWorld(worldName);

        return world;
    }

    public static void saveToFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(file)); // Resets file
        for (Location loc : tableLocations) {
            writer.write(loc.toString() + "\n");
        }
        writer.close();
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

}
