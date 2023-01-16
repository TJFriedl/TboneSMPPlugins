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

public class CrystalFruit {

    public static ItemStack crystalFruit;
    public static ItemStack crystalFruitEight;
    public static ItemStack crystalFruitStack;

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

        item = new ItemStack(Material.CHORUS_FRUIT, 8);
        im = item.getItemMeta();
        im.setDisplayName("§bCrystal Fruit");
        lore = new ArrayList<>();
        lore.add("§bAllows user to obtain a potion effect at random.");
        lore.add("§bEffect lasts §d20 §bseconds.");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        crystalFruitEight = item;

        item = new ItemStack(Material.CHORUS_FRUIT, 64);
        im = item.getItemMeta();
        im.setDisplayName("§bCrystal Fruit");
        lore = new ArrayList<>();
        lore.add("§bAllows user to obtain a potion effect at random.");
        lore.add("§bEffect lasts §d20 §bseconds.");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        crystalFruitStack = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("crys_fruit"), item);
        sr.shape(" G ", "GFG", " G ");
        sr.setIngredient('G', Material.GOLD_INGOT);
        sr.setIngredient('F', Material.CHORUS_FRUIT);
        Bukkit.getServer().addRecipe(sr);
    }
}
