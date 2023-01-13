package me.tbonejdi.tboneplugins.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class MagicItemAmount implements InventoryHolder {

    private Inventory inv;

    private ItemStack customItem;

    public MagicItemAmount(ItemStack item) {
        inv = Bukkit.createInventory(this, 9, "Magic item amount");
        customItem = item;
        init();
    }

    @Override
    public Inventory getInventory() { return inv; }

    public ItemStack getCustomItem() { return customItem; }

    private void init() {
        ItemStack item = createItem(Material.GREEN_WOOL, Collections.singletonList("§2§l1"), 1);
        ItemStack item2 = createItem(Material.GREEN_WOOL, Collections.singletonList("§2§l8"), 8);
        ItemStack item3 = createItem(Material.GREEN_WOOL, Collections.singletonList("§2§l64"), 64);
        ItemStack item4 = createItem(Material.BARRIER, Collections.singletonList("§4§lEXIT"), 1);

        inv.setItem(0, item);
        inv.setItem(2, item2);
        inv.setItem(4, item3);
        inv.setItem(8, item4);

    }

    private ItemStack createItem(Material material, List<String> lore, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
