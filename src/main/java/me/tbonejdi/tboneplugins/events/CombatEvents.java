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
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class CombatEvents implements Listener {

    @EventHandler
    public static void onAttackEvent(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;

        Player damager = (Player) e.getDamager();

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
            Vector direction = damager.getLocation().getDirection().normalize();
            Double damage = e.getDamage();

            for (Entity entity : nearbyEntities) {
                if (entity instanceof Mob) nearbyMobs.add((Mob) entity);
            }

            for (LivingEntity mob : nearbyMobs) {

                Vector knockback = direction.multiply(2.0);
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

        if (states.isChargingCenteredStrike) {
            player.sendMessage(ChatColor.GOLD + "Centered Strike " + ChatColor.GRAY + "focus lost...");
            states.isChargingCenteredStrike = false;
            FileStartupEvents.playerStates.replace(player.getName(), states);
        }
    }

}
