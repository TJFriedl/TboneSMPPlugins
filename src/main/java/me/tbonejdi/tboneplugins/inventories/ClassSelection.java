package me.tbonejdi.tboneplugins.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

/**
 * Custom Inventory GUI responsible for allowing players to choose different in-game classes.
 */
public class ClassSelection implements InventoryHolder {

    private Inventory inv;

    /**
     * Constructor for ClassSelection
     */
    public ClassSelection() {
        inv = Bukkit.createInventory(this, 45, "Select a Class");
        init();
    }

    /**
     * Gets specific inventory invoked by player.
     *
     * @return
     */
    @Override
    public Inventory getInventory() {
        return inv;
    }

    /**
     * Creates the ClassSelection GUI.
     */
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

    /**
     * Helper method for creating special items to be placed in the GUI.
     *
     * @param name
     * @param mat
     * @param lore
     * @return
     */
    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }



}
