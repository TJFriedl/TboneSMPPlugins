package me.tbonejdi.tboneplugins.tomes;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.items.FirstTome;
import me.tbonejdi.tboneplugins.items.SecondTome;
import me.tbonejdi.tboneplugins.items.SecondTomePage;
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

    /**
     * Tracks event when "tome" books are right-clicked in game.
     * @param e
     * @throws IOException
     */
    @EventHandler
    public static void onTomeRightClick(PlayerInteractEvent e) throws IOException {
        // Check to make sure we have an item and that it has metadata.
        if (e.getItem() == null) { return; }
        else if (e.getItem().getItemMeta() == null) { return; }

        Player p = e.getPlayer();
        PackageInitializer pckg = FileStartupEvents.playerData.get(e.getPlayer().getName());
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            // ------------ FIRST TOME ------------
            if (e.getItem().getItemMeta().getDisplayName().equals(FirstTome.firstTome.getItemMeta().getDisplayName())) {
                if (pckg.tfw.isBookDiscovered(0)) {
                    p.sendMessage(ChatColor.GRAY + "You've already activated that tome.");
                    e.getItem().setAmount(0);
                    return;
                } else if (!FirstTome.isIntendedUser(e.getItem(), p)) {
                    p.sendMessage(ChatColor.GRAY + "Hey! This item is not meant for you.");
                    return;
                }

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

            // ------------ SECOND TOME (w/ missing page) ------------
            if (e.getItem().getItemMeta().getDisplayName().equals(SecondTome.secondTome.getItemMeta().getDisplayName())) {
                if (pckg.tfw.isBookDiscovered(1)) {
                    p.sendMessage(ChatColor.GRAY + "You've already activated that tome.");
                    e.getItem().setAmount(0);
                    return;
                } else if (!(pckg.tfw.isBookDiscovered(0))) {
                    p.sendMessage(ChatColor.GRAY + "I cannot do that for you right now.");
                    return;
                } else if (!SecondTome.isIntendedUser(e.getItem(), p)) {
                    p.sendMessage(ChatColor.GRAY + "Hey! This item is not meant for you.");
                    return;
                }

                p.sendMessage(ChatColor.GRAY + "You have activated a tome! Use §e/tomes §7to read.");
                Bukkit.broadcastMessage(ChatColor.AQUA + p.getDisplayName() + " has found a new tome!");
                p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                e.getItem().setAmount(0);
                pckg.tfw.setBookStatus(1, true);
                pckg.tfw.saveToFile();
                FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
                return;
            }

            // ------------ SECOND TOME'S MISSING PAGE ------------
            if (e.getItem().getItemMeta().getDisplayName().equals(SecondTomePage.secondTomePage.getItemMeta().getDisplayName())) {
                if (pckg.tfw.isBookDiscovered(2)) {
                    p.sendMessage(ChatColor.GRAY + "You've already activated this page.");
                    e.getItem().setAmount(0);
                    return;
                } else if (!(pckg.tfw.isBookDiscovered(1))) {
                    p.sendMessage(ChatColor.GRAY + "I cannot do that for you right now.");
                    return;
                } else if (!SecondTomePage.isIntendedUser(e.getItem(), p)) {
                    p.sendMessage(ChatColor.GRAY + "Hey! This item is not meant for you.");
                    return;
                }

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
