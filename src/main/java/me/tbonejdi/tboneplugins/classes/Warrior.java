package me.tbonejdi.tboneplugins.classes;

import me.tbonejdi.tboneplugins.fileadministrators.ClassInfo;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Warrior extends ClassFile{

    public static void setClass() {
        FileStartupEvents.cInfo.setCurrentClass("Warrior");
        setBuffs(FileStartupEvents.cInfo);
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
        else if (level > 0 && level < 4) { return 2.0; }
        else if (level >= 4 && level < 8) { return 4.0; }
        else if (level >= 8 && level < 10) { return 6.0; }
        else {
            return 8.0;
        }
    }

    /*
    FIXME: Add level parameter from cInfo somehow
     */
    private static double armorChanges(int level) {
        if (level <= 0) { return 0.0; }
        else if (level > 0 && level < 3) { return 1.0; }
        else if (level >= 3 && level < 6) { return 2.0; }
        else if (level >= 6 && level < 9) { return 3.0; }
        else if (level == 9) { return 4.0; }
        else {
            return 6.0;
        }
    }

}
