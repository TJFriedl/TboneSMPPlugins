package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.fileadministrators.PlayerInfo;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LevelProgressionEvents implements Listener {

    @EventHandler
    public void gainXPEvent(BlockBreakEvent e) { // Change this to its own class?

        PackageInitializer pckg = FileStartupEvents.playerData.get(e.getPlayer().getName());
        PlayerInfo playerStats = pckg.pInfo;

        Material block = e.getBlock().getType();
        if (block == Material.EMERALD_ORE) {
            playerStats.gainXP();
            playerStats.checkForLevelUp();
        }

        FileStartupEvents.playerData.replace(e.getPlayer().getName(), pckg);
    }
}
