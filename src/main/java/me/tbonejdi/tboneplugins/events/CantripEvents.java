package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.items.MagicTable;
import me.tbonejdi.tboneplugins.items.SecondTomePage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CantripEvents implements Listener {

    @EventHandler
    public static void playerStrike(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlockPlaced();

        /** This below is just some test code, remove this at some point. **/
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
            PackageInitializer pckg = FileStartupEvents.playerData.get(p.getName());
            Location location = e.getBlock().getLocation();
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) { // These loops will check for the surrounding 3x3 below the table.
                    if (p.getWorld().getBlockAt(location.getBlockX() + x, location.getBlockY() - 1,
                            location.getBlockZ() + z).getType() != Material.AMETHYST_BLOCK) {
                        return;
                    }
                }
            }

            if (p.getWorld().getBlockAt(location.getBlockX() - 1, location.getBlockY(),
                    location.getBlockZ()).getType() != Material.LECTERN) {
                return;
            } else if (p.getWorld().getBlockAt(location.getBlockX() + 1, location.getBlockY(),
                    location.getBlockZ()).getType() != Material.LECTERN) {
                return;
            } else if (p.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(),
                    location.getBlockZ() - 1).getType() != Material.LECTERN) {
                return;
            }

            if (p.getWorld().getBlockAt(location.getBlockX()-1, location.getBlockY(),
                    location.getBlockZ()-1).getType() != Material.BOOKSHELF) {
                return;
            } else if (p.getWorld().getBlockAt(location.getBlockX()+1, location.getBlockY(),
                    location.getBlockZ()-1).getType() != Material.BOOKSHELF) {
                return;
            }

            Lectern lectern = (Lectern) p.getWorld().getBlockAt(location.getBlockX(),
                    location.getBlockY(), location.getBlockZ()-1).getState();

            if (lectern.getInventory().isEmpty()) { return; }
            p.getWorld().strikeLightningEffect(location);
            block.setType(Material.AIR);
            location.getWorld().dropItemNaturally(location, new ItemStack(MagicTable.magicTable));
            if (!(pckg.tfw.isBookDiscovered(2))) {
                SecondTomePage.resetItem();
                ItemStack item = SecondTomePage.secondTomePage;
                ItemMeta im = item.getItemMeta();
                List<String> lore = im.getLore();
                lore.add((ChatColor.DARK_BLUE + "Page magically created for " + ChatColor.GOLD + p.getName()));
                im.setLore(lore);
                item.setItemMeta(im);

                location.getWorld().dropItemNaturally(location, item);
            }
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    p.getWorld().getBlockAt(location.getBlockX()+x, location.getBlockY()-1,
                            location.getBlockZ()+z).setType(Material.COBBLED_DEEPSLATE);
                    p.getWorld().getBlockAt(location.getBlockX()+x, location.getBlockY(),
                            location.getBlockZ()+z).setType(Material.AIR);
                }
            }
            return;
        }

        return; // TODO: Investigate this eventually. I might have put this here for a reason lol.
    }
}
