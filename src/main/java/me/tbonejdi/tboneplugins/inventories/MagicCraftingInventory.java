package me.tbonejdi.tboneplugins.inventories;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MagicCraftingInventory implements InventoryHolder {

    private Inventory inv;

    public MagicCraftingInventory() {
        inv = Bukkit.createInventory(this, InventoryType.WORKBENCH,
                "§6§kiii§5Magic Crafting Table§6§kiii");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
