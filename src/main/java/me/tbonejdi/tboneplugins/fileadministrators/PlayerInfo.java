package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerInfo {

    private String name;
    private int level;
    private int experience;
    private int maxExperience;
    private Player player;


    public PlayerInfo(String name, int level, int experience, int maxExperience, Player player) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.maxExperience = maxExperience;
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int lvl) {
        level = lvl;
    }

    public int getExp() {
        return experience;
    }

    public void setExp(int xp) {
        experience = xp;
    }

    public int getMaxExp() { return maxExperience; }

    public void gainXP(int gainedXP) {
        experience += gainedXP;
    }

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
