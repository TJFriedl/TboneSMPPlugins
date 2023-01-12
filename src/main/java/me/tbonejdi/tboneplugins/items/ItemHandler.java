package me.tbonejdi.tboneplugins.items;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ItemHandler {

    public static ArrayList<ItemStack> magicItems;

    /**
     * ADDS ALL OF THE CURRENT CUSTOM ITEMS INTO STATIC VARIABLES, THIS IS VERY IMPORTANT
     */
    public static void implementCustomItems() {

        CrystalFruit.registerItem();
        DiamondWand.registerItem();
        FirstTome.registerItem();
        FloatingWand.registerItem();
        ItemManager.registerItem();
        MagicMirror.registerItem();
        MagicTable.registerItem();
        SecondTome.registerItem();
        SecondTomePage.registerItem();
        GreenStuff.registerItem();
        MysteriousItem.registerItem();

    }

    public static void populateStaticList() {

        magicItems = new ArrayList<>();
        magicItems.add(CrystalFruit.crystalFruit);
        magicItems.add(DiamondWand.diamondWand);
        magicItems.add(FloatingWand.floatingWand);
        magicItems.add(ItemManager.wand);
        magicItems.add(MagicMirror.magicMirror);
        magicItems.add(MagicTable.magicTable);
        magicItems.add(GreenStuff.greenStuff);
        magicItems.add(MysteriousItem.mysteriousItem);

    }

}
