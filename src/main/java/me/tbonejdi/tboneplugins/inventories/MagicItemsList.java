package me.tbonejdi.tboneplugins.inventories;

import me.tbonejdi.tboneplugins.items.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class MagicItemsList implements InventoryHolder {

    private Inventory inv;

    public MagicItemsList() {
        inv = Bukkit.createInventory(this, 9, "Magic items");
        init();
    }

    @Override
    public Inventory getInventory() { return inv; }

    //TODO: Logic needs to be polished, barrier needs "exit" title
    private void init() {
        for (int i = 0; i < ItemHandler.magicItems.size(); i++) {
            inv.setItem(i, ItemHandler.magicItems.get(i));
        }
        ItemStack item = createItem("§4§lExit", Material.RED_STAINED_GLASS_PANE,
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
