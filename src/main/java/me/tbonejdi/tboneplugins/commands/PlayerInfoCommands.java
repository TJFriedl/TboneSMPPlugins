package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.classes.ClassType;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PlayerInfoCommands implements CommandExecutor {

    /**
     * Handles event for player info command capture
     *
     * @param sender
     * @param cmd
     * @param s
     * @param args
     * @return
     *
     * FIXME: (UPDATE FOR LATER) Some commands are not used inside playerInfo class, maybe make
     * its own commands class to clean up?
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;
        PackageInitializer pckg = FileStartupEvents.playerData.get(p.getName());

        // Make sure that the player trying to call the command has OP privileges.
        if (!(p.isOp())) {
            p.sendMessage("§4You do not have sufficient permissions.");
            return true;
        }

        // ------------ "setplayerxp" command ------------
        if (cmd.getName().equalsIgnoreCase("setplayerxp")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setrpgxp <xp>");
            }
            int xp = Integer.parseInt(args[0]);
            pckg.pInfo.setExp(xp);
            p.sendMessage("XP value has been set to " + xp);
            try {
                pckg.fw.saveToFile(pckg.pInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileStartupEvents.playerData.replace(p.getName(), pckg);
            return true;
        }

        // ------------ "setplayerlevel" command ------------
        if (cmd.getName().equalsIgnoreCase("setplayerlevel")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setrpglevel <level>");
            }
            int lvl = Integer.parseInt(args[0]);
            pckg.pInfo.setLevel(lvl);
            p.sendMessage("Level value has been set to " + lvl);
            try {
                pckg.fw.saveToFile(pckg.pInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileStartupEvents.playerData.replace(p.getName(), pckg);
            return true;
        }

        // ------------ "setplayerclass" command ------------
        if (cmd.getName().equalsIgnoreCase("setplayerclass")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setrpgclassname <className>");
            }
            ClassType classType = ClassType.valueOf(args[0]); // If errors are presented, check here
            pckg.cInfo.setCurrentClass(classType);
            pckg.cInfo.applyBuffs();
            p.sendMessage("Class name changed to §6" + classType);
            try {
                pckg.fw.saveToFile(pckg.pInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileStartupEvents.playerData.replace(p.getName(), pckg);
            return true;
        }

        // ------------ "setclasslevel" command ------------
        if (cmd.getName().equalsIgnoreCase("setclasslevel")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setclasslevel <level>");
            }
            int lvl = Integer.parseInt(args[0]);
            pckg.cInfo.setLevel(lvl);
            p.sendMessage("Level value has been set to " + lvl);
            try {
                pckg.cw.saveToFile(pckg.cInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileStartupEvents.playerData.replace(p.getName(), pckg);
            return true;
        }

        // ------------ "setclassxp" command ------------
        if (cmd.getName().equalsIgnoreCase("setclassxp")) {
            if (args.length != 1) {
                p.sendMessage("§4Incorrect format: §6/setclassxp <xp>");
            }
            int xp = Integer.parseInt(args[0]);
            pckg.cInfo.setExp(xp);
            p.sendMessage("XP value has been set to " + xp);
            try {
                pckg.cw.saveToFile(pckg.cInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileStartupEvents.playerData.replace(p.getName(), pckg);
            return true;
        }

        // ------------ "deletefiledata" command ------------
        // TODO: Maybe add a prompt for the user to confirm they want to do this. THIS IS DANGEROUS.
        if (cmd.getName().equalsIgnoreCase("deletefiledata")) {
           p.sendMessage("We are deleting your current save file data INCLUDING tomes...");
           pckg.tfw.deleteFile();
           pckg.fw.deleteFile();
           pckg.cw.deleteFile();
           FileStartupEvents.playerStates.get(p.getName()).isPlayerReset = true;
           p.kickPlayer(ChatColor.GREEN + "Files deleted successfully! Kicking for security.");
           FileStartupEvents.playerData.replace(p.getName(), pckg);
           return true;
        }

        // ------------ "getplayermaxhealth" command ------------
        if (cmd.getName().equalsIgnoreCase("getplayermaxhealth")) {
            // OVERRIDDEN FOR SEVERAL DIFFERENT STATS
            p.sendMessage("Max player health: " + p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            p.sendMessage("Current movement speed: " + p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue());
            p.sendMessage("Generic armor value: " + p.getAttribute(Attribute.GENERIC_ARMOR).getValue());
            return true;
        }

        // ------------ "setplayermaxhealth" command ------------
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
            return true;
        }

        // ------------ "setplayerbasearmor" command ------------
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
            return true;
        }

        // ------------ "printlocation" command ------------
        if (cmd.getName().equalsIgnoreCase("printlocation")) {
            p.sendMessage(p.getLocation().toString());
            return true;
        }

        // ------------ "quit" command ------------
        if (cmd.getName().equalsIgnoreCase("quit")) {
            p.kickPlayer(ChatColor.BLUE + "Quitting now!");
            return true;
        }

        return true;
    }
}
