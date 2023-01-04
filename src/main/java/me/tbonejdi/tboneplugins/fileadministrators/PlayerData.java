package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.entity.Player;

public class PlayerData extends AbstractFile {

    public PlayerData(Main main) {
        super(main, "playerdata.yml");
    }

    public void newPlayer(Player player) {
        config.set(player.getUniqueId().toString(), player.getName());
        save();
    }



}
