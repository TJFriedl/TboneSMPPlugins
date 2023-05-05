package me.tbonejdi.tboneplugins.events;

import me.tbonejdi.tboneplugins.Main;
import me.tbonejdi.tboneplugins.classes.ClassXPEvents;
import me.tbonejdi.tboneplugins.enchants.EnchantEvents;
import me.tbonejdi.tboneplugins.fileadministrators.FileStartupEvents;
import me.tbonejdi.tboneplugins.inventories.InventoryEvents;
import me.tbonejdi.tboneplugins.mobs.MobEvents;
import me.tbonejdi.tboneplugins.mobs.SpiderEvents;
import me.tbonejdi.tboneplugins.portals.PortalEvents;
import me.tbonejdi.tboneplugins.tomes.TomeEvents;

public class EventRegistrar {

    public static void EnableAllEvents(Main main) {
        main.getServer().getPluginManager().registerEvents(new ItemEvents(), main);
        main.getServer().getPluginManager().registerEvents(new InventoryEvents(), main);
        main.getServer().getPluginManager().registerEvents(new FileStartupEvents(), main);
        main.getServer().getPluginManager().registerEvents(new CantripEvents(), main);
        main.getServer().getPluginManager().registerEvents(new MobDropEvents(), main);
        main.getServer().getPluginManager().registerEvents(new TomeEvents(), main);
        main.getServer().getPluginManager().registerEvents(new ClassXPEvents(), main);
        main.getServer().getPluginManager().registerEvents(new LevelProgressionEvents(), main);
        main.getServer().getPluginManager().registerEvents(new EnchantEvents(), main);
        main.getServer().getPluginManager().registerEvents(new CraftingEvents(), main);
        main.getServer().getPluginManager().registerEvents(new PortalEvents(), main);
        main.getServer().getPluginManager().registerEvents(new SpiderEvents(), main);
        main.getServer().getPluginManager().registerEvents(new MobEvents(), main);
    }

}
