package me.tbonejdi.tboneplugins.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KeenCleaver {

    public static ItemStack keenCleaver;
    public static ArrayList<Material> recipeList = new ArrayList<>();

    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Keen Cleaver");
        List<String> lore = new ArrayList<>();
        lore.add("§7A 50kg hatchet. Seems very dangerous.");
        lore.add(ChatColor.GREEN + "Right click to activate ability! (Lv. 5 required)");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        keenCleaver = item;

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
