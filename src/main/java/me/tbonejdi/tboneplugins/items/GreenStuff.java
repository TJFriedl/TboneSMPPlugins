package me.tbonejdi.tboneplugins.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GreenStuff {

    public static ItemStack greenStuff;
    public static ItemStack greenStuffEight;
    public static ItemStack greenStuffStack;

    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.LARGE_FERN);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_GREEN + "Goofy stuff");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "§lRare Item");
        lore.add("§8What is this stuff?");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        greenStuff = item;

        item = new ItemStack(Material.LARGE_FERN);
        im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_GREEN + "Goofy stuff");
        lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "§lRare Item");
        lore.add("§8What is this stuff?");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        greenStuffEight = item;

        item = new ItemStack(Material.LARGE_FERN);
        im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_GREEN + "Goofy stuff");
        lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "§lRare Item");
        lore.add("§8What is this stuff?");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        greenStuffStack = item;

        //Furnace Recipe
        FurnaceRecipe fr = new FurnaceRecipe(NamespacedKey.minecraft("green_smelt"), item,
                Material.LARGE_FERN, 1.0f, 300); // 10 Seconds
        Bukkit.getServer().addRecipe(fr);
    }

}
