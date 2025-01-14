package me.tbonejdi.tboneplugins.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
 * Class for MagicTable item.
 */
public class MagicTable {

    public static ItemStack magicTable;

    /**
     * Register item to the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.CRAFTING_TABLE,1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName("§4Mag§4§ki§4c Crafting Table");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "§lRare Item");
        lore.add("§8Used in the process of creating magic crafting items.");
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 1, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        magicTable = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("magic_bench"), item);
        sr.shape("BLB", "LWL", "BLB");
        sr.setIngredient('B', Material.BOOKSHELF);
        sr.setIngredient('L', Material.LECTERN);
        sr.setIngredient('W', Material.CRAFTING_TABLE);
        Bukkit.getServer().addRecipe(sr);
    }
}
