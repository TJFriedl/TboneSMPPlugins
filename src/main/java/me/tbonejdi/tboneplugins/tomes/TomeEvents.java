package me.tbonejdi.tboneplugins.tomes;

import me.tbonejdi.tboneplugins.events.CantripEvents;
import me.tbonejdi.tboneplugins.events.CraftingEvents;
import me.tbonejdi.tboneplugins.events.MobDropEvents;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.IOException;

public class TomeEvents implements Listener {

    @EventHandler
    public static void onTomeRightClick(PlayerInteractEvent e) throws IOException {
        if (e.getItem() == null) { return; }
        Player p = e.getPlayer();
        PackageInitializer pckg = FileStartupEvents.playerData.get(e.getPlayer().getName());
        if (MobDropEvents.tutorialBook == null) { return; }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            /*
            Preset designed for the first tome
             */
            if (e.getItem().getItemMeta().equals(MobDropEvents.tutorialBook.getItemMeta())) {
                if (pckg.tfw.isBookDiscovered(0)) {
                    p.sendMessage(ChatColor.GRAY + "Hey! You've already activated that tome.");
                    e.getItem().setAmount(0);
                    return;
                }

                //TODO: Add conditional so player cannot activate tome meant for someone else.

                p.sendMessage(ChatColor.GRAY + "You have activated a tome! Use §e/tomes §7to read.");
                Bukkit.broadcastMessage(ChatColor.BLUE + p.getDisplayName()
                        + " has found their first tome!");
                p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                e.getItem().setAmount(0);
                pckg.tfw.setBookStatus(0, true);
                pckg.tfw.saveToFile();
                FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
                return;
            }

            if (e.getItem().getItemMeta().equals(CraftingEvents.magicCraftingBook.getItemMeta())) {
                if (pckg.tfw.isBookDiscovered(1)) {
                    p.sendMessage(ChatColor.GRAY + "Hey! You've already activated that tome.");
                    e.getItem().setAmount(0);
                    return;
                }
                else if (!(pckg.tfw.isBookDiscovered(0))) {
                    p.sendMessage(ChatColor.GRAY + "Hey, I cannot do that for you right now.");
                    return;
                }

                //TODO: Add conditional so player cannot activate tome meant for someone else.

                p.sendMessage(ChatColor.GRAY + "You have activated a tome! Use §e/tomes §7to read.");
                Bukkit.broadcastMessage(ChatColor.AQUA + p.getDisplayName() + " has found a new tome!");
                p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                e.getItem().setAmount(0);
                pckg.tfw.setBookStatus(1, true);
                pckg.tfw.saveToFile();
                FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
                return;
            }

            if (e.getItem().equals(CantripEvents.firstMissingPage.getItemMeta())) {
                if (pckg.tfw.isBookDiscovered(2)) {
                    p.sendMessage(ChatColor.GRAY + "Hey! You've already activated this page.");
                    e.getItem().setAmount(0);
                    return;
                }
                else if (!(pckg.tfw.isBookDiscovered(1))) {
                    p.sendMessage(ChatColor.GRAY + "Hey, I cannot do that for you right now.");
                    return;
                }

                //TODO: Add conditional so player cannot activate tome meant for someone else.

                p.sendMessage(ChatColor.GRAY + "Hmm... This fits right into the magic crafting tome.");
                Bukkit.broadcastMessage(ChatColor.AQUA + p.getDisplayName() + " has found a missing page!");
                p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                e.getItem().setAmount(0);
                pckg.tfw.setBookStatus(2, true);
                pckg.tfw.saveToFile();
                FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
                return;
            }
        }
        FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
    }

}
