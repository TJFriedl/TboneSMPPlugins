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

/**
 * Custom Inventory GUI responsible for allowing players to choose a magic item.
 */
public class MagicItemsList implements InventoryHolder {

    private Inventory inv;

    /**
     * Constructor for MagicItemsList
     */
    public MagicItemsList() {
        inv = Bukkit.createInventory(this, 18, "Magic items");
        init();
    }

    /**
     * Gets specific inventory invoked by player.
     *
     * @return
     */
    @Override
    public Inventory getInventory() { return inv; }

    //TODO: Error is being thrown below
    private void init() {
        for (int i = 0; i < ItemHandler.magicItems.size(); i++) {
            inv.setItem(i, ItemHandler.magicItems.get(i));
        }
        ItemStack item = createItem("§4§lExit", Material.BARRIER,
                Collections.singletonList("§4§lEXIT"));
        inv.setItem(inv.getSize()-1, item);
    }

    /**
     * Helper method for creating special items to be placed in the GUI
     *
     * @param name
     * @param mat
     * @param lore
     * @return
     */
    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
