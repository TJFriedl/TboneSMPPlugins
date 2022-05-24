package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.items.DiamondWand;
import me.tbonejdi.tboneplugins.items.MagicMirror;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemEvents implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
                && e.getItem() != null) {

            if (e.getItem().getItemMeta().equals(DiamondWand.diamondWand.getItemMeta())) {
                p.performCommand("diamonddetect");
            }

            if (e.getItem().getItemMeta().equals(MagicMirror.magicMirror.getItemMeta())) {
                Location bedSpawnLoc = p.getBedSpawnLocation();

                if (bedSpawnLoc == null) {
                    p.sendMessage(ChatColor.YELLOW + "Doesn't seen like you have a bed spawn set...");
                }
                else {
                    p.teleport(bedSpawnLoc);
                    p.sendMessage(ChatColor.GOLD + "§lWOOSH!");
                    e.getItem().setAmount(0);
                }
            }
        }
    }
}
