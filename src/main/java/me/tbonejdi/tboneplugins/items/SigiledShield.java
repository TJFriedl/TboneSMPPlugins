package me.tbonejdi.tboneplugins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for SigiledShield item.
 */
public class SigiledShield {

    public static ItemStack sigiledShield;
    public static ArrayList<Material> recipeList = new ArrayList<>();

    /**
     * Registers item to the server.
     */
    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Sigiled Shield");
        List<String> lore = new ArrayList<>();
        lore.add("§7A shield bearing an ancient Nordic engraving.");
        lore.add(ChatColor.GREEN + "Right click to activate ability! (Lv. 15 required)");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        sigiledShield = item;

        // RECIPE
        recipeList.add(Material.AIR);
        recipeList.add(Material.IRON_INGOT);
        recipeList.add(Material.IRON_INGOT);
        recipeList.add(Material.AIR);
        recipeList.add(Material.CHAIN);
        recipeList.add(Material.IRON_INGOT);
        recipeList.add(Material.AIR);
        recipeList.add(Material.CHAIN);
        recipeList.add(Material.AIR);

    }

}
