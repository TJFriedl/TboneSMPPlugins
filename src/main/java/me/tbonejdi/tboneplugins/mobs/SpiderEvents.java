package me.tbonejdi.tboneplugins.mobs;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class SpiderEvents implements Listener {

    public static void spawnLeapingSpider(Location location) {

        Spider spider = location.getWorld().spawn(location, Spider.class);
        spider.setCustomName(ChatColor.DARK_GRAY + "Leaping Spider §c(100/100❤)");
        spider.setCustomNameVisible(true);
        Attributable spiderAt = spider;
        AttributeInstance attribute = spiderAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
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
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Main.mainClassCall, 0L, 20L);
    }

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
            spider.setCustomName(ChatColor.DARK_GRAY + "Leaping Spider §c("+ health + "/100❤)");
        }
    }

    @EventHandler
    public void castSpiderSpawn(EntitySpawnEvent e) {

        Random randGen = new Random();
        int seed = randGen.nextInt(9);

        if (!(e.getEntity() instanceof Spider)) return;
        else if (seed % 9 != 0) return;

        e.setCancelled(true);
        spawnLeapingSpider(e.getLocation());
    }
}
