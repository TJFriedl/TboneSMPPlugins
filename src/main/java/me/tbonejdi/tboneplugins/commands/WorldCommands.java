package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommands implements CommandExecutor {

    /**
     * Handles event for world command capture
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

        // Make sure that the player trying to call the command has OP privileges.
        if (!(p.isOp())) {
            p.sendMessage("You are not oped!");
            return true;
        }

        // ------------ "sendtoaether" command ------------
        if (cmd.getName().equalsIgnoreCase("sendtoaether")) {
            World world = Bukkit.getWorld("aether");
            p.teleport(world.getSpawnLocation());
            return true;
        }

        // ------------ "returnfromaether" command ------------
        if (cmd.getName().equalsIgnoreCase("returnfromaether")) {
            p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
            p.sendMessage("WOOSH!");
        }

        // ------------ "getcurrenttime" command ------------
        if (cmd.getName().equalsIgnoreCase("getcurrenttime")) {
            p.sendMessage("Current time is: " + p.getWorld().getTime());
            p.sendMessage("Calculated in game time: " + Main.handleInGameTime(p));
        }

        return true;
    }
}
