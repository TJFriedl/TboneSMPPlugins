package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
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

        if (cmd.getName().equalsIgnoreCase("getcurrenttime")) {
            p.sendMessage("Current time is: " + p.getWorld().getTime());
            p.sendMessage("Calculated in game time: " + Main.handleInGameTime(p));
        }

        return true;
    }
}
