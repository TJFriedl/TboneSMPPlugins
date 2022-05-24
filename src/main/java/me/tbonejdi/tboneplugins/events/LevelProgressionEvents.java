package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LevelProgressionEvents implements Listener {

    @EventHandler
    public void gainXPEvent(BlockBreakEvent e) { // Change this to its own class?
        Material block = e.getBlock().getType();
        if (block == Material.EMERALD_ORE) {
            FileStartupEvents.pInfo.gainXP();
            FileStartupEvents.pInfo.checkForLevelUp();
        }
    }
}
