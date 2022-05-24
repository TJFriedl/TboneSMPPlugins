package me.tbonejdi.tboneplugins.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class BookSelection implements InventoryHolder {

    private Inventory inv;

    public BookSelection() {
        inv = Bukkit.createInventory(this, 9, ChatColor.BOLD +
                "TOMES KNOWLEDGE");
        init();
    }

    private void init() {
        ItemStack item;
        item = createItem("Book 1: Tutorial Book", Material.WRITTEN_BOOK,
                Collections.singletonList("Opens an obtained book!"));
        inv.setItem(0, item);

        ItemStack item2;
        item2 = createItem(ChatColor.RED + "Not Explored", Material.BOOK, Collections.singletonList(""));
        for (int i = 1; i < 9; i++) {
            inv.setItem(i, item2);
        }
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
