package me.tbonejdi.tboneplugins.portals;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class AetherPortal {
    private World world;
    private ArrayList<Location> portal;
    private ArrayList<Location> frame;


    public AetherPortal(World world, ArrayList<Location> portal, ArrayList<Location> frame) {
        this.world = world;
        this.frame = frame;
        this.portal = portal;
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Location> getPortalFrame() {
        return frame;
    }

    public ArrayList<Location> getPortal() {
        return portal;
    }
}
