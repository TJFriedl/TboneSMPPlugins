package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftingEvents implements Listener {

    public static ItemStack magicCraftingBook;

    @EventHandler
    public void onItemCraft(CraftItemEvent e) {
        // First thing we need to do is find the location of the crafting table used.
        Player player = (Player) e.getWhoClicked();
        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());

        if ((pckg.tfw.isBookDiscovered(1)) ||  pckg.pInfo.getLevel() < 2) { return; }

        Location loc = player.getLocation();
        Location craftingTable;
        for (int x=-5; x<=5; x++) {
            for (int y=-5; y<=5; y++) {
                for (int z=-5; z<=5; z++) {
                    Material block = player.getWorld().getBlockAt(loc.getBlockX()+x, loc.getBlockY()+y,
                            loc.getBlockZ()+z).getType();
                    if (block == Material.CRAFTING_TABLE) {
                        craftingTable = new Location(player.getWorld(), loc.getBlockX()+x, loc.getBlockY()+y,
                                loc.getBlockZ()+z);
                        ItemStack item = new ItemStack(Material.BOOK, 1);
                        ItemMeta im = item.getItemMeta();
                        im.setDisplayName(ChatColor.DARK_BLUE + "The handyman's chronicle.");
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.GRAY + "Has a well-worm cover, smells of cedarwood.");
                        lore.add(ChatColor.BLUE + "Right click in hand to activate!");
                        lore.add(ChatColor.DARK_BLUE + "Book magically created for " + ChatColor.GOLD + player.getName());
                        im.setLore(lore);
                        im.addEnchant(Enchantment.LUCK, 1,false);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(im);
                        magicCraftingBook = item;
                        craftingTable.getWorld().dropItemNaturally(craftingTable, item);
                        player.closeInventory();
                        player.sendMessage("§7Wha- ? This was not what I had tried crafting...");
                        return;
                    }
                }
            }
        }
    }
}
