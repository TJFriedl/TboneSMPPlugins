package me.tbonejdi.tboneplugins.mobs;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class PigEvents implements Listener {

    @EventHandler
    public void castPigSpawn(EntitySpawnEvent e) {
        if (!(e.getEntity() instanceof Pig)) return;
        int level = new Random().nextInt(20) + 1;

        Pig pig = (Pig) e.getEntity();

        pig.setCustomName("§6Lv. " + level);
        pig.setCustomNameVisible(true);
        AttributeInstance health = pig.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        health.setBaseValue(health.getBaseValue() * 1.0 + (0.5 * level));
    }

    @EventHandler
    public void onPigDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Pig && e.getEntity().getCustomName() != null) {
            Pig pig = (Pig) e.getEntity();
            int health = (int) (pig.getHealth() - e.getDamage());
            if (health < 0) health = 0;

            int level = parseLevel(pig.getCustomName());

            pig.setCustomName("§6Lv. " + level + " §c(" + health + "/"
            + (int) pig.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + "❤)");
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
