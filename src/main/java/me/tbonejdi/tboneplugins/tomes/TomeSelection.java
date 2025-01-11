package me.tbonejdi.tboneplugins.tomes;

import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

/**
 * Custom Inventory GUI for selecting a tome in game.
 */
public class TomeSelection implements InventoryHolder {

    private Inventory inv;
    private Player player;
    public static ItemStack tutorialBook;
    public static ItemStack magicCraftingBook;

    /**
     * Constructor for TomeSelection. Takes player as an argument.
     *
     * @param player
     */
    public TomeSelection(Player player) {
        this.player = player;
        inv = Bukkit.createInventory(this, 36, ChatColor.BOLD +
                "TOMES KNOWLEDGE");
        init();
    }

    /**
     * Initializes the tome GUI in game.
     */
    private void init() {
        ItemStack item = createItem(" ", Material.GRAY_STAINED_GLASS_PANE, Collections.singletonList(""));
        for (int i = 0; i < 36; i++) {
            inv.setItem(i, item);
        }

        ItemStack item2;
        item2 = createItem(ChatColor.DARK_GRAY + "Not Unlocked Yet", Material.BOOK, Collections.singletonList(""));
        inv.setItem(2, item2);
        inv.setItem(7, item2);
        inv.setItem(10, item2);
        inv.setItem(14, item2);
        inv.setItem(21, item2);
        inv.setItem(26, item2);

        ItemStack item3 = createItem("§4§lEXIT TOMES LIBRARY", Material.BARRIER, Collections.singletonList(""));
        inv.setItem(31, item3);

        ItemStack item4 = createItem(" ", Material.VINE, Collections.singletonList(""));
        inv.setItem(3, item4);
        inv.setItem(12, item4);
        inv.setItem(18, item4);
        inv.setItem(24, item4);
        inv.setItem(28, item4);
        inv.setItem(34, item4);

        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());

        /*
        LIST OF TOMES NEEDED TO UPDATE THE INVENTORY -> ONCE FOUND
         */
        ItemStack item5 = createItem("Book 1: Tutorial Book", Material.WRITTEN_BOOK,
                Collections.singletonList(ChatColor.GREEN + "§lCommon: §fOpens tutorial tome"));
        tutorialBook = item5;
        ItemStack item6 = createItem("Book 2: Magician's Crafting Guide", Material.WRITTEN_BOOK,
                Collections.singletonList(ChatColor.GREEN + "§lCommon: §fOpens magic crafting guide"));
        magicCraftingBook = item6;
        if (pckg.tfw.isBookDiscovered(0)) { inv.setItem(2, item5); }
        if (pckg.tfw.isBookDiscovered(1)) { inv.setItem(7, item6); }
    }

    /**
     * Helper method for creating items to be placed in the inventory GUI.
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

    /**
     * Grabs the instance of the GUI created.
     *
     * @return instance of TomeSelection GUI.
     */
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
