package me.tbonejdi.tboneplugins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for KeenBlade item.
 */
public class KeenBlade {

    public static ItemStack keenBlade;
    public static ArrayList<Material> recipeList = new ArrayList<>();

    /**
     * Register item to the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Keen Blade");
        List<String> lore = new ArrayList<>();
        lore.add("§7Wielded by only the most powerful of warriors");
        lore.add(ChatColor.GREEN + "Right click to activate ability! (Lv. 5 required)");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        keenBlade = item;

        // RECIPE
        recipeList.add(Material.AIR);
        recipeList.add(Material.IRON_INGOT);
        recipeList.add(Material.AIR);
        recipeList.add(Material.AIR);
        recipeList.add(Material.IRON_INGOT);
        recipeList.add(Material.AIR);
        recipeList.add(Material.AIR);
        recipeList.add(Material.CHAIN);
        recipeList.add(Material.AIR);
    }

}
