package me.tbonejdi.tboneplugins.datacontainer;

import org.bukkit.inventory.ItemStack;

public class PlayerStates {
    public boolean isMagicCrafting;
    public boolean isPlayerReset;
    public boolean isChargingCenteredStrike;
    public boolean isChargingCenteredSweep;
    public boolean isChargingSigiledShield;
    public boolean sigiledShieldisCharged;
    public ItemStack magicItemGive;

    public PlayerStates() {

        isMagicCrafting = false;
        isPlayerReset = false;
        isChargingCenteredStrike = false;
        isChargingCenteredSweep = false;
        isChargingSigiledShield = false;
        sigiledShieldisCharged = false;
        magicItemGive = null;
    }
}
