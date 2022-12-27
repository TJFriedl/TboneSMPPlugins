package me.tbonejdi.tboneplugins.classes;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
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
                Zombie zombie = (Zombie) e.getEntity();
                if (!zombie.isAdult()) {
                    xp = (int) Math.floor((Math.random() * 55));
                    player.sendMessage("§6Baby Zombie Gave §d" + xp + " §6experience");
                    pckg.cInfo.gainClassXP(xp);
                    pckg.cInfo.checkForLevelUp();
                    break;
                }
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
            case SPIDER:
                if (e.getEntity().getCustomName() != null &&
                        e.getEntity().getCustomName().contains("Leaping Spider")) {
                    xp = (int) Math.floor((Math.random() * 250) *
                            (1.0 + 0.1 * pckg.cInfo.currentLvl));
                    player.sendMessage(ChatColor.DARK_GRAY + "Leaping Spider §6Gave §d" + xp + " §6experience");
                    pckg.cInfo.gainClassXP(xp);
                    pckg.cInfo.checkForLevelUp();
                    break;
                }
                xp = (int) Math.floor((Math.random() * 50) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Spider Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case ENDERMAN:
                xp = (int) Math.floor((Math.random() * 100) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Enderman Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case WITHER_SKELETON:
                xp = (int) Math.floor((Math.random() * 50) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Wither Skeleton Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case BLAZE:
                xp = (int) Math.floor((Math.random() * 80) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Blaze Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case GHAST:
                xp = (int) Math.floor((Math.random() * 100) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Ghast Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case ZOMBIFIED_PIGLIN:
                xp = (int) Math.floor((Math.random() * 50) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Zombified Piglin Gave §d" + xp + " §6experience");
                pckg.cInfo.gainClassXP(xp);
                pckg.cInfo.checkForLevelUp();
                break;
            case WITCH:
                xp = (int) Math.floor((Math.random() * 90) *
                        (1.0 + 0.1 * pckg.cInfo.currentLvl));
                player.sendMessage("§6Witch Gave §d" + xp + " §6experience");
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
