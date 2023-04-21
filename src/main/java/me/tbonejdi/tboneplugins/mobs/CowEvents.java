package me.tbonejdi.tboneplugins.mobs;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class CowEvents implements Listener {

    @EventHandler
    public void castCowSpawn(EntitySpawnEvent e) {
        if (!(e.getEntity() instanceof Cow)) return;
        int level = new Random().nextInt(20) + 1;

        Cow cow = (Cow) e.getEntity();

        cow.setCustomName("Cow | §6Lv. " + level);
        cow.setCustomNameVisible(true);
        Attributable cowAt = cow;
        AttributeInstance health = cowAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        health.setBaseValue(health.getBaseValue() * 1.0 + (0.5 * level));
        AttributeInstance damage = cowAt.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        damage.setBaseValue(damage.getBaseValue() * 1.0 + (0.5 * level));
    }

    @EventHandler
    public void onCowDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Cow && e.getEntity().getCustomName() != null) {
            Cow cow = (Cow) e.getEntity();
            int health = (int) (cow.getHealth() - e.getDamage());
            int level = parseLevel(cow.getCustomName());

            cow.setCustomName("Cow | §6Lv. " + level + " §c(" + health + "/"
                    + (int) cow.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + "❤)");
        }

    }

    private int parseLevel(String customName) {
        String level = "";
        for (int i = customName.lastIndexOf('.') + 2; i < customName.length(); i++) {
            if (customName.charAt(i) == ' ') break;
            level += customName.charAt(i);
        }

        return Integer.parseInt(level);
    }

}
