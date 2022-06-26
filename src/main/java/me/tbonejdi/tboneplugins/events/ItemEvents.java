package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.items.CrystalFruit;
import me.tbonejdi.tboneplugins.items.DiamondWand;
import me.tbonejdi.tboneplugins.items.MagicMirror;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class ItemEvents implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
                && e.getItem() != null) {

            if (e.getItem().getItemMeta().equals(DiamondWand.diamondWand.getItemMeta())) {
                p.performCommand("diamonddetect");
            }

            if (e.getItem().getItemMeta().equals(MagicMirror.magicMirror.getItemMeta())) {
                Location bedSpawnLoc = p.getBedSpawnLocation();

                if (bedSpawnLoc == null) {
                    p.sendMessage(ChatColor.YELLOW + "Doesn't seen like you have a bed spawn set...");
                }
                else {
                    p.teleport(bedSpawnLoc);
                    p.sendMessage(ChatColor.GOLD + "§lWOOSH!");
                    e.getItem().setAmount(0);
                }
            }
        }
    }

    @EventHandler
    public static void onItemConsume(PlayerItemConsumeEvent e, PlayerTeleportEvent e2) {
        if (e.getItem().getItemMeta().equals(CrystalFruit.crystalFruit.getItemMeta())) {
            e2.setCancelled(true);
            Player player = e.getPlayer();
            Random rand = new Random();
            int randomNum = rand.nextInt(30);

            switch (randomNum) {
                case 0:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                case 1:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1));
                case 2:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
                case 3:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 200, 1));
                case 4:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
                case 5:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                case 6:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
                case 7:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 1));
                case 8:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 1));
                case 9:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 1));
                case 10:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 1));
                case 11:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 200, 1));
                case 12:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 1));
                case 13:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 200, 1));
                case 14:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
                case 15:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 1));
                case 16:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 1));
                case 17:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200, 1));
                case 18:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 1));
                default:
                    return;
            }
        }
    }
}
