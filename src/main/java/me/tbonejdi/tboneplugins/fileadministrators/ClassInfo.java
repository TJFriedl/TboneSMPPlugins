package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.classes.ClassFile;
import me.tbonejdi.tboneplugins.classes.Warrior;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ClassInfo {

    public Player player;
    public String currentClass;
    public int currentLvl;
    public int currentXP;
    public int currentMaxEXP;

    public ClassInfo(Player player, String currentClass, int currentLvl, int currentXP,
                     int currentMaxExp) {
        this.player = player;
        this.currentClass = currentClass;
        this.currentLvl = currentLvl;
        this.currentXP = currentXP;
        this.currentMaxEXP = currentMaxExp;
    }

    public String getName() { return player.getName(); }

    public int getClassLvl() { return currentLvl; }

    public void setLevel(int lvl) {
        if (lvl > 30 || lvl < 0) {
            player.sendMessage("For right now, let's keep the level between 0 and 30.");
            return;
        }
        currentLvl = lvl;
    }

    public int getClassExp() { return currentXP; }

    public void setExp(int xp) { currentXP = xp; }

    public String getCurrentClass() { return currentClass; }

    public void setCurrentClass(String newClass) { currentClass = newClass; }

    public int getClassMaxExp() { return currentMaxEXP; }

    public void setMaxExp(int xp) { currentMaxEXP = xp; }

    public void gainClassXP(int gainedXP) { currentXP += gainedXP; }

    public void checkForLevelUp() {
        if (currentXP >= currentMaxEXP) {
            currentLvl++;
            currentXP -= currentMaxEXP;
            currentMaxEXP *= 2;
            player.sendMessage("§6LEVEL UP! You are now level §3" + currentLvl);
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, (float) 0.4, 1);
            ClassFile.resetPlayer(player);
            applyBuffs();
        }
    }

    public void applyBuffs() {
        String currentClass = this.currentClass;
        switch (currentClass) {
            case "Warrior":
                player.sendMessage("Applying warrior buffs!");
                Warrior.setBuffs(this);
                break;
            default:
                player.sendMessage("No class found...");
                setCurrentClass("None");
                ClassFile.resetPlayer(player);
                break;
        }
    }
}
