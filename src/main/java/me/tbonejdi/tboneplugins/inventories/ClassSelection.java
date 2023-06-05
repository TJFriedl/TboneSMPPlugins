package me.tbonejdi.tboneplugins.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class ClassSelection implements InventoryHolder {

    private Inventory inv;

    public ClassSelection() {
        inv = Bukkit.createInventory(this, 45, "Select a Class");
        init();
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    private void init() {
        ItemStack item;
        item = createItem(null, Material.CHAIN, null);
        inv.setItem(0, item);
        inv.setItem(14, item);
        inv.setItem(28, item);
        inv.setItem(42, item);

        item = createItem("§6§lWarrior", Material.IRON_SWORD,
                Collections.singletonList("§7Select the 'Warrior' Class!"));
        inv.setItem(11, item);

        item = createItem("§4§lExit", Material.BARRIER, null);
        inv.setItem(40, item);
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }



}
