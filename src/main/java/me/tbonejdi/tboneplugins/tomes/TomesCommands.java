package me.tbonejdi.tboneplugins.tomes;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TomesCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;
        PackageInitializer pckg = FileStartupEvents.playerData.get(p.getName());

        if (cmd.getName().equalsIgnoreCase("tomes")) {
            TomeSelection gui = new TomeSelection(p);
            p.openInventory(gui.getInventory());
            p.sendMessage("§6Opening available tomes...");
        }

        if (cmd.getName().equalsIgnoreCase("detecttome")) {
            if (args.length != 1) {
                p.sendMessage("§6Expected format: /detecttome <bookNum>");
            }
            int bookNum = Integer.parseInt(args[0]);
            p.sendMessage("Value for book " + bookNum + ": " + pckg.tfw.isBookDiscovered(bookNum));

        }

        FileStartupEvents.playerData.replace(p.getName(), pckg);
        return true;
    }
}
