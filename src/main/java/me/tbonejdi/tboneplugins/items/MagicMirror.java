package me.tbonejdi.tboneplugins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MagicMirror {

    public static ItemStack magicMirror;
    public static ArrayList<Material> recipeList = new ArrayList<>();

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

        // RECIPE
        recipeList.add(Material.OBSIDIAN);
        recipeList.add(Material.REDSTONE_BLOCK);
        recipeList.add(Material.OBSIDIAN);
        recipeList.add(Material.REDSTONE_BLOCK);
        recipeList.add(Material.COMPASS);
        recipeList.add(Material.REDSTONE_BLOCK);
        recipeList.add(Material.OBSIDIAN);
        recipeList.add(Material.REDSTONE_BLOCK);
        recipeList.add(Material.OBSIDIAN);
    }
}
