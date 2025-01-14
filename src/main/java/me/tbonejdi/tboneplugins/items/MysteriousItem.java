package me.tbonejdi.tboneplugins.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for MysteriousItem item.
 */
public class MysteriousItem {

    public static ItemStack mysteriousItem;

    /**
     * Register item to the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.BROWN_CANDLE,1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_RED + "§lA quite mysterious item");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "§lVery Rare Item");
        lore.add("§8Quick trip to Yodie-land!");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        mysteriousItem = item;

        // Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("mc_smoke"), item);
        sr.shape("  P", " W ", "B  ");
        sr.setIngredient('P', Material.PAPER);
        sr.setIngredient('W', new RecipeChoice.ExactChoice(GreenStuff.greenStuff));
        sr.setIngredient('B', Material.BROWN_CANDLE);
        Bukkit.getServer().addRecipe(sr);
    }
}
