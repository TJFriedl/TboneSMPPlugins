package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.fileadministrators.TomesFileWorker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MobDropEvents implements Listener {

    public static ItemStack tutorialBook;
    private static Player player;

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) { //Followed from a YouTube Tutorial
        if (e.getEntity().getKiller() == null) { return; }
        else if (e.getEntity().getKiller() instanceof Player) {
            if (e.getEntity().getKiller() == e.getEntity()) { return; } // You caused it, you goof!
            Player player = e.getEntity().getKiller();
            this.player = player;
        }
        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());
        TomesFileWorker TomesFW = pckg.tfw;
        if (!TomesFW.isBookDiscovered(0)) { // Eventually check if item is already on ground
            ItemStack item = new ItemStack(Material.BOOK, 1);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(ChatColor.DARK_PURPLE + "A mysterious book...");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "This appears to be a book like no other you've seen before...");
            lore.add(ChatColor.BLUE + "Right click in hand to activate");
            im.setLore(lore);
            im.addEnchant(Enchantment.LUCK, 1, false);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(im);
            tutorialBook = item;

            LivingEntity mob = e.getEntity();
            mob.getLocation().getWorld().dropItemNaturally(mob.getLocation(), item);
            e.getEntity().getKiller().sendMessage(ChatColor.YELLOW + "It seems the creature has dropped a strange book?");
            player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1, -1);

        }

        FileStartupEvents.playerData.replace(player.getName(), pckg);

    }

}
