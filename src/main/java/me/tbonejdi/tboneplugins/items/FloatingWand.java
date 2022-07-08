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

public class FloatingWand {

    public static ItemStack floatingWand;

    public static void init() { createFloatingWand(); }

    private static void createFloatingWand() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName("§eFloating Wand");
        List<String> lore = new ArrayList<>();
        lore.add("§7This wand holds the power to make the user levitate!");
        lore.add("§7Only the most powerful of sorcerers can use this item.");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        floatingWand = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("floating_wand"), item);
        sr.shape(" Z ", " S ", " Z ");
        sr.setIngredient('S', Material.STICK);
        sr.setIngredient('Z', Material.SHULKER_SHELL);
        Bukkit.getServer().addRecipe(sr);

    }
}
