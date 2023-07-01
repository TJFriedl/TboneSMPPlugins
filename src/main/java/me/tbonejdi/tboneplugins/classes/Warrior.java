package me.tbonejdi.tboneplugins.classes;

import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import me.tbonejdi.tboneplugins.effects.BlockEffects;
import me.tbonejdi.tboneplugins.fileadministrators.ClassInfo;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.effects.ParticleEffects;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Warrior extends ClassFile implements Listener {

    public static void setClass(Player player) {
        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());

        pckg.cInfo.setCurrentClass(ClassType.WARRIOR);
        setBuffs(pckg.cInfo);

        FileStartupEvents.playerData.replace(player.getName(), pckg);
    }

    public static void setBuffs(ClassInfo classInfo) {
        Player player = classInfo.player;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0 + healthChanges(classInfo.currentLvl));
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(armorChanges(classInfo.currentLvl));
    }

    /*
    FIXME: Add level parameter from cInfo somehow
     */
    private static double healthChanges(int level) {
        if (level <= 0) { return 0.0; }
        else if (level > 0 && level < 4) { return 1.0; }
        else if (level >= 4 && level < 9) { return 2.0; }
        else if (level >= 9 && level < 15) { return 3.0; }
        else if (level >= 15 && level < 20) { return 4.0; }
        else return 5.0;
    }

    /*
    FIXME: Add level parameter from cInfo somehow
     */
    private static double armorChanges(int level) {
        if (level <= 2) { return 0.0; }
        else if (level >= 3 && level < 7) { return 2.0; }
        else if (level >= 7 && level < 13) { return 3.0; }
        else if (level >= 14 && level < 18) { return 3.0; }
        else return 4.0;
    }

    @EventHandler
    public void onGroundPound(PlayerMoveEvent e) {
        PlayerStates playerStates = FileStartupEvents.playerStates.get(e.getPlayer().getName());
        boolean isJumping = false;

        if (e.getTo().getY() > e.getFrom().getY() && !(e.getPlayer().isFlying())) isJumping = true;

        if (!(isJumping) || !(playerStates.sigiledShieldisCharged) || playerStates.isGroundPounding)
            return;

        //Handle Ground Pound Situation
        playerStates.isGroundPounding = true;
        Player player = e.getPlayer();
        Vector velo = e.getPlayer().getVelocity();
        velo.setY(2.0);
        player.setVelocity(velo);

        player.playSound(player, Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1, 1);

        playerStates.isChargingSigiledShield = false;
        FileStartupEvents.playerStates.replace(e.getPlayer().getName(), playerStates);

    }

    @EventHandler
    public void onGroundPoundLanding(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;


        Player player = (Player) e.getEntity();
        PlayerStates playerStates = FileStartupEvents.playerStates.get(player.getName());
        if (!(playerStates.sigiledShieldisCharged) ||
                !(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))) return;
        player.playSound(player, Sound.ENTITY_WITHER_SPAWN, 1, 1);
        ParticleEffects.createHorizontalRing(player, Particle.CLOUD, 3.0, 50);
        e.setCancelled(true);
        ArrayList<Mob> nearbyMobs = new ArrayList<>();
        for (Entity entity : player.getNearbyEntities(5.0, 5.0, 5.0)) {
            if (entity instanceof Mob) nearbyMobs.add((Mob) entity);
        }

        for (Mob mob : nearbyMobs) {
            Vector sourceLocation = player.getLocation().toVector();
            Vector targetLocation = mob.getLocation().toVector();
            mob.damage(5.0 / player.getLocation().distance(mob.getLocation()));
            Vector knockback = targetLocation.subtract(sourceLocation).normalize().multiply(2.0);
            knockback.setY(1);
            mob.setVelocity(knockback);
        }

        BlockEffects.createGroundPoundExplosion(player, 5);

        playerStates.isGroundPounding = false;
        playerStates.sigiledShieldisCharged = false;
        FileStartupEvents.playerStates.replace(player.getName(), playerStates);

    }

}
