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
 * Class for SecondTomePage item
 */
public class SecondTomePage {

    public static ItemStack secondTomePage;

    /**
     * Re-register item to the server.
     */
    public static void resetItem() { registerItem(); }

    /**
     * Register item to the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Woodsmith's Missing Pages");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Hey! I've been looking for these.");
        lore.add(ChatColor.BLUE + "Right click in hand to activate.");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        secondTomePage = item;
    }

    /**
     * Checks to make sure that book being consumed is intended for the player
     * trying to consume it.
     *
     * @param page
     * @param player
     * @return
     */
    public static boolean isIntendedUser(ItemStack page, Player player) {

        List<String> lore = page.getItemMeta().getLore();
        if (lore.get(0).equals(null) || (!lore.get(0).equals(secondTomePage.getItemMeta().getLore().get(0)))) {
            return false;
        }
        else if (lore.get(2).equals(null) || (!lore.get(2).contains(player.getName()))) {
            return false;
        }

        return true;

    }
}
