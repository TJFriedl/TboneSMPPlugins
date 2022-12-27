package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.mobs.SpiderEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EntityCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command");
            return true;
        }

        Player p = (Player) sender;
        //TODO: Deal with apparent error coming from this?
        if (cmd.getName().equalsIgnoreCase("removechunkentities")) {
            Entity[] entities = p.getLocation().getChunk().getEntities();

            if (entities == null) {
                p.sendMessage("No entities found...");
                return true;
            }
            for (Entity e : entities) {
                e.remove();
            }
            p.sendMessage("Chunk entities removed!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("leapingspider")) {
            SpiderEvents.spawnLeadingSpider(p.getLocation());
            return true;
        }

        return true;
    }
}
