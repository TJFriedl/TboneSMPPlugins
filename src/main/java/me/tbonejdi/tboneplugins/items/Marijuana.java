package me.tbonejdi.tboneplugins.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Marijuana {

    public static ItemStack marijuana;

    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.LARGE_FERN);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_GREEN + "Weed :)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "§lRare Item");
        lore.add("§8Used for having a fun time!");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        marijuana = item;

        //Furnace Recipe
        FurnaceRecipe fr = new FurnaceRecipe(NamespacedKey.minecraft("weed_smelt"), item,
                Material.LARGE_FERN, 1.0f, 300); // 10 Seconds
        Bukkit.getServer().addRecipe(fr);
    }

}
