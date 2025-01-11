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
 * Custom Inventory GUI responsible for allowing players to choose a specific amount of a magic item.
 */
public class MagicItemAmount implements InventoryHolder {

    private Inventory inv;

    /**
     * Constructor for MagicItemAmount
     */
    public MagicItemAmount() {
        inv = Bukkit.createInventory(this, 9, "Magic item amount");
        init();
    }

    /**
     * Gets specific inventory invoked by player.
     * @return
     */
    @Override
    public Inventory getInventory() { return inv; }

    /**
     * Creates the MagicItemAmount GUI.
     */
    private void init() {
        ItemStack item = createItem(Material.GREEN_WOOL, Collections.singletonList("§2§lOne"), 1);
        ItemStack item2 = createItem(Material.GREEN_WOOL, Collections.singletonList("§2§lEight"), 8);
        ItemStack item3 = createItem(Material.GREEN_WOOL, Collections.singletonList("§2§lSixty-four"), 64);
        ItemStack item4 = createItem(Material.BARRIER, Collections.singletonList("§4§lEXIT"), 1);

        inv.setItem(0, item);
        inv.setItem(2, item2);
        inv.setItem(4, item3);
        inv.setItem(8, item4);

    }

    /**
     * Helper method for creating special items to be placed in the GUI.
     * @param material
     * @param lore
     * @param amount
     * @return
     */
    private ItemStack createItem(Material material, List<String> lore, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
