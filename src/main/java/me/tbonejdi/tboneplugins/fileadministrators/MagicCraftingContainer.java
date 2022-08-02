package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.*;
import java.util.HashSet;

public class MagicCraftingContainer implements Listener {

    public static HashSet<Location> tableLocations;
    private static BufferedWriter writer;
    private static BufferedReader reader;
    private static File file;


    //TODO: Add entity spawn for name :)
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
        }

    }

    public static Location parseLocation(String dataString) {
        double x = 0, y = 0, z = 0;

        for (int i = 0; i < dataString.length(); i++) {
            if (dataString.charAt(i) == 'x') { x = parseCoord(i+2, dataString); }
            else if (dataString.charAt(i) == 'y') { y = parseCoord(i+2, dataString); }
            else if (dataString.charAt(i) == 'z') {
                z = parseCoord(i+2, dataString);
                break;
            }
        }

        Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);

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

    public static void saveToFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(file)); // Resets file
        for (Location loc : tableLocations) {
            writer.write(loc.toString() + "\n");
        }
        writer.close();
    }

}
