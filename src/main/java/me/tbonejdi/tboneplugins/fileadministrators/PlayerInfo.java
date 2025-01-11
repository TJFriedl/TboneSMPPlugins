package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Class responsible for holding all player information, such as level, experience, and max level experience.
 */
public class PlayerInfo {

    private String name;
    private int level;
    private int experience;
    private int maxExperience;
    private Player player;

    /**
     * PlayerInfo Constructor.
     *
     * @param name
     * @param level
     * @param experience
     * @param maxExperience
     * @param player
     */
    public PlayerInfo(String name, int level, int experience, int maxExperience, Player player) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.maxExperience = maxExperience;
        this.player = player;
    }

    /**
     * Grab player name.
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * Grab player level.
     * @return player level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set player level.
     * @param lvl
     */
    public void setLevel(int lvl) {
        level = lvl;
    }

    /**
     * Get player experience
     * @return player experience
     */
    public int getExp() {
        return experience;
    }

    /**
     * Sets current player experience
     * @param xp
     */
    public void setExp(int xp) {
        experience = xp;
    }

    /**
     * Grabs players current maximum experience needed to level up.
     * @return
     */
    public int getMaxExp() { return maxExperience; }

    /**
     * Adds player experience.
     * @param gainedXP
     */
    public void gainXP(int gainedXP) {
        experience += gainedXP;
    }

    /**
     * Check to see if player is eligible for level up.
     */
    public void checkForLevelUp() {
        if (experience >= maxExperience) {
            experience -= maxExperience;
            level += 1;
            maxExperience *= 2;
            player.sendMessage( "§6§lLevel up! §7You are now Lv. §b§l" + level);
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, (float) 0.4, 1);
            checkPlayerUnlocks();
        }
    }

    /**
     * Message that will show what features player unlocks during level up.
     */
    private void checkPlayerUnlocks() {
        player.sendMessage("§6§lNew Features at Lv. §b§l" + level);
        player.sendMessage("§6§l-=-=-=-=-=-=-=-=-=-");
        switch(level) {
            case 1:
                player.sendMessage("§b+5% §7Player Level XP"); // Still needs implementation
                break;
            default:
                break;
        }
    }

}
