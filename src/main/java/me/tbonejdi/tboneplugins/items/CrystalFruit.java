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
 * Class for CrystalFruit item.
 */
public class CrystalFruit {

    public static ItemStack crystalFruit;

    /**
     * Registers item in the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.CHORUS_FRUIT, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName("§bCrystal Fruit");
        List<String> lore = new ArrayList<>();
        lore.add("§bAllows user to obtain a potion effect at random.");
        lore.add("§bEffect lasts §d20 §bseconds.");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        crystalFruit = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("crys_fruit"), item);
        sr.shape(" G ", "GFG", " G ");
        sr.setIngredient('G', Material.GOLD_INGOT);
        sr.setIngredient('F', Material.CHORUS_FRUIT);
        Bukkit.getServer().addRecipe(sr);
    }
}
