package me.tbonejdi.tboneplugins.classes;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public abstract class ClassFile {

    /**
     * Resets the player's in-game stats back to normal, usually before a class change.
     * @param player
     */
    public static void resetPlayer(Player player) {
        player.getActivePotionEffects().clear();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0); // Automatically cuts health
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);

    }




}
