package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.inventories.MagicItemsList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommands implements CommandExecutor {

    /**
     * Handles event for item command capture
     *
     * @param sender
     * @param cmd
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;

        // ------------ "diamonddetect" command ------------
        if (cmd.getName().equalsIgnoreCase("diamonddetect")) {
            boolean diamondsFound = false;
            int diamondCtr = 0;
            p.sendMessage("§d§lScanning for diamonds...");

            // Search in 5x5x5 spherical radius
            for (int x = -5; x <= 5; x++) {
                for (int y = -5; y <= 5; y++) {
                    for (int z = -5; z <= 5; z++) {
                        Material b = p.getWorld().getBlockAt(p.getLocation().getBlockX() + x, p.getLocation().getBlockY() + y, p.getLocation().getBlockZ() + z)
                                .getType();
                        if (b == Material.DIAMOND_ORE || b == Material.DEEPSLATE_DIAMOND_ORE) {
                            diamondsFound = true;
                            diamondCtr++;
                        }
                    }
                }
            }
            if (diamondsFound) {
                p.sendMessage("§e§lDiamonds have been found!");
                p.sendMessage(diamondCtr + " diamond(s) have been detected nearby.");
            }
            else {
                p.sendMessage("§4No diamonds were found :(");
            }
        }

        // ------------ "magicitems" command ------------
        if (cmd.getName().equalsIgnoreCase("magicitems")) {

            if (!p.isOp()) {
                p.sendMessage(ChatColor.RED + "You must have op permissions to run this command.");
                return true;
            }
            p.sendMessage("Select some magic items!");
            MagicItemsList gui = new MagicItemsList();
            p.openInventory(gui.getInventory());
        }
        return true;
    }
}
