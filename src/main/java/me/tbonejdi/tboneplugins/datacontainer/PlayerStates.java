package me.tbonejdi.tboneplugins.datacontainer;

import org.bukkit.inventory.ItemStack;

public class PlayerStates {
    public boolean isMagicCrafting;
    public boolean isPlayerReset;
    public boolean isChargingCenteredStrike;
    public ItemStack magicItemGive;

    public PlayerStates() {

        isMagicCrafting = false;
        isPlayerReset = false;
        isChargingCenteredStrike = false;
        magicItemGive = null;
    }
}
