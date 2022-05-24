package me.tbonejdi.tboneplugins.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectionScreen implements InventoryHolder {

    private Inventory inv;

    public SelectionScreen() {
        inv = Bukkit.createInventory(this, 9, "Selection Screen");
        init();
    }

    private void init() {
        ItemStack item;
        // Left
        for (int i = 0; i < 4; i++) {
            item = createItem("§a§lAccept", Material.LIME_STAINED_GLASS_PANE,
                    Collections.singletonList("§7Accepts the request!"));
            inv.setItem(i, item);
        }

        // Center
        List<String> lore = new ArrayList<>();
        lore.add("§7Please select either the accept");
        lore.add("§7or deny button to make a selection!");
        item = createItem("§b§lMake a Selection", Material.BOOK, lore);
        inv.setItem(inv.firstEmpty(), item);

        // Right
        for (int i = 5; i < 9; i++) {
            item = createItem("§c§lDeny", Material.RED_STAINED_GLASS_PANE,
                    Collections.singletonList("§7Denies the request!"));
            inv.setItem(i, item);
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
