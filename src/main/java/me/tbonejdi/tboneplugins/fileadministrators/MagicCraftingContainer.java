package me.tbonejdi.tboneplugins.fileadministrators;

import com.sun.istack.internal.NotNull;
import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class MagicCraftingContainer implements Listener {

    public static HashSet<Location> tableLocations;
    private static final File tableFile = new File("//home//container//plugins//playerFiles//magictables.txt");

    //TODO: Given location, update block's metadata... Add entity with floating name?
    public void setBlockMetaData(Location location) {
        location.getBlock().setMetadata("MagicCraftingTable",
                new FixedMetadataValue(Main.mainClassCall, "magic-craft"));
    }

    //TODO: Work on this. Should call parseLocation.
    public static void initializeWorld() throws IOException {
        if (!(tableFile.exists()))
            tableFile.createNewFile();

        //TODO: Parse file, add reader/writer, figre out how to read and save location to file.

    }

    public Location parseLocation() {
        Location location;

        return null; //FIXME
    }

    //TODO: Use on server shutdown and restarts.
    public void saveToFile() {

    }
}
