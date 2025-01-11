package me.tbonejdi.tboneplugins.portals;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

/**
 * Class used to create an aether portal object to track information
 */
public class AetherPortal {
    private World world;
    private ArrayList<Location> portal;
    private ArrayList<Location> frame;

    /**
     * Constructor for AetherPortal. Requires world, and blocks of frame and portal.
     * @param world
     * @param portal
     * @param frame
     */
    public AetherPortal(World world, ArrayList<Location> portal, ArrayList<Location> frame) {
        this.world = world;
        this.frame = frame;
        this.portal = portal;
    }

    /**
     * Grabs the world that the portal was created in.
     * @return portal world.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Returns portal frame block list.
     * @return portal frame blocks.
     */
    public ArrayList<Location> getPortalFrame() {
        return frame;
    }

    /**
     * Returns portal "portal" block list.
     * @return portal "portal" block lists.
     */
    public ArrayList<Location> getPortal() {
        return portal;
    }
}
