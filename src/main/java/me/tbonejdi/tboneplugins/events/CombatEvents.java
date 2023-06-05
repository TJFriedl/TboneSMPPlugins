package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.items.KeenBlade;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class CombatEvents implements Listener {

    @EventHandler
    public static void onAttackEvent(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;

        Player damager = (Player) e.getDamager();

        if (damager.getInventory().getItemInMainHand().getItemMeta().getLore()
                .equals(KeenBlade.keenBlade.getItemMeta().getLore())) {

            PlayerStates state = FileStartupEvents.playerStates.get(damager.getName());
            if (state.isChargingCenteredStrike == false) return;

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
