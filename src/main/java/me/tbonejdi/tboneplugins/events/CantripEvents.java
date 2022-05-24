package me.tbonejdi.tboneplugins.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class CantripEvents implements Listener {

    @EventHandler
    public static void playerStrike(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlockPlaced();

        if (block.getType() == Material.LAPIS_BLOCK) {
            int x = block.getLocation().getBlockX();
            int y = block.getLocation().getBlockY();
            int z = block.getLocation().getBlockZ();

            Block block2 = p.getWorld().getBlockAt(x, y-1, z);
            if (block2.getType() == Material.NETHERRACK) {
                e.getPlayer().getWorld().strikeLightning(block.getLocation());
                block2.setType(Material.AIR);
                block.setType(Material.AIR);
            }
        }
    }
}
