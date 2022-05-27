package me.tbonejdi.tboneplugins.enchants;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class EnchantEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        // These are conditionals shown in the tutorial... Condense and clean eventually.
        if (e.getPlayer().getInventory().getItemInMainHand() == null ||
                !e.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
            return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.TELEPATHY))
            return;
        if (!(e.getPlayer().getGameMode() == GameMode.SURVIVAL))
            return;
        if (e.getPlayer().getInventory().firstEmpty() == -1)
            return;
        if (e.getBlock().getState() instanceof Container)
            return;

        e.setDropItems(false);
        Player p = e.getPlayer();
        Block b = e.getBlock();

        Collection<ItemStack> drops = b.getDrops(p.getInventory().getItemInMainHand());
        if (drops.isEmpty()) return;
        p.getInventory().addItem(drops.iterator().next());

    }

    @EventHandler
    public void onOreBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.IRON_ORE
                && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.BLAZED_TIP)) {
            e.setCancelled(true);
            e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                    new ItemStack(Material.IRON_INGOT));
        }
    }

}
