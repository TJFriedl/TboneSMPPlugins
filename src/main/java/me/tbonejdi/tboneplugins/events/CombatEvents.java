package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.items.KeenBlade;
import me.tbonejdi.tboneplugins.items.KeenCleaver;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class CombatEvents implements Listener {

    @EventHandler
    public static void onAttackEvent(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;

        Player damager = (Player) e.getDamager();

        // Return prematurely - no operation needed.
        if (damager.getInventory().getItemInMainHand().getItemMeta() == null)
            return;
        else if (!(damager.getInventory().getItemInMainHand().getItemMeta().hasLore()))
            return;

        if (damager.getInventory().getItemInMainHand().getItemMeta().getLore()
                .equals(KeenBlade.keenBlade.getItemMeta().getLore())) {

            PlayerStates state = FileStartupEvents.playerStates.get(damager.getName());
            if (!state.isChargingCenteredStrike) return;

            damager.sendMessage(ChatColor.GOLD + "Centered Strike " + ChatColor.GRAY + "activated!");

            // Do some cool stuff for activating this event!
            e.getEntity().getWorld().spawnParticle(Particle.REDSTONE, e.getEntity().getLocation(), 30, 0.2,
                    0.2, 0.2, 0, new Particle.DustOptions(org.bukkit.Color.RED, 1));
            e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.BLOCK_ANVIL_HIT,
                    1, 1);
            state.isChargingCenteredStrike = false;
            FileStartupEvents.playerStates.replace(damager.getName(), state);
            return;
        }

        if (damager.getInventory().getItemInMainHand().getItemMeta().getLore()
                .equals(KeenCleaver.keenCleaver.getItemMeta().getLore())) {

            PlayerStates state = FileStartupEvents.playerStates.get(damager.getName());
            if (!state.isChargingCenteredSweep) return;

            damager.sendMessage(ChatColor.GOLD + "Centered Sweep " + ChatColor.GRAY + "activate!");

            //TODO: Work on getting something cool for a centered sweep
            ArrayList<Entity> nearbyEntities = (ArrayList<Entity>) damager.getWorld().getNearbyEntities
                    (damager.getLocation(), 3.0, 3.0, 3.0);
            ArrayList<Mob> nearbyMobs = new ArrayList<>();
            Double damage = e.getDamage();

            for (Entity entity : nearbyEntities) {
                if (entity instanceof Mob) nearbyMobs.add((Mob) entity);
            }

            for (LivingEntity mob : nearbyMobs) {
                Vector sourceLocation = damager.getLocation().toVector();
                Vector targetLocation = mob.getLocation().toVector();
                Vector knockback = targetLocation.subtract(sourceLocation).normalize().multiply(2.0);
                mob.setVelocity(knockback);
                mob.damage(damage);

            }

            // End process of centered sweep
            state.isChargingCenteredSweep = false;
            FileStartupEvents.playerStates.replace(damager.getName(), state);
            return;

        }
    }

    @EventHandler
    public static void onTakeDamageEvent(EntityDamageEvent e) {
        if (!((e.getEntity()) instanceof Player)) return;

        Player player = (Player) e.getEntity();
        PlayerStates states = FileStartupEvents.playerStates.get(player.getName());

        // TODO: Test this. This is testing for a current mob feature.
        if (((player.getHealth() - e.getFinalDamage()) <= 0) && (player.getKiller() instanceof Mob)) {
            Mob mob = (Mob) player.getKiller();
            mob.setCustomName(null);
            mob.setCustomNameVisible(false);
        }

        if (states.isChargingCenteredStrike) {
            player.sendMessage(ChatColor.GOLD + "Centered Strike " + ChatColor.GRAY + "focus lost...");
            states.isChargingCenteredStrike = false;
        }

        if (states.isChargingCenteredSweep) {
            player.sendMessage(ChatColor.GOLD + "Centered Sweep" + ChatColor.GRAY + "focus lost...");
            states.isChargingCenteredSweep = false;
        }

        if (states.isChargingSigiledShield && !(player.isBlocking())) {
            player.sendMessage(ChatColor.GOLD + "Ground Pound " + ChatColor.GRAY + "broken...");
            states.isChargingSigiledShield = false;
        }

        FileStartupEvents.playerStates.replace(player.getName(), states);
    }

//    @EventHandler
//    public void onPlayerDeath(PlayerDeathEvent e) {
//        if (!(e.getEntity().getKiller() instanceof Mob)) return;
//
//        if (e.getEntity().getKiller() instanceof Mob && (e.getEntity().getKiller().getCustomName() != null)) {
//            e.getEntity().getKiller().setCustomName(null);
//            e.getEntity().getKiller().setCustomNameVisible(false);
//        }
//    }

}
