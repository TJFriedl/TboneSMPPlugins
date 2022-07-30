package me.tbonejdi.tboneplugins.datacontainer;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerStates {
    public boolean isMagicCrafting;

    public static HashMap<String, PlayerStates> playerState;

    public PlayerStates() {
        isMagicCrafting = false;
    }
}
