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

public class PortalEvents implements Listener {

    @EventHandler
    public void createAetherPortal(PlayerBucketEmptyEvent e) {
        Block b = e.getBlock();
        if (e.getBucket() != Material.WATER_BUCKET) { return; }

        World w = b.getWorld();
        int trueZ = b.getZ();
        int trueY = b.getY();
        int trueX = b.getX();
        int glowStoneCt = 0;
        Location smallBlock = null, bigBlock = null;
        int localSum, specificSum; // This seems very complicated...
        boolean isZaxis = false;

        // Setting up portal frame search algo
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
                    }
                }
            }
        }
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

    @EventHandler
    public void stopWaterFlow(BlockFromToEvent e) {
        if (e.getBlock().hasMetadata("AetherWater"))
            e.setCancelled(true);
    }

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

    @EventHandler
    public void onPortalDestroy(BlockBreakEvent e) {
        //TODO:
    }


}
