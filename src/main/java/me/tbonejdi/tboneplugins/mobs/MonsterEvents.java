package me.tbonejdi.tboneplugins.mobs;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class MonsterEvents implements Listener {

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {

        /* Has been changed to only suit monsters (for now) */
        if (!(e.getEntity() instanceof Monster)) return;

        int level = new Random().nextInt(20) + 1;

        Mob mob = (Mob) e.getEntity();
        mob.setCustomName("§6Lv. " + level);
        mob.setCustomNameVisible(true);
        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(mob.getAttribute(Attribute.GENERIC_MAX_HEALTH)
                .getBaseValue() + (0.5 * level));

        new BukkitRunnable() {

            @Override
            public void run() {
                if (!mob.isDead()) {
                    boolean canBeSeen = false;
                    for (Entity e : mob.getLocation().getChunk().getEntities()) {
                        if (e instanceof Player && ((Player) e).hasLineOfSight(mob)) {
                            mob.setCustomNameVisible(true);
                            canBeSeen = true;
                            break;
                        }
                    }

                    if (!canBeSeen) {
                        mob.setCustomNameVisible(false);
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Main.mainClassCall, 0L, 20L);

    }

    @EventHandler
    public void onMobDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Mob && e.getEntity().getCustomName() != null &&
                !e.getEntity().getCustomName().contains("Leaping")) {
            Mob mob = (Mob) e.getEntity();
            int health = (int) (mob.getHealth() - e.getDamage());
            if (health < 0) health = 0;

            int level = parseLevel(mob.getCustomName());

            mob.setCustomName("§6Lv. " + level + " §c(" + health + "/"
                    + (int) mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + "❤)");
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
