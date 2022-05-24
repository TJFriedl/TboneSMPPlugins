package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PlayerInfoCommands implements CommandExecutor {

    /**
     *
     * @param sender
     * @param cmd
     * @param s
     * @param args
     * @return
     *
     * UPDATE FOR LATER: Some commands are not used inside playerInfo class, maybe make
     * its own commands class to clean up?
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("setplayerxp")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setrpgxp <xp>");
            }
            int xp = Integer.parseInt(args[0]);
            FileStartupEvents.pInfo.setExp(xp);
            p.sendMessage("XP value has been set to " + xp);
            try {
                FileStartupEvents.fw.saveToFile(FileStartupEvents.pInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (cmd.getName().equalsIgnoreCase("setplayerlevel")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setrpglevel <level>");
            }
            int lvl = Integer.parseInt(args[0]);
            FileStartupEvents.pInfo.setLevel(lvl);
            p.sendMessage("Level value has been set to " + lvl);
            try {
                FileStartupEvents.fw.saveToFile(FileStartupEvents.pInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (cmd.getName().equalsIgnoreCase("setplayerclass")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setrpgclassname <className>");
            }
            String className = args[0];
            FileStartupEvents.cInfo.setCurrentClass(className);
            p.sendMessage("Class name changed to §6" + className);
            try {
                FileStartupEvents.fw.saveToFile(FileStartupEvents.pInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (cmd.getName().equalsIgnoreCase("setclasslevel")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setclasslevel <level>");
            }
            int lvl = Integer.parseInt(args[0]);
            FileStartupEvents.cInfo.setLevel(lvl);
            p.sendMessage("Level value has been set to " + lvl);
            try {
                FileStartupEvents.cw.saveToFile(FileStartupEvents.cInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (cmd.getName().equalsIgnoreCase("setclassxp")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setclassxp <xp>");
            }
            int xp = Integer.parseInt(args[0]);
            FileStartupEvents.cInfo.setExp(xp);
            p.sendMessage("XP value has been set to " + xp);
            try {
                FileStartupEvents.cw.saveToFile(FileStartupEvents.cInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (cmd.getName().equalsIgnoreCase("deletefiledata")) {
           p.sendMessage("We are deleting your current save file data INCLUDING tomes...");
           FileStartupEvents.tfw.deleteFile();
           FileStartupEvents.fw.deleteFile();
           FileStartupEvents.cw.deleteFile();
           FileStartupEvents.playerReset = true; // Error prevention
           p.kickPlayer(ChatColor.GREEN + "Files deleted successfully! Kicking for security.");

        }

        if (cmd.getName().equalsIgnoreCase("getplayermaxhealth")) {
            // OVERRIDEN FOR SEVERAL DIFFERENT STATS
            p.sendMessage("Max player health: " + p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            p.sendMessage("Current movement speed: " + p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue());
            p.sendMessage("Generic armor value: " + p.getAttribute(Attribute.GENERIC_ARMOR).getValue());
        }

        if (cmd.getName().equalsIgnoreCase("setplayermaxhealth")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setplayermaxhealth <health: def = 20>");
            }
            float maxHealth = Float.parseFloat(args[0]);
            if (maxHealth >= 40.0) {
                p.sendMessage("Try assigning a lower value (40)");
            }
            else {
                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
                p.sendMessage("New max health value: " + maxHealth);
            }
        }

        if (cmd.getName().equalsIgnoreCase("setplayerbasearmor")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setplayerbasearmor <armor: def = 0>");
            }
            float baseArmor = Float.parseFloat(args[0]);
            if (baseArmor >= 40.0) {
                p.sendMessage("Try assigning to a value lower than (40)");
            }
            else {
                p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(baseArmor);
                p.sendMessage("New base armor value:" + baseArmor);
            }
        }

        return true;
    }
}
