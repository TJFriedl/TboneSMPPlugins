package me.tbonejdi.tboneplugins.commands;

import me.tbonejdi.tboneplugins.enchants.CustomEnchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        if (s.equalsIgnoreCase("telepathy")) {

            ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
            item.addUnsafeEnchantment(CustomEnchants.TELEPATHY, 1);

            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(CustomEnchants.TELEPATHY, 1, true); //TODO: Check for functionality
            List<String> lore = new ArrayList<String>();
            // lore.add(ChatColor.GRAY + "Telepathy I");

            if (meta.hasLore())
                for (String l : meta.getLore())
                    lore.add(l);

            meta.setLore(lore);
            item.setItemMeta(meta);

            player.getInventory().addItem(item);
            return true;
        }

        if (s.equalsIgnoreCase("blazedtip")) {
            ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
            item.addUnsafeEnchantment(CustomEnchants.BLAZED_TIP, 1);

            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(CustomEnchants.BLAZED_TIP, 1, true); // TODO: Check for functionality
            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GOLD + "Blazed Tip I");

            if (meta.hasLore())
                for (String l : meta.getLore())
                    lore.add(l);

            meta.setLore(lore);
            item.setItemMeta(meta);

            player.getInventory().addItem(item);
            return true;
        }
        return true;
    }
}
