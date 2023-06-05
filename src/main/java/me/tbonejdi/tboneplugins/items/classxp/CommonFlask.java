package me.tbonejdi.tboneplugins.items.classxp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommonFlask {

    public static ItemStack commonFlask;

    protected static void registerItem() {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Common " + ChatColor.WHITE + "Class Exp Flask");
        List<String> lore = new ArrayList<>();
        lore.add("Gives player between 50-1000 exp upon consumption");
        lore.add("§bItem is consumed on use (right click).");
        im.setLore(lore);
        item.setItemMeta(im);
        commonFlask = item;
    }
}
