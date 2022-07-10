package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.inventories.ClassSelection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClassCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("chooseclass")) {
            ClassSelection gui = new ClassSelection();
            p.openInventory(gui.getInventory());
            p.sendMessage("§6Change your class now!");
        }

        return true;
    }
}
