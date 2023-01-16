package me.tbonejdi.tboneplugins.inventories;

import me.tbonejdi.tboneplugins.classes.ClassFile;
import me.tbonejdi.tboneplugins.classes.Warrior;
import me.tbonejdi.tboneplugins.datacontainer.PlayerStates;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.fileadministrators.PackageInitializer;
import me.tbonejdi.tboneplugins.tomes.TomeSelection;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) throws IOException {
        if (e.getClickedInventory() == null) { return; }

        /*
        Is an instance of the class selection test inventory
         */
        if (e.getClickedInventory().getHolder() instanceof ClassSelection) {
            PackageInitializer pckg = FileStartupEvents.playerData.get(e.getWhoClicked().getName());
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() == null) { return; }
            if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                p.sendMessage("§4Exiting Process...");
                p.closeInventory();
            }
            else if(e.getSlot() == 0) {
                p.sendMessage("§6Selecting the §cWarrior §6class!");
                pckg.cw.saveToFile(pckg.cInfo); // Check this, sorta skeptical for errors.
                ClassFile.resetPlayer(p);
                Warrior.setClass(p);
                Warrior.setBuffs(pckg.cInfo);
                p.closeInventory();
            }

            FileStartupEvents.playerData.replace(e.getWhoClicked().getName(), pckg);
            return;
        }

        /*
        Is an instance of the TomeSelection inventory screen
         */
        if (e.getClickedInventory().getHolder() instanceof TomeSelection) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            if (e.getCurrentItem() == null) { return; }

            /*
            This is only in place for the default book, needs to be updated once more books are added
             */
            if (e.getCurrentItem().getItemMeta().equals(TomeSelection.tutorialBook.getItemMeta())) {
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) book.getItemMeta();
                meta.setAuthor("TboneSMP");
                meta.setTitle(ChatColor.GOLD + "Tutorial book [#1]");
                ArrayList<String> pages = new ArrayList<>();
                pages.add(0, "§8Hello there stranger.\n\n" +
                        "§0Who I am should not matter to you. Somehow you have discovered this tome through some sense " +
                        "of magic. The knowledge you'll discover is quite discrete to your nature. " +
                        "Only those strong enough may know how to use this knowledge.");
                pages.add(1, "As you might now notice, there seems to be lots of secrets to explore, " +
                        "and I do believe that you hold the potential to seek it all. The most important part " +
                        "is that you stay hungry and patient in your searching.");
                pages.add(2, "I would advise you to let nature take its course. The secrets of the " +
                        "universe shall come to you. I wish you good luck.\n\n\n§5Signed §d§kTobias");
                meta.setPages(pages);
                book.setItemMeta(meta);
                p.closeInventory();
                p.openBook(book);
                return;
            }

            if (e.getCurrentItem().getItemMeta().equals(TomeSelection.magicCraftingBook.getItemMeta())) {
                //TODO: Add different tiers so that we can have players "unlock" the missing pages.
                PackageInitializer pckg = FileStartupEvents.playerData.get(p.getName());
                if (pckg.tfw.isBookDiscovered(2)) {
                    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta meta = (BookMeta) book.getItemMeta();
                    meta.setAuthor("TboneSMP");
                    meta.setTitle(ChatColor.RED + "Magic Crafting Handbook [#2]");
                    ArrayList<String> pages = new ArrayList<>();
                    pages.add(0, "\n\n\n\n          §lMAGIC\n\n      CRAFTING\n\n      HANDBOOK\n\n\n\n");
                    pages.add(1, "§8§lJuly 10th\n\n§0I can no longer distinguish between my lucidity " +
                            "and the voices. They all seem to be so real. Just last night I had the same friendly spirit " +
                            "remind me of that damn symbol. What is it??");
                    pages.add(2, "\n\n\n\n\n|﹉|   |﹉|   |﹉|\n|﹉|   |﹉|   |﹉|  ->  |§kx§0|" +
                            "\n|﹉|   |﹉|   |﹉|\n§f|§0﹉    ﹉    ﹉§f|");
                    pages.add(3, "TODO: Add more later :) Good job!");
                    meta.setPages(pages);
                    book.setItemMeta(meta);
                    p.closeInventory();
                    p.openBook(book);
                    return;
                }
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) book.getItemMeta();
                meta.setAuthor("TboneSMP");
                meta.setTitle(ChatColor.RED + "Magic Crafting Handbook [#2]");
                ArrayList<String> pages = new ArrayList<>();
                pages.add(0, "\n\n\n\n          §lMAGIC\n\n      CRAFTING\n\n      HANDBOOK\n\n\n\n");
                pages.add(1, "§8§lJuly 10th\n\n§0I can no longer distinguish between my lucidity " +
                        "and the voices. They all seem to be so real. Just last night I had the same friendly spirit " +
                        "remind me of that damn symbol. What is it??");
                pages.add(2, "\n\n\n\n\n|﹉|   |﹉|   |﹉|\n|﹉|   |﹉|   |﹉|  ->  |§kx§0|" +
                        "\n|﹉|   |﹉|   |﹉|\n§f|§0﹉    ﹉    ﹉§f|");
                pages.add(3, "No missing page found!"); // Add more later... Remember to leave room for the "missing page".
                meta.setPages(pages);
                book.setItemMeta(meta);
                p.closeInventory();
                p.openBook(book);
                return;
            }


            if (e.getCurrentItem().getType() == Material.BOOK) {
                p.sendMessage(ChatColor.GRAY + "Hmmm... It seems this knowledge is still undiscovered.");
                return;
            }

            if (e.getCurrentItem().getType() == Material.BARRIER) {
                p.closeInventory();
            }
        }

        if (e.getClickedInventory().getHolder() instanceof MagicItemsList) {

            if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                e.getWhoClicked().closeInventory();
                return;
            }

            Player player = (Player) e.getWhoClicked();
            player.closeInventory();
            MagicItemAmount gui = new MagicItemAmount(e.getCurrentItem());
            PlayerStates states = FileStartupEvents.playerStates.get(player.getName());
            states.magicItemGive = e.getCurrentItem();
            FileStartupEvents.playerStates.replace(player.getName(), states);
            player.openInventory(gui.getInventory());
        }

        if (e.getClickedInventory().getHolder() instanceof  MagicItemAmount) {

            Player player = (Player) e.getWhoClicked();
            PlayerStates states = FileStartupEvents.playerStates.get(player.getName());
            ItemStack item = states.magicItemGive;

            if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                e.getWhoClicked().closeInventory();
                states.magicItemGive = null;
                FileStartupEvents.playerStates.replace(player.getName(), states);
                return;
            }

            if (e.getSlot() == 0) {
                player.getInventory().addItem(item);

            } else if (e.getSlot() == 2) {
                ItemStack newItem = new ItemStack(item.getType(), 8);
                ItemMeta im = item.getItemMeta();
                item.setItemMeta(im);
                player.getInventory().addItem(newItem);
                states.magicItemGive = null;
                FileStartupEvents.playerStates.replace(player.getName(), states);

            } else if (e.getSlot() == 4) {
                ItemStack newItem = new ItemStack(item.getType(), 64);
                ItemMeta im = item.getItemMeta();
                item.setItemMeta(im);
                player.getInventory().addItem(newItem);
                states.magicItemGive = null;
                FileStartupEvents.playerStates.replace(player.getName(), states);
            }
        }

    }
}
