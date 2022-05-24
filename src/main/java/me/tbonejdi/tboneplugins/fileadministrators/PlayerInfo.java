package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.ChatColor;
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

    public void gainXP() {
        int gainedXP = (int) Math.floor(Math.random() * 100);
        player.sendMessage(ChatColor.GOLD + "+" + gainedXP + " Mined XP");
        experience += gainedXP;
    }

    public void checkForLevelUp() {
        if (experience >= maxExperience) {
            experience -= maxExperience;
            level += 1;
            maxExperience *= 2;
            player.sendMessage(ChatColor.DARK_RED + "Level up! Lv. " + level);
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, (float) 0.4, 1);
        }
    }

}
