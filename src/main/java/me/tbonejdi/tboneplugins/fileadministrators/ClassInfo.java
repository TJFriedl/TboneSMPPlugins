package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.classes.ClassFile;
import me.tbonejdi.tboneplugins.classes.ClassType;
import me.tbonejdi.tboneplugins.classes.Warrior;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Represents the container of all class info for a player on the server. Contains items such as player's class,
 * current level, current experience, and level's maximum experience (might remove later).
 */
public class ClassInfo {

    public Player player;
    public ClassType classType;
    public int currentLvl;
    public int currentXP;
    public int currentMaxEXP;

    /**
     * Constructs a new ClassInfo object for a player.
     *
     * @param player
     * @param classType
     * @param currentLvl
     * @param currentXP
     * @param currentMaxExp
     */
    public ClassInfo(Player player, ClassType classType, int currentLvl, int currentXP,
                     int currentMaxExp) {
        this.player = player;
        this.classType = classType;
        this.currentLvl = currentLvl;
        this.currentXP = currentXP;
        this.currentMaxEXP = currentMaxExp;
    }

    /**
     * Returns in-game player's name.
     *
     * @return player's name
     */
    public String getName() { return player.getName(); }

    /**
     * Returns in-game player's level.
     *
     * @return player's current level.
     */
    public int getClassLvl() { return currentLvl; }

    /**
     * Set player's current level.
     *
     * @param lvl
     */
    public void setLevel(int lvl) {
        if (lvl > 30 || lvl < 0) {
            player.sendMessage("For right now, let's keep the level between 0 and 30.");
            return;
        }
        currentLvl = lvl;
    }

    /**
     * Returns player's current class experience.
     *
     * @return current class experience.
     */
    public int getClassExp() { return currentXP; }

    /**
     * Sets player's current class experience.
     *
     * @param xp
     */
    public void setExp(int xp) { currentXP = xp; }

    /**
     * Returns player's current class.
     *
     * @return player's class type.
     */
    public ClassType getCurrentClass() { return classType; }

    /**
     * Sets player's current class.
     *
     * @param newClass
     */
    public void setCurrentClass(ClassType newClass) { classType = newClass; }

    /**
     * Get player's current maximum experience associated with their class level.
     *
     * @return current class's maximum experience.
     */
    public int getClassMaxExp() { return currentMaxEXP; }

    /**
     * Sets player's maximum experience (typically used for debugging purposes)
     *
     * @param xp
     */
    public void setMaxExp(int xp) { currentMaxEXP = xp; }

    /**
     * Add to player's current experience.
     *
     * @param gainedXP
     */
    public void gainClassXP(int gainedXP) { currentXP += gainedXP; }

    /**
     * Checks to see if player is current eligible for level up.
     */
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

    /**
     * Applies buffs associated with player's specific class.
     */
    public void applyBuffs() {
        ClassType classType = this.classType;
        switch (classType) {
            case WARRIOR:
                player.sendMessage("Applying warrior buffs!");
                Warrior.setBuffs(this);
                break;
            default:
                player.sendMessage("No class found...");
                setCurrentClass(ClassType.NONE);
                ClassFile.resetPlayer(player);
                break;
        }
    }
}
