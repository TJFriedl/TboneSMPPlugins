package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class LevelProgressionEvents implements Listener {

    /**
     * Event tracks when a player breaks an ore block, adding to their custom XP amount
     *
     * @param e
     */
    @EventHandler
    public void gainXPEvent(BlockBreakEvent e) { // Change this to its own class?

        // Check to make sure player is in Survival
        if (!(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL))) return;

        // Grab event player's information to link back to database
        PackageInitializer pckg = FileStartupEvents.playerData.get(e.getPlayer().getName());
        Player player = e.getPlayer();
        Random rand = new Random();
        int XP;

        Material block = e.getBlock().getType();

        // Check to see which ore block was broken, and handle case specifically.
        switch (block) {
            case EMERALD_ORE:
                XP = rand.nextInt(10) + 50;
                pckg.pInfo.gainXP(XP);
                player.sendMessage(ChatColor.GOLD + "+" + XP + " §7Mining XP");
                pckg.pInfo.checkForLevelUp();
                break;
            case DIAMOND_ORE:
                XP = rand.nextInt(10) + 75;
                pckg.pInfo.gainXP(XP);
                player.sendMessage(ChatColor.GOLD + "+" + XP + " §7Mining XP");
                pckg.pInfo.checkForLevelUp();
                break;
            case GOLD_ORE:
                XP = rand.nextInt(10) + 30;
                pckg.pInfo.gainXP(XP);
                player.sendMessage(ChatColor.GOLD + "+" + XP + " §7Mining XP");
                pckg.pInfo.checkForLevelUp();
                break;
            case LAPIS_ORE:
                XP = rand.nextInt(10) + 25;
                pckg.pInfo.gainXP(XP);
                player.sendMessage(ChatColor.GOLD + "+" + XP + " §7Mining XP");
                pckg.pInfo.checkForLevelUp();
                break;
            case IRON_ORE:
                XP = rand.nextInt(10) + 15;
                pckg.pInfo.gainXP(XP);
                player.sendMessage(ChatColor.GOLD + "+" + XP + " §7Mining XP");
                pckg.pInfo.checkForLevelUp();
                break;
            case COAL_ORE:
                XP = rand.nextInt(10) + 10;
                pckg.pInfo.gainXP(XP);
                player.sendMessage(ChatColor.GOLD + "+" + XP + " §7Mining XP");
                pckg.pInfo.checkForLevelUp();
                break;
            case COPPER_ORE:
                XP = rand.nextInt(15) + 5;
                pckg.pInfo.gainXP(XP);
                player.sendMessage(ChatColor.GOLD + "+" + XP + " §7Mining XP");
                pckg.pInfo.checkForLevelUp();
                break;
        }

        FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
    }
}
