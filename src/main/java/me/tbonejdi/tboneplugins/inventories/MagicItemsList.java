package me.tbonejdi.tboneplugins.inventories;

import me.tbonejdi.tboneplugins.items.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

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
        inv.setItem(8, new ItemStack(Material.BARRIER));
    }

}
