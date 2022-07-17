package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.items.MagicTable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class CantripEvents implements Listener {

    @EventHandler
    public static void playerStrike(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlockPlaced();

        if (block.getType() == Material.LAPIS_BLOCK) {
            int x = block.getLocation().getBlockX();
            int y = block.getLocation().getBlockY();
            int z = block.getLocation().getBlockZ();

            Block block2 = p.getWorld().getBlockAt(x, y-1, z);
            if (block2.getType() == Material.NETHERRACK) {
                e.getPlayer().getWorld().strikeLightning(block.getLocation());
                block2.setType(Material.AIR);
                block.setType(Material.AIR);
            }
            return;
        }

        if (block.getType() == Material.CRAFTING_TABLE) {
            // Do some kind of cantrip logic, then does magic event
            // First, check to make sure that the crafting table is laying on at LEAST the center of a 3x3 of amethyst
            Location location = e.getBlock().getLocation();
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) { // These loops will check for the surrounding 3x3 below the table.
                    if (p.getWorld().getBlockAt(location.getBlockX() + x, location.getBlockY() - 1,
                            location.getBlockZ() + z).getType() != Material.AMETHYST_BLOCK) {
                        return;
                    }
                }
            }
            p.sendMessage("Amethyst test passed!");

            // Check to make sure there are four lecterns surrounding the
            if (p.getWorld().getBlockAt(location.getBlockX() - 1, location.getBlockY(),
                    location.getBlockZ()).getType() != Material.LECTERN) {
                return;
            } else if (p.getWorld().getBlockAt(location.getBlockX() + 1, location.getBlockY(),
                    location.getBlockZ()).getType() != Material.LECTERN) {
                return;
            } else if (p.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(),
                    location.getBlockZ() - 1).getType() != Material.LECTERN) {
                return;
            } else if (p.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(),
                    location.getBlockZ() + 1).getType() != Material.LECTERN) {
                return;
            }
            p.sendMessage("Lectern test passed!");

            // Test Sequence... Error might occur on cast?
            Lectern lectern = (Lectern) p.getWorld().getBlockAt(location.getBlockX()+1,
                    location.getBlockY(), location.getBlockZ()).getState();
            if (!(lectern.getInventory().isEmpty())) {
                p.getWorld().strikeLightningEffect(location);
                block.setType(Material.AIR);
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.PAPER));
                location.getWorld().dropItemNaturally(location, new ItemStack(MagicTable.magicTable));
                p.sendMessage("Tests have passed!");
            }
        }

        return;
    }
}
