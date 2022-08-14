package me.tbonejdi.tboneplugins.portals;

import org.bukkit.Location;
import org.bukkit.World;

public class AetherPortal {
    private World world;
    private Location[] portal;
    private Location[] frame;


    public AetherPortal(World world, Location[] portal, Location[] frame) {
        this.world = world;
        this.frame = frame;
        this.portal = portal;
    }

    public World getWorld() {
        return world;
    }

    public Location[] getPortalFrame() {
        return frame;
    }

    public Location[] getPortal() {
        return portal;
    }
}
