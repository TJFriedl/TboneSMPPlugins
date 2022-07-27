package me.tbonejdi.tboneplugins.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MagicMirror {

    public static ItemStack magicMirror;
    public static ItemStack[] recipe;

    public static void init() { createMagicMirror(); }

    private static void createMagicMirror() {
        ItemStack item = new ItemStack(Material.CLOCK, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName("§bMagic Mirror");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "§lRare Item");
        lore.add("§8Consumable, sends you back to home sweet home!");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        magicMirror = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("magic_mirror"), item);
        sr.shape("ORO", "RCR", "ORO");
        sr.setIngredient('O', Material.OBSIDIAN);
        sr.setIngredient('R', Material.REDSTONE_BLOCK);
        sr.setIngredient('C', Material.COMPASS);
        Bukkit.getServer().addRecipe(sr);
    }
}
