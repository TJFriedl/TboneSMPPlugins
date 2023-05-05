package me.tbonejdi.tboneplugins.mobs;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpiderEvents implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Spider && e.getDamager().getCustomName() != null &&
        e.getDamager().getCustomName().contains("Leaping Spider")) {
            Player player = (Player) e.getEntity();
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 0));
        }
    }

    @EventHandler
    public void onSpiderDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof  Spider && e.getEntity().getCustomName() != null &&
        e.getEntity().getCustomName().contains("Leaping Spider")) {

            Spider spider = (Spider) e.getEntity();
            int health = (int) (spider.getHealth() - e.getDamage());
            if (health < 0) health = 0;
            spider.setCustomName(ChatColor.DARK_GRAY + "Leaping Spider §c("+ health + "/100❤)");
        } else if (e.getEntity() instanceof  Spider && e.getEntity().getCustomName() != null &&
        e.getEntity().getCustomName().contains("Lv.")) {

            Spider spider = (Spider) e.getEntity();
            int health = (int) (spider.getHealth() - e.getDamage());
            if (health < 0) health = 0;

            int level = parseLevel(spider.getCustomName());

            spider.setCustomName("§6Lv. " + level + " §c(" + health + "/"
                    + (int) spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + "❤)");

        }
    }

    @EventHandler
    public void castSpiderSpawn(CreatureSpawnEvent e) {

        int seed = new Random().nextInt(9);

        if (!(e.getEntity() instanceof Spider) || e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.COMMAND)) return;

        Spider spider = (Spider) e.getEntity();

        if (seed % 9 == 0) castToLeapingSpider(spider);

    }

    public static Spider castToLeveledSpider(Spider spider) {

        int level = new Random().nextInt(20) + 1;

        spider.setCustomName("§6Lv. " + level);
        spider.setCustomNameVisible(true);
        AttributeInstance health = spider.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        health.setBaseValue(health.getBaseValue() * 1.0 + (0.1 * level));
        AttributeInstance damage = spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        damage.setBaseValue(damage.getBaseValue() * 1.0 + (0.1 * level));

        return spider;

    }

    public static Spider castToLeapingSpider(Spider spider) {

        spider.setCustomName(ChatColor.DARK_GRAY + "Leaping Spider §c(100/100❤)");
        AttributeInstance attribute = spider.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attribute.setBaseValue(100);
        spider.setHealth(100);

        new BukkitRunnable(){
            @Override
            public void run() {
                if(!spider.isDead()) {
                    if (spider.getTarget() == null) {
                        for (Entity entity : spider.getNearbyEntities(10, 10, 10)) {
                            if (entity instanceof Player) {
                                Player player = (Player) entity;
                                if (player.getGameMode().equals(GameMode.SURVIVAL))
                                    spider.setTarget(player);
                            }
                        }
                    } else {
                        LivingEntity target = spider.getTarget();
                        if (target.getLocation().distanceSquared(spider.getLocation()) > 25) {
                            spider.getWorld().playSound(spider.getLocation(), Sound.ENTITY_WITHER_SHOOT, 5, 5);
                            spider.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, spider.getLocation(), 10);
                            spider.setVelocity(target.getLocation().add(0, 2, 0).subtract(spider.getLocation()).toVector().multiply(0.275));
                        }
                    }
                    boolean canBeSeen = false;
                    for (Entity e : spider.getLocation().getChunk().getEntities()) {
                        if (e instanceof Player && ((Player) e).hasLineOfSight(spider)) {
                            spider.setCustomNameVisible(true);
                            canBeSeen = true;
                            break;
                        }
                    }

                    if (!canBeSeen) {
                        spider.setCustomNameVisible(false);
                    }

                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Main.mainClassCall, 0L, 20L);

        return spider;
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
