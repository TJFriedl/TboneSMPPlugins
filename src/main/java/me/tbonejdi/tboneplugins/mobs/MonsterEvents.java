package me.tbonejdi.tboneplugins.mobs;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;


public class MonsterEvents implements Listener {

    /**
     * Event tracks when a mob spawns in game
     *
     * @param e
     */
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

        // Run a thread that prints level and health bar if visible to player.
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

    /**
     * Event tracks when a mob takes damage in game.
     * @param e
     */
    @EventHandler
    public void onMobDamage(EntityDamageEvent e) {

        // Checks for an instance of a "Leaping" mob. In this case, a spider.
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

    /**
     * Event tracks when a mob dies in game.
     *
     * @param e
     */
    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {

        if (e.getEntity() instanceof Mob && e.getEntity().getCustomName() != null) {
            e.getEntity().setCustomName(null);
            e.getEntity().setCustomNameVisible(false);
        }

    }

    /**
     * Helper method used to parse the level of a mob the has been spawned.
     *
     * @param customName
     * @return
     */
    private int parseLevel(String customName) {
        String level = "";
        for (int i = customName.lastIndexOf('.') + 2; i < customName.length(); i++) {
            if (customName.charAt(i) == ' ') break;
            level += customName.charAt(i);
        }

        return Integer.parseInt(level);
    }

}
