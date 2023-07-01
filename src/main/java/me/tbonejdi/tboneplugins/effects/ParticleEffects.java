package me.tbonejdi.tboneplugins.effects;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ParticleEffects {

    public static void createHorizontalRing(Player player, Particle particle, double radius, int particleCount) {
        Location location = player.getLocation();
        World world = location.getWorld();
        double increment = (2 * Math.PI / particleCount);

        for (int i = 0; i < particleCount; i++) {
            double angle = i * increment;
            double x = location.getX() + radius * Math.cos(angle);
            double y = location.getY();
            double z = location.getZ() + radius * Math.sin(angle);

            Location particleLocation = new Location(world, x, y, z);
            world.spawnParticle(particle, location, 1);
        }
    }

}
