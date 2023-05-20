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
        BeastAmulet.registerItem();
        KeenBlade.registerItem();
        KeenCleaver.registerItem();
    }

    public static void populateStaticList() {
        magicItems = new ArrayList<>();
        // Crystal Fruit Instance
        magicItems.add(CrystalFruit.crystalFruit);
        // Diamond Wand Instance
        magicItems.add(DiamondWand.diamondWand);
        // Floating Wand Instance
        magicItems.add(FloatingWand.floatingWand);
        // Wand Instance
        magicItems.add(ItemManager.wand);
        // Magic Mirror Instance
        magicItems.add(MagicMirror.magicMirror);
        // Magic Table Instance
        magicItems.add(MagicTable.magicTable);
        // "Green Stuff" Instance/c
        magicItems.add(GreenStuff.greenStuff);
        // Mysterious Item Instance
        magicItems.add(MysteriousItem.mysteriousItem);
        // Beast Amulet Instance
        magicItems.add(BeastAmulet.beastAmulet);
        // Keen Blade Instance
        magicItems.add(KeenBlade.keenBlade);
        // keen Cleaver Instance
        magicItems.add(KeenCleaver.keenCleaver);
    }

}
