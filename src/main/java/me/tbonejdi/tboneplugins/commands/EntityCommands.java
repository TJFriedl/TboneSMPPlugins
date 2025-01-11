package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.mobs.SpiderEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;

public class EntityCommands implements CommandExecutor {

    /**
     * Handles event for entity command capture.
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
            sender.sendMessage("Only players can use that command");
            return true;
        }

        Player p = (Player) sender;
        // ------------ "removechunkentities" command ------------
        // TODO: Deal with apparent error coming from this?
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
        // ------------ "leapingspider" command ------------
        if (cmd.getName().equalsIgnoreCase("leapingspider")) {
            SpiderEvents.castToLeapingSpider(p.getWorld().spawn(p.getLocation(), Spider.class));
            return true;
        }
        // ------------ "leveledspider" command ------------
        if (cmd.getName().equalsIgnoreCase("leveledspider")) {
            SpiderEvents.castToLeveledSpider(p.getWorld().spawn(p.getLocation(), Spider.class));
        }

        return true;
    }
}
