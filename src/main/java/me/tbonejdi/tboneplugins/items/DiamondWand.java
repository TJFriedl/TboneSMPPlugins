package me.tbonejdi.tboneplugins.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for DiamondWand item.
 */
public class DiamondWand {

    public static ItemStack diamondWand;

    /**
     * Registers item in the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName("§bDiamond Detecting Wand");
        List<String> lore = new ArrayList<>();
        lore.add("§7With this powerful wand, we can detect nearby diamonds!");
        lore.add("§7Only the most powerful of sorcerers can use this item.");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        diamondWand = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("dd_wand"), item);
        sr.shape("R  ", " D ", "  S");
        sr.setIngredient('R', Material.REDSTONE_BLOCK);
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);

    }
}
