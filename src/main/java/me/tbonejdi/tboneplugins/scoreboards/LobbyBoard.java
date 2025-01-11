package me.tbonejdi.tboneplugins.scoreboards;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class made to handle the information to allow a lobby board for each player.
 */
public class LobbyBoard {

    private static Map<UUID, Integer> TASKS = new HashMap<>();
    private final UUID uuid;

    /**
     * Constructor for LobbyBoard. Takes player's UUID as argument.
     * @param uuid
     */
    public LobbyBoard(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Sets the UUID for lobby board.
     * @param id
     */
    public void setID(int id) {
        TASKS.put(uuid, id);
    }

    /**
     * Returns the current UUID set for the lobby board.
     * @return
     */
    public int getID() {
        return TASKS.get(uuid);
    }

    /**
     * Check to see that the lobby board contains a UUID.
     * @return
     */
    public boolean hasID() {
        return TASKS.containsKey(uuid);
    }

    /**
     * Stops the lobby board from functioning.
     */
    public void stop() {
        Bukkit.getScheduler().cancelTask(TASKS.get(uuid));
        TASKS.remove(uuid);
    }
}
