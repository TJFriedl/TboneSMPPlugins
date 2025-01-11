package me.tbonejdi.tboneplugins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for beastAmulet item.
 */
public class BeastAmulet {

    public static ItemStack beastAmulet;

    /**
     * Registers item in server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.NAUTILUS_SHELL, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.RED + "Beast Amulet");
        List<String> lore = new ArrayList<>();
        lore.add("§bAllows for user to store a beast and unleash it on command.");
        lore.add("§bItem is consumed on use.");
        im.setLore(lore);
        item.setItemMeta(im);
        beastAmulet = item;

        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("beast_amulet"), item);
        sr.shape(" D ", "AEA", " R ");
        sr.setIngredient('D', Material.DIAMOND_ORE);
        sr.setIngredient('A', Material.AMETHYST_SHARD);
        sr.setIngredient('E', Material.EGG);
        sr.setIngredient('R', Material.REDSTONE);
    }

}
