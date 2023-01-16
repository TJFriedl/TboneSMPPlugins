package me.tbonejdi.tboneplugins.datacontainer;

import org.bukkit.inventory.ItemStack;

public class PlayerStates {
    public boolean isMagicCrafting;
    public boolean isPlayerReset;

    public ItemStack magicItemGive;

    public PlayerStates() {

        isMagicCrafting = false;
        isPlayerReset = false;
        magicItemGive = null;
    }
}
