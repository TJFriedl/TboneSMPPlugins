package me.tbonejdi.tboneplugins.portals;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;

public class PortalEvents implements Listener {

    /**
     * Tracks event when a player would supposedly place water in an Aether portal.
     * @param e
     */
    @EventHandler
    public void createAetherPortal(PlayerBucketEmptyEvent e) {
        Block b = e.getBlock();

        // We should make sure it's a water bucket...
        if (e.getBucket() != Material.WATER_BUCKET) { return; }

        World w = b.getWorld();
        int trueZ = b.getZ();
        int trueY = b.getY();
        int trueX = b.getX();
        int glowStoneCt = 0;
        Location smallBlock = null, bigBlock = null;
        int localSum, specificSum; // This seems very complicated...
        boolean isZaxis = false;
        ArrayList<Location> portalFrame = new ArrayList<>();
        ArrayList<Location> portal = new ArrayList<>();

        // Setting up portal frame lazy search algo
        for (int x = -3; x <= 3; x++) {
            for (int y = -3; y <= 3; y++) {
                for (int z = -3; z <= 3; z++) {
                    localSum = x+y+z;
                    if (z == -3 && y == -3 && x == -3) {
                        bigBlock = new Location(w, trueX+x, trueY+y, trueZ+z);
                    }
                    if (w.getBlockAt(trueX+x, trueY+y, trueZ+z).getType() == Material.GLOWSTONE) {
                        glowStoneCt++;
                        if (smallBlock == null) { smallBlock = new Location(w, trueX+x, trueY+y, trueZ+z); }
                        specificSum = (trueX+x) + (trueY+y) + (trueZ+z);
                        if (specificSum >= localSum) {
                            bigBlock = new Location(w, trueX+x, trueY+y, trueZ+z);
                        }
                        portalFrame.add(new Location(w, trueX+x, trueY+y, trueZ+z));
                    }
                }
            }
        }

        // Return if not a full frame.
        if (glowStoneCt != 14) { return; }
        if (bigBlock.getBlockX() - smallBlock.getBlockX() == 0) { isZaxis = true; }
        if (isZaxis) { // Checking to make sure there is portal border.
            for (int y = 0; y<= 4; y++) {
                for (int z = 0; z<= 3; z++) {
                    Location tempBlock = new Location(w, smallBlock.getBlockX(), smallBlock.getBlockY()+y,
                            smallBlock.getBlockZ()+z);
                    if ((y == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) || (y == 4 &&
                            w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE)) {
                        return;
                    }
                    if ((y == 1 && z == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                            (y == 2 && z == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                    y == 3 && z == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) {
                        return;
                    }
                    if ((y == 1 && z == 3 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                            (y == 2 && z == 3 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                            y == 3 && z == 3 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) {
                        return;
                    } else {
                        portal.add(tempBlock);
                    }

                }
            }

            // If still going, you should have passed the test!
            for (int y = 1; y <= 3; y++) {
                for (int z = 1; z <=2; z++) {
                     Location tempBlock = new Location(w, smallBlock.getBlockX(), smallBlock.getBlockY()+y,
                             smallBlock.getBlockZ()+z);
                     w.getBlockAt(tempBlock).setType(Material.WATER);
                     w.getBlockAt(tempBlock).setMetadata("AetherWater",
                             new FixedMetadataValue(Main.mainClassCall, "aether-portal"));
                }
            }

            AetherPortal aetherPortal = new AetherPortal(w, portal, portalFrame);
        }
        else {
            for (int y = 0; y <= 4; y++) {
                for (int x = 0; x <= 3; x++) {
                    Location tempBlock = new Location(w, smallBlock.getBlockX()+x, smallBlock.getBlockY()+y,
                            smallBlock.getBlockZ());
                    if ((y == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) || (y == 4 &&
                            w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE)) {
                        return;
                    }
                    if ((y == 1 && x == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                            (y == 2 && x == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                            y == 3 && x == 0 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) {
                        return;
                    }
                    if ((y == 1 && x == 3 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                            (y == 2 && x == 3 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) ||
                            y == 3 && x == 3 && w.getBlockAt(tempBlock).getType() != Material.GLOWSTONE) {
                        return;
                    } else {
                        portal.add(tempBlock);
                    }
                }
            }

            for (int y = 1; y <= 3; y++) {
                for (int x = 1; x <=2; x++) {
                    Location tempBlock = new Location(w, smallBlock.getBlockX()+x, smallBlock.getBlockY()+y,
                            smallBlock.getBlockZ());
                    w.getBlockAt(tempBlock).setType(Material.WATER);
                    w.getBlockAt(tempBlock).setMetadata("AetherWater",
                            new FixedMetadataValue(Main.mainClassCall, "aether-portal"));
                }
            }
        }


    }

    /**
     * Event stops the flowing of water in the Aether Portal to make it seem like an actual aether portal.
     * @param e
     */
    @EventHandler
    public void stopWaterFlow(BlockFromToEvent e) {
        if (e.getBlock().hasMetadata("AetherWater"))
            e.setCancelled(true);
    }

    /**
     * Event responsible for sending player to the "Aether" world when they step through the correct portal.
     * @param e
     */
    @EventHandler
    public void teleportToAether(PlayerMoveEvent e) {
        Block block = e.getTo().getBlock();
        if (block.hasMetadata("AetherWater")) {
            World world = e.getPlayer().getWorld();
            if (world.getName().equalsIgnoreCase(Bukkit.getServer().getWorlds().get(0).getName())) {
                e.getPlayer().performCommand("sendtoaether");
            }
            else if (world.getName().equalsIgnoreCase("aether")) {
                e.getPlayer().performCommand("returnfromaether");
            }
        }
    }

    /**
     * Tracks when portal is destroyed.
     * TODO: This still needs to be implemented.
     *
     * @param e
     */
    @EventHandler
    public void onPortalDestroy(BlockBreakEvent e) {

    }


}
