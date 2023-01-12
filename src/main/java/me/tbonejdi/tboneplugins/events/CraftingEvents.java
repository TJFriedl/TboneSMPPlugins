package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.Main;
import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.MagicBlockManager;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.items.*;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class CraftingEvents implements Listener {

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
                        SecondTome.resetItem();
                        ItemStack item = SecondTome.secondTome;
                        ItemMeta im = item.getItemMeta();
                        List<String> lore = im.getLore();
                        lore.add(ChatColor.DARK_BLUE + "Book magically created for " + ChatColor.GOLD + player.getName());
                        im.setLore(lore);
                        item.setItemMeta(im);

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
            Block block = e.getBlockPlaced();
            block.setMetadata("MagicCraftingTable",
                    new FixedMetadataValue(Main.mainClassCall, "magic-craft"));
            MagicBlockManager.tables.add(e.getBlock().getLocation());
            MagicBlockManager.createTextEntity(e.getBlock().getLocation());
        }
    }

    @EventHandler
    public void breakMagicTableEvent (BlockBreakEvent e) {

        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            MagicBlockManager.tables.remove(e.getBlock().getLocation());
            MagicBlockManager.removeTextEntity(e.getBlock().getLocation());
            return;
        }

        if (e.getBlock().hasMetadata("MagicCraftingTable")) {
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
            e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    MagicTable.magicTable);
            e.getBlock().removeMetadata("MagicCraftingTable", Main.mainClassCall);
            MagicBlockManager.tables.remove(e.getBlock().getLocation());
            MagicBlockManager.removeTextEntity(e.getBlock().getLocation());
        }
    }
    // TODO: Maybe try to improve this algorithm in the future... Currently brute-force.
    @EventHandler
    public void openCraftingTable (InventoryOpenEvent e) {
        PlayerStates states = FileStartupEvents.playerStates.get(e.getPlayer().getName());
        if (!(e.getInventory() instanceof CraftingInventory) || states.isMagicCrafting)
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

                        //TODO: Add particle effect here!

                        states.isMagicCrafting = true;
                        FileStartupEvents.playerStates.replace(player.getName(), states);
                        player.sendMessage("MagicCraftingState: true");
                        e.setCancelled(true);
                        player.openWorkbench(null, true); // Opens magic crafting table.
                        return;
                    }
                }
            }
        }
    }


    // Throwing an error: Called once data is deleted?
    @EventHandler
    public void closeCraftingTable (InventoryCloseEvent e) {
        if (!(e.getInventory() instanceof CraftingInventory)) { return; }

        // Sometimes thrown after player leaves game.
        PlayerStates state = FileStartupEvents.playerStates.get(e.getPlayer().getName());
        if (!(FileStartupEvents.playerStates.containsKey(e.getPlayer().getName()))) { return; }

        if (state.isMagicCrafting) {
            state.isMagicCrafting = false;
            FileStartupEvents.playerStates.replace(e.getPlayer().getName(), state);
        }
    }

    @EventHandler
    public void handleMagicCrafting (PrepareItemCraftEvent e) throws NullPointerException {
        Player player = (Player) e.getViewers().get(0); // Assuming only one person is viewing this inventory?
        if (!(FileStartupEvents.playerStates.get(player.getName()).isMagicCrafting)) {
            return;
        }

        ArrayList<Material> recipeList = new ArrayList<>();
        for (ItemStack i : e.getInventory().getMatrix()) {
            try {
                recipeList.add(i.getType());
            } catch (NullPointerException ne) {}
        }

        if (recipeList.equals(MagicMirror.recipeList)) {
            e.getInventory().setResult(MagicMirror.magicMirror);
        }

    }
}
