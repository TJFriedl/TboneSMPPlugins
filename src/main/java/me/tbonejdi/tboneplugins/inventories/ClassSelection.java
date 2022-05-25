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
        inv = Bukkit.createInventory(this, 9, "Select a Class");
        init();
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    private void init() {
        ItemStack item;
        item = createItem("§6§lWarrior", Material.IRON_SWORD,
                Collections.singletonList("§7Select the 'Warrior' Class!"));
        inv.setItem(0, item);

        item = createItem("§4§lExit", Material.RED_STAINED_GLASS_PANE,
                Collections.singletonList("§4§lEXIT"));
        inv.setItem(8, item);
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }



}
