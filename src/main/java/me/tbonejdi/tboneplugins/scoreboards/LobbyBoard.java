package me.tbonejdi.tboneplugins.scoreboards;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LobbyBoard {

    private static Map<UUID, Integer> TASKS = new HashMap<>();
    private final UUID uuid;

    public LobbyBoard(UUID uuid) {
        this.uuid = uuid;
    }

    public void setID(int id) {
        TASKS.put(uuid, id);
    }

    public int getID() {
        return TASKS.get(uuid);
    }

    public boolean hasID() {
        return TASKS.containsKey(uuid);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(TASKS.get(uuid));
        TASKS.remove(uuid);
    }
}
