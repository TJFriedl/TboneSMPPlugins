package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.Main;
import me.tbonejdi.tboneplugins.classes.ClassType;
import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import me.tbonejdi.tboneplugins.fileadministrators.ClassInfo;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.items.*;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class ItemEvents implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
                && e.getItem() != null) {

            if (e.getItem().getItemMeta().equals(DiamondWand.diamondWand.getItemMeta())) {
                p.performCommand("diamonddetect");
                return;
            }

            if (e.getItem().getItemMeta().equals(MagicMirror.magicMirror.getItemMeta())) {
                Location bedSpawnLoc = p.getBedSpawnLocation();

                if (bedSpawnLoc == null) {
                    p.sendMessage(ChatColor.YELLOW + "Doesn't seen like you have a bed spawn set...");
                }
                else {
                    p.teleport(bedSpawnLoc);
                    p.sendMessage(ChatColor.GOLD + "§lWOOSH!");
                    if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
                    e.getItem().setAmount(e.getItem().getAmount()-1);
                }
                return;
            }

            if (e.getItem().getItemMeta().equals(FloatingWand.floatingWand.getItemMeta())) {
                p.sendMessage("§e§lYou have been picked up by the wind, WOOSH!");
                p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 300, 1));
                return;
            }

            if (e.getItem().getItemMeta().equals(MysteriousItem.mysteriousItem.getItemMeta())) {
                p.sendMessage("§2§lZOO WEE MAMA!");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000, 10));
                if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
                e.getItem().setAmount(e.getItem().getAmount()-1);
                return;
            }

            if (e.getItem().getItemMeta().getLore()
                    .equals(KeenBlade.keenBlade.getItemMeta().getLore())) {

                if (!(e.getAction().name().contains("RIGHT_CLICK"))) return;

                ClassInfo classInfo = FileStartupEvents.playerData.get(e.getPlayer().getName()).cInfo;
                PlayerStates playerStates = FileStartupEvents.playerStates.get(e.getPlayer().getName());

                if (playerStates.isChargingCenteredStrike) return;

                if (!(classInfo.getCurrentClass().equals(ClassType.WARRIOR))) {
                    e.getPlayer().sendMessage(ChatColor.RED + "Cannot activate this weapon!");
                    return;
                } else if (classInfo.getClassLvl() < 5) {
                    e.getPlayer().sendMessage(ChatColor.RED + "You need to be at least level 5 to use this ability!");
                    return;
                } else {
                    e.getPlayer().sendMessage(ChatColor.GRAY + "Charging " + ChatColor.GOLD +
                            "Centered Strike" + ChatColor.GRAY + "...");
                    playerStates.isChargingCenteredStrike = true;
                    FileStartupEvents.playerStates.replace(e.getPlayer().getName(), playerStates);
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (FileStartupEvents.playerStates.get(e.getPlayer().getName()).isChargingCenteredStrike == false)
                                return;
                            e.getPlayer().sendMessage(ChatColor.GRAY + "Lowering " + ChatColor.GOLD +
                                    "Centered Strike" + ChatColor.GRAY + " focus...");
                            playerStates.isChargingCenteredStrike = false;
                            FileStartupEvents.playerStates.replace(e.getPlayer().getName(), playerStates);
                        }
                    }.runTaskLater(Main.mainClassCall, 100L);
                    return;
                }
            }

            if (e.getItem().getItemMeta().getLore()
                    .equals(KeenCleaver.keenCleaver.getItemMeta().getLore())) {

                ClassInfo classInfo = FileStartupEvents.playerData.get(e.getPlayer().getName()).cInfo;
                PlayerStates playerStates = FileStartupEvents.playerStates.get(e.getPlayer().getName());

                if (playerStates.isChargingCenteredSweep) return;

                if (!(classInfo.getCurrentClass().equals(ClassType.WARRIOR))) {
                    e.getPlayer().sendMessage(ChatColor.RED + "Cannot activate this weapon!");
                    return;
                } else if (classInfo.getClassLvl() < 5) {
                    e.getPlayer().sendMessage(ChatColor.RED + "You need to be at least level 5 to use this ability!");
                    return;
                } else {
                    e.getPlayer().sendMessage(ChatColor.GRAY + "Charging " + ChatColor.GOLD +
                            "Centered Sweep" + ChatColor.GRAY + "...");
                    playerStates.isChargingCenteredSweep = true;
                    FileStartupEvents.playerStates.replace(e.getPlayer().getName(), playerStates);
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (FileStartupEvents.playerStates.get(e.getPlayer().getName()).isChargingCenteredSweep == false)
                                return;
                            e.getPlayer().sendMessage(ChatColor.GRAY + "Lowering " + ChatColor.GOLD +
                                    "Centered Sweep" + ChatColor.GRAY + " focus...");
                            playerStates.isChargingCenteredSweep = false;
                            FileStartupEvents.playerStates.replace(e.getPlayer().getName(), playerStates);
                        }
                    }.runTaskLater(Main.mainClassCall, 100L);
                    return;
                }

            }
        }
    }


    @EventHandler
    public static void onItemConsume(PlayerItemConsumeEvent e) {
        if (e.getItem().getItemMeta().equals(CrystalFruit.crystalFruit.getItemMeta())) {
            Player player = e.getPlayer();
            Random rand = new Random();
            int randomNum = rand.nextInt(19);

            switch (randomNum) {
                case 0:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                    break;
                case 1:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1));
                    break;
                case 2:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
                    break;
                case 3:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 200, 1));
                    break;
                case 4:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
                    break;
                case 5:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                    break;
                case 6:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
                    break;
                case 7:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 1));
                    break;
                case 8:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 1));
                    break;
                case 9:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 1));
                    break;
                case 10:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 1));
                    break;
                case 11:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 200, 1));
                    break;
                case 12:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 1));
                    break;
                case 13:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 200, 1));
                    break;
                case 14:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
                    break;
                case 15:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 1));
                    break;
                case 16:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 1));
                    break;
                case 17:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 200, 1));
                    break;
                case 18:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 1));
                    break;
            }
        }
    }
    @EventHandler
    public static void onItemTeleport(PlayerTeleportEvent e) {
        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT) &&
                e.getPlayer().getInventory().getItemInMainHand().getItemMeta().equals(CrystalFruit.crystalFruit.getItemMeta())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e) {

        if (!e.getBlock().getType().equals(Material.LARGE_FERN) || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;

        int X = e.getBlock().getX();
        int Y = e.getBlock().getY();
        int Z = e.getBlock().getZ();

        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -2; z <= 2; z++) {

                    Block block = e.getBlock().getWorld().getBlockAt(X+x, Y+y, Z+z);
                    if (block.getType().equals(Material.END_ROD)) {
                        e.setCancelled(true);
                        e.getBlock().setType(Material.AIR);
                        Block belowBlock = e.getBlock().getWorld().getBlockAt(X, Y-1, Z);
                        belowBlock.setType(Material.FERN);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), GreenStuff.greenStuff);
                        return;
                    }

                }
            }
        }
    }
}
