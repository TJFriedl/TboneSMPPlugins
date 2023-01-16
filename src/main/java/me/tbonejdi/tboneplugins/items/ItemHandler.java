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
        // Crystal Fruit Instance
        magicItems.add(CrystalFruit.crystalFruit);
        magicItems.add(CrystalFruit.crystalFruitEight);
        magicItems.add(CrystalFruit.crystalFruitStack);
        // Diamond Wand Instance
        magicItems.add(DiamondWand.diamondWand);
        magicItems.add(DiamondWand.diamondWandEight);
        magicItems.add(DiamondWand.diamondWandStack);
        // Floating Wand Instance
        magicItems.add(FloatingWand.floatingWand);
        magicItems.add(FloatingWand.floatingWandEight);
        magicItems.add(FloatingWand.floatingWandStack);
        // Wand Instance
        magicItems.add(ItemManager.wand);
        magicItems.add(ItemManager.wandEight);
        magicItems.add(ItemManager.wandStack);
        // Magic Mirror Instance
        magicItems.add(MagicMirror.magicMirror);
        magicItems.add(MagicMirror.magicMirrorEight);
        magicItems.add(MagicMirror.magicMirrorStack);
        // Magic Table Instance
        magicItems.add(MagicTable.magicTable);
        magicItems.add(MagicTable.magicTableEight);
        magicItems.add(MagicTable.magicTableStack);
        // "Green Stuff" Instance
        magicItems.add(GreenStuff.greenStuff);
        magicItems.add(GreenStuff.greenStuffEight);
        magicItems.add(GreenStuff.greenStuffStack);
        // Mysterious Item Instance
        magicItems.add(MysteriousItem.mysteriousItem);
        magicItems.add(MysteriousItem.mysteriousItemEight);
        magicItems.add(MysteriousItem.mysteriousItemStack);
    }

}
