package me.tbonejdi.tboneplugins.tomes;

import me.tbonejdi.tboneplugins.events.MobDropEvents;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.fileadministrators.TomesFileWorker;
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
            if (e.getItem().getItemMeta()
                    .equals(MobDropEvents.tutorialBook.getItemMeta())) {
                p.sendMessage(ChatColor.YELLOW + "You have activated a tome! Use /tomes to read");
                Bukkit.broadcastMessage(ChatColor.AQUA + p.getDisplayName()
                        + " has found their first tome!");
                p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
                e.getItem().setAmount(0);
                TomesFileWorker tfw = pckg.tfw;
                tfw.setBookStatus(0, true);
                tfw.saveToFile();
            }
        }
        FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
    }

}
