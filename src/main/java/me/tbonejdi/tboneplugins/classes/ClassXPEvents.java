package me.tbonejdi.tboneplugins.classes;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ClassXPEvents implements Listener {

    @EventHandler
    public static void onMobKill(EntityDeathEvent e) {
        if (!(e.getEntity().getKiller() instanceof Player)) { return; } // Prevents next line errors
        Player player = e.getEntity().getKiller();
        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());
        int xp;

        if (e.getEntity() instanceof Player || e.getEntity().getKiller() == null) { return; } // Has to be player killing a mob

        switch (e.getEntity().getType()) {
            case ZOMBIE:
                xp = (int) Math.floor((Math.random() * 50) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Zombie Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case SKELETON:
                xp = (int) Math.floor((Math.random() * 40) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Skeleton Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case CREEPER:
                xp = (int) Math.floor((Math.random() * 60) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Creeper Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            default:
                break;
            // Eventually add a case more all mobs you think apply...

        }
        FileStartupEvents.playerData.replace(player.getName(), pckg); // Update the Map with the updated data
    }
}
