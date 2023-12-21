package me.tbonejdi.tboneplugins.effects;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockEffects {

    public static void createGroundPoundExplosion(Player player, int radius) {

        Location location = player.getLocation();
        List<Block> blocks = new ArrayList<>();
        Random random = new Random();

        // Handle populating the blocks list first
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                blocks.add(location.getWorld().getHighestBlockAt(x, z));
            }
        }

        for (Block block : blocks) {
            if (random.nextInt(5) != 4 && block.getType().equals(Material.GRASS_BLOCK))
                block.setType(Material.DIRT);
        }



    }

}
