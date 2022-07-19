package me.tbonejdi.tboneplugins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FirstMissingPage {

    public static ItemStack firstMissingPage;

    public static void init() { createItem(); }

    public static void createItem() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Woodsmith's Missing Pages");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Hey! I've been looking for these.");
        lore.add(ChatColor.BLUE + "Right click in hand to activate.");
        lore.add((ChatColor.DARK_BLUE + "Book magically created for ")); // TODO
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        firstMissingPage = item;

    }
}
