package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.items.FirstTome;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MobDropEvents implements Listener {

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null || (!(e.getEntity().getKiller() instanceof Player))) { return; }
        else if (e.getEntity().getKiller() == e.getEntity()) { return; }

        Player player = e.getEntity().getKiller();
        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());

        if (pckg.pInfo.getLevel() < 1) { return; } // Needs at least player lv. 1
        if (!pckg.tfw.isBookDiscovered(0)) { // Eventually check if item is already on ground

            FirstTome.resetItem();
            ItemStack item = FirstTome.firstTome;
            ItemMeta im = item.getItemMeta();
            List<String> lore = im.getLore();
            lore.add(ChatColor.DARK_BLUE + "Book magically created for " + ChatColor.GOLD + player.getName());
            im.setLore(lore);
            item.setItemMeta(im);

            LivingEntity mob = e.getEntity();
            mob.getLocation().getWorld().dropItemNaturally(mob.getLocation(), item);
            e.getEntity().getKiller().sendMessage(ChatColor.YELLOW + "It seems the creature has dropped a strange book?");
            player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1, -1);

        }
        FileStartupEvents.playerData.replace(player.getName(), pckg);
    }
}
