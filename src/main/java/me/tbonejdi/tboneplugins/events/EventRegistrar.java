package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.Main;
import me.tbonejdi.tboneplugins.classes.ClassXPEvents;
import me.tbonejdi.tboneplugins.classes.Warrior;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.inventories.InventoryEvents;
import me.tbonejdi.tboneplugins.mobs.MonsterEvents;
import me.tbonejdi.tboneplugins.mobs.SpiderEvents;
import me.tbonejdi.tboneplugins.portals.PortalEvents;
import me.tbonejdi.tboneplugins.tomes.TomeEvents;

public class EventRegistrar {

    /**
     * Registers all in-game events programmed within this plugin.
     * @param main
     */
    public static void EnableAllEvents(Main main) {
        // ------------ Item Events ------------
        main.getServer().getPluginManager().registerEvents(new ItemEvents(), main);

        // ------------ Inventory Events ------------
        main.getServer().getPluginManager().registerEvents(new InventoryEvents(), main);

        // ------------ File Startup Events ------------
        main.getServer().getPluginManager().registerEvents(new FileStartupEvents(), main);

        // ------------ Cantrip Events ------------
        main.getServer().getPluginManager().registerEvents(new CantripEvents(), main);

        // ------------ Mob Drop Events ------------
        main.getServer().getPluginManager().registerEvents(new MobDropEvents(), main);

        // ------------ Tome Events ------------
        main.getServer().getPluginManager().registerEvents(new TomeEvents(), main);

        // ------------ Class XP Events ------------
        main.getServer().getPluginManager().registerEvents(new ClassXPEvents(), main);

        // ------------ Level Progression Events ------------
        main.getServer().getPluginManager().registerEvents(new LevelProgressionEvents(), main);

        // ------------ Crafting Events ------------
        main.getServer().getPluginManager().registerEvents(new CraftingEvents(), main);

        // ------------ Portal Events ------------
        main.getServer().getPluginManager().registerEvents(new PortalEvents(), main);

        // ------------ Spider Events ------------
        main.getServer().getPluginManager().registerEvents(new SpiderEvents(), main);

        // ------------ Monster Events ------------
        main.getServer().getPluginManager().registerEvents(new MonsterEvents(), main);

        // ------------ Combat Events ------------
        main.getServer().getPluginManager().registerEvents(new CombatEvents(), main);

        // ------------ Warrior Events ------------
        main.getServer().getPluginManager().registerEvents(new Warrior(), main);
    }

}
