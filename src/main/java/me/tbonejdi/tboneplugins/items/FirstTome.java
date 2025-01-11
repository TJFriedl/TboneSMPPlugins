package me.tbonejdi.tboneplugins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for FirstTome item.
 */
public class FirstTome {

    public static ItemStack firstTome;

    /**
     * Re-registers the item in the server.
     */
    public static void resetItem() { registerItem(); }

    /**
     * Registers item in the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_PURPLE + "A mysterious book...");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Appears to be a book like no other...");
        lore.add(ChatColor.BLUE + "Right click in hand to activate."); // Next line will be filled on instantiation
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        firstTome = item;
    }

    /**
     * Checks to make sure that the book is meant for the person consuming it.
     * @param book
     * @param player
     * @return
     */
    public static boolean isIntendedUser(ItemStack book, Player player) {

        List<String> lore = book.getItemMeta().getLore();
        if (lore.get(0).equals(null) || (!lore.get(0).equals(firstTome.getItemMeta().getLore().get(0)))) {
            return false;
        }
        else if (lore.get(2).equals(null) || (!lore.get(2).contains(player.getName()))) {
            return false;
        }

        return true;

    }

}
