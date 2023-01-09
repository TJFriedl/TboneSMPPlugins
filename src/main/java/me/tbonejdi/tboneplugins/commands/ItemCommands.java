package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.items.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("summonitem")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/summonitem <itemname>");
            }
            String itemName = args[0];

            // These only account for items that have been coded so far.
            if (itemName.equalsIgnoreCase("diamondwand")) {
                p.getInventory().addItem(DiamondWand.diamondWand);
            }
            else if (itemName.equalsIgnoreCase("magicmirror")) {
                p.getInventory().addItem(MagicMirror.magicMirror);
            }
            else if (itemName.equalsIgnoreCase("crystalfruit")) {
                p.getInventory().addItem(CrystalFruit.crystalFruit);
            }
            else if (itemName.equalsIgnoreCase("floatingwand")) {
                p.getInventory().addItem(FloatingWand.floatingWand);
            }
            else if (itemName.equalsIgnoreCase("magicworkbench")) {
                p.getInventory().addItem(MagicTable.magicTable);
            }
            else if (itemName.equalsIgnoreCase("weed") || itemName.equalsIgnoreCase("marijuana")) {
                p.getInventory().addItem(Marijuana.marijuana);
            }
            else if (itemName.equalsIgnoreCase("blunt")) {
                p.getInventory().addItem(Blunt.blunt);
            }
            else {
                p.sendMessage("§4Error: Item does not seem to exist.");
            }
        }

        if (cmd.getName().equalsIgnoreCase("diamonddetect")) {
            boolean diamondsFound = false;
            int diamondCtr = 0;
            p.sendMessage("§d§lScanning for diamonds...");
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
        return true;
    }
}
