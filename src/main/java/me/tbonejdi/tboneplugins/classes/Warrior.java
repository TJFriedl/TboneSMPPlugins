package me.tbonejdi.tboneplugins.classes;

import me.tbonejdi.tboneplugins.fileadministrators.ClassInfo;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Warrior extends ClassFile implements Listener {

    public static void setClass(Player player) {
        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());

        pckg.cInfo.setCurrentClass(ClassType.WARRIOR);
        setBuffs(pckg.cInfo);

        FileStartupEvents.playerData.replace(player.getName(), pckg);
    }

    public static void setBuffs(ClassInfo classInfo) {
        Player player = classInfo.player;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0 + healthChanges(classInfo.currentLvl));
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(armorChanges(classInfo.currentLvl));
    }

    /*
    FIXME: Add level parameter from cInfo somehow
     */
    private static double healthChanges(int level) {
        if (level <= 0) { return 0.0; }
        else if (level > 0 && level < 4) { return 1.0; }
        else if (level >= 4 && level < 9) { return 2.0; }
        else if (level >= 9 && level < 15) { return 3.0; }
        else if (level >= 15 && level < 20) { return 4.0; }
        else return 5.0;
    }

    /*
    FIXME: Add level parameter from cInfo somehow
     */
    private static double armorChanges(int level) {
        if (level <= 2) { return 0.0; }
        else if (level >= 3 && level < 7) { return 2.0; }
        else if (level >= 7 && level < 13) { return 3.0; }
        else if (level >= 14 && level < 18) { return 3.0; }
        else return 4.0;
    }

}
