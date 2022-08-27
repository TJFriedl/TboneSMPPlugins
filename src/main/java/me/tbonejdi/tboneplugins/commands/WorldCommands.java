package me.tbonejdi.tboneplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;

        if (!(p.isOp())) {
            p.sendMessage("You are not oped!");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("sendtoaether")) {
            World world = Bukkit.getWorld("aether");
            p.teleport(world.getSpawnLocation());
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("returnfromaether")) {
            p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
            p.sendMessage("WOOSH!");
        }

        return true;
    }
}
