package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.Main;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.inventories.MagicCraftingInventory;
import me.tbonejdi.tboneplugins.items.MagicMirror;
import me.tbonejdi.tboneplugins.items.MagicTable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class CraftingEvents implements Listener {

    public static ItemStack magicCraftingBook;

    @EventHandler
    public void onItemCraft(CraftItemEvent e) {
        // First thing we need to do is find the location of the crafting table used.
        Player player = (Player) e.getWhoClicked();
        PackageInitializer pckg = FileStartupEvents.playerData.get(player.getName());

        if ((pckg.tfw.isBookDiscovered(1)) ||  pckg.pInfo.getLevel() < 2) { return; }

        Location loc = player.getLocation();
        Location craftingTable;
        for (int x=-5; x<=5; x++) {
            for (int y=-5; y<=5; y++) {
                for (int z=-5; z<=5; z++) {
                    Material block = player.getWorld().getBlockAt(loc.getBlockX()+x, loc.getBlockY()+y,
                            loc.getBlockZ()+z).getType();
                    if (block == Material.CRAFTING_TABLE) {
                        craftingTable = new Location(player.getWorld(), loc.getBlockX()+x, loc.getBlockY()+y,
                                loc.getBlockZ()+z);
                        ItemStack item = new ItemStack(Material.BOOK, 1);
                        ItemMeta im = item.getItemMeta();
                        im.setDisplayName(ChatColor.DARK_BLUE + "The handyman's chronicle.");
                        List<String> lore = new ArrayList<>();
                        lore.add(ChatColor.GRAY + "Has a well-worm cover, smells of cedarwood.");
                        lore.add(ChatColor.BLUE + "Right click in hand to activate!");
                        lore.add(ChatColor.DARK_BLUE + "Book magically created for " + ChatColor.GOLD + player.getName());
                        im.setLore(lore);
                        im.addEnchant(Enchantment.LUCK, 1,false);
                        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(im);
                        magicCraftingBook = item;
                        craftingTable.getWorld().dropItemNaturally(craftingTable, item);
                        player.closeInventory();
                        player.sendMessage("§7Wha- ? This was not what I had tried crafting...");
                        craftingTable.getWorld().dropItemNaturally(craftingTable, e.getCurrentItem());
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void placeMagicTableEvent (BlockPlaceEvent e) {
        if (e.getItemInHand().getItemMeta().equals(MagicTable.magicTable.getItemMeta())) {
            e.getPlayer().sendMessage("This is in fact a magic table!");

            Block block = e.getBlockPlaced();
            block.setMetadata("MagicCraftingTable",
                    new FixedMetadataValue(Main.mainClassCall, "magic-craft"));
            e.getPlayer().sendMessage(ChatColor.GOLD + "MetaData assigned to magic crafting table");
        }
    }

    @EventHandler
    public void breakMagicTableEvent (BlockBreakEvent e) {
        if (e.getBlock().hasMetadata("MagicCraftingTable")) {
            e.getPlayer().sendMessage(ChatColor.GOLD + "This was a magic crafting table!");
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    MagicTable.magicTable);
            e.getBlock().removeMetadata("MagicCraftingTable", Main.mainClassCall);
        }
    }
    // TODO: Maybe try to improve this algorithm in the future... Currently brute-force.
    @EventHandler
    public void openCraftingTable (InventoryOpenEvent e) {
        if (!(e.getInventory() instanceof CraftingInventory))
            return;
        Player player = (Player) e.getPlayer();
        Location location = player.getLocation();
        for (int x = -4; x <= 4; x++) {
            for (int y = -4; y <= 4; y++) {
                for (int z = -4; z <= 4; z++) {
                    if (player.getWorld().getBlockAt(location.getBlockX() + x,
                            location.getBlockY() + y, location.getBlockZ() + z)
                            .hasMetadata("MagicCraftingTable")) {
                        player.sendMessage(ChatColor.GOLD + " OPENING MAGIC WORKBENCH");
                        e.setCancelled(true);
                        MagicCraftingInventory gui = new MagicCraftingInventory();
                        player.openInventory(gui.getInventory());

                        return;
                    }
                }
            }
        }
    }

    // FOR SLOTS: 0 = Result, 1-9 = Recipe
    @EventHandler
    public void craftMagicItem (InventoryClickEvent e) throws NullPointerException {
        if (e.getView().getTitle() != "§6§kiii§5Magic Crafting Table§6§kiii")
            return;

        Player player = (Player) e.getWhoClicked();
        InventoryView inv = e.getView();

        Bukkit.broadcastMessage(e.getAction().toString());

        if ((e.getAction() == InventoryAction.PICKUP_ALL ||
                e.getAction() == InventoryAction.PICKUP_HALF) && e.getSlot() == 0) {
            ItemStack itemStack = inv.getItem(0);
            player.closeInventory();
            player.getWorld().dropItemNaturally(e.getWhoClicked().getEyeLocation(),
                    itemStack);
            return;
        }
        // Shaped Recipe for Magic Mirror
        try {
            if (inv.getItem(1).getType() == Material.OBSIDIAN && inv.getItem(2).getType()
                    == Material.REDSTONE_BLOCK && inv.getItem(3).getType() == Material.OBSIDIAN &&
                    inv.getItem(4).getType() == Material.REDSTONE_BLOCK && inv.getItem(5).getType()
                    == Material.COMPASS && inv.getItem(6).getType() == Material.REDSTONE_BLOCK &&
                    inv.getItem(7).getType() == Material.OBSIDIAN && inv.getItem(8).getType()
                    == Material.REDSTONE_BLOCK && inv.getItem(9).getType() == Material.OBSIDIAN) {
                inv.setItem(0, MagicMirror.magicMirror);
                return;
            }
            else {
                inv.setItem(0, null);
            }
        } catch (NullPointerException exception) {}

    }
}
