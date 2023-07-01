package me.tbonejdi.tboneplugins.effects;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.Location;
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

        // Handle populating the blocks list first
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                blocks.add(location.getWorld().getHighestBlockAt(x, z)
                        .getLocation().subtract(0,1,0).getBlock());
            }
        }

        new BukkitRunnable() {
            int count = 0;
            List<FallingBlock> fblocks = new ArrayList<>();
            @Override
            public void run() {
                if (count == 300) {
                    cancel();
                    for (FallingBlock block : fblocks)
                        block.setGravity(true);
                    return;
                }
                Random random = new Random();
                Block block = blocks.get(random.nextInt(blocks.size() - 0) + 0);
                FallingBlock fblock = player.getWorld().spawnFallingBlock(block.getLocation(), block.getBlockData());
                fblock.setVelocity((fblock.getLocation().toVector().subtract(player.getLocation().toVector())
                        .multiply(2.0).normalize()));
                fblock.setGravity(false);
                fblock.setDropItem(false);
                fblock.setHurtEntities(false);

                fblocks.add(fblock);

                count++;
                player.sendMessage("Count: " + count);
            }
        }.runTaskTimer(Main.mainClassCall, 0, 0);
    }

}
