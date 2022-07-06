package me.tbonejdi.tboneplugins;

import me.tbonejdi.tboneplugins.classes.ClassXPEvents;
import me.tbonejdi.tboneplugins.commands.EnchantCommands;
import me.tbonejdi.tboneplugins.commands.ItemCommands;
import me.tbonejdi.tboneplugins.commands.PlayerInfoCommands;
import me.tbonejdi.tboneplugins.enchants.CustomEnchants;
import me.tbonejdi.tboneplugins.enchants.EnchantEvents;
import me.tbonejdi.tboneplugins.fileadministrators.*;
import me.tbonejdi.tboneplugins.inventories.InventoryEvents;
import me.tbonejdi.tboneplugins.items.CrystalFruit;
import me.tbonejdi.tboneplugins.items.MagicMirror;
import me.tbonejdi.tboneplugins.tomes.TomeEvents;
import me.tbonejdi.tboneplugins.tomes.TomesCommands;
import me.tbonejdi.tboneplugins.commands.TutorialCommands;
import me.tbonejdi.tboneplugins.events.*;
import me.tbonejdi.tboneplugins.items.DiamondWand;
import me.tbonejdi.tboneplugins.items.ItemManager;
import me.tbonejdi.tboneplugins.scoreboards.LobbyBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.io.IOException;

public final class Main extends JavaPlugin implements Listener {

    private int taskID;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "TboneSMP plugin is enabled!");


        /*
            THESE ARE PRESET PLUGINS YOU WROTE - DO NOT DELETE!!!
         */
        getServer().getPluginManager().registerEvents(new ItemEvents(), this);
        getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
        getServer().getPluginManager().registerEvents(new FileStartupEvents(), this);
        getServer().getPluginManager().registerEvents(new CantripEvents(), this);
        getServer().getPluginManager().registerEvents(new MobDropEvents(), this);
        getServer().getPluginManager().registerEvents(new TomeEvents(), this);
        getServer().getPluginManager().registerEvents(new ClassXPEvents(), this);
        getServer().getPluginManager().registerEvents(new LevelProgressionEvents(), this);
        getServer().getPluginManager().registerEvents(new EnchantEvents(), this);

         /*
            Score board functionality (in the main method)
          */
        this.getServer().getPluginManager().registerEvents(this, this);

         /*
            These are for messing around with custom items and commands
          */
        ItemManager.init();
        DiamondWand.init();
        MagicMirror.init();
        CrystalFruit.init();

        TutorialCommands t = new TutorialCommands();
        getCommand("select").setExecutor(t);
        getCommand("chooseclass").setExecutor(t);

        TomesCommands tc = new TomesCommands();
        getCommand("tomes").setExecutor(tc);
        getCommand("detecttome").setExecutor(tc);

        ItemCommands ic = new ItemCommands();
        getCommand("summonitem").setExecutor(ic);
        getCommand("diamonddetect").setExecutor(ic);

        PlayerInfoCommands pic = new PlayerInfoCommands();
        getCommand("setplayerxp").setExecutor(pic);
        getCommand("setplayerlevel").setExecutor(pic);
        getCommand("setplayerclass").setExecutor(pic);
        getCommand("setclasslevel").setExecutor(pic);
        getCommand("setclassxp").setExecutor(pic);
        getCommand("deletefiledata").setExecutor(pic);
        getCommand("getplayermaxhealth").setExecutor(pic);
        getCommand("setplayermaxhealth").setExecutor(pic);
        getCommand("setplayerbasearmor").setExecutor(pic);

        EnchantCommands ec = new EnchantCommands();
        getCommand("telepathy").setExecutor(ec);
        getCommand("blazedtip").setExecutor(ec);


        CustomEnchants.register();

         /*
              RESETS THE SCOREBOARDS IN THE CASE THAT THE SERVER EXECUTES /reload, might need to be changed in order to
              be a static call from main...
          */
        if(!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player online: Bukkit.getOnlinePlayers()) {
                try {
                    createBoard(online);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    start(online);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "TboneSMP plugin shutting down...");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        createBoard(event.getPlayer());
        start(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        LobbyBoard board = new LobbyBoard(event.getPlayer().getUniqueId());
        if (board.hasID()) { board.stop(); }
    }

    public void start(Player player) throws IOException{
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            int count = 0;
            LobbyBoard board = new LobbyBoard(player.getUniqueId());

            @Override
            public void run() {
                if (!board.hasID()) { board.setID(taskID); }
                if (count == 10) count = 0;

                switch(count) {
                    case 0:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<TboneSMP>>");
                        break;
                    case 1:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<§e§lT§6§lboneSMP>>");
                        break;
                    case 2:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<T§e§lb§6§loneSMP>>");
                        break;
                    case 3:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<Tb§e§lo§6§lneSMP>>");
                        break;
                    case 4:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<Tbo§e§ln§6§leSMP>>");
                        break;
                    case 5:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<Tbon§e§le§6§lSMP>>");
                        break;
                    case 6:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<Tbone§e§lS§6§lMP>>");
                        break;
                    case 7:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<TboneS§e§lM§6§lP>>");
                        break;
                    case 8:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<TboneSM§e§lP§6§l>>");
                        break;
                    case 9:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6§l<<TboneSMP>>");
                        try {
                            createBoard(player);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                count++;
            }
        }, 0, 10);
    }
    /*
    TODO: Needs to update personal information... Currently stuck updating everyone's together.
     */
    public void createBoard(Player player) throws IOException {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("ScoreBoard-1", "dummy", "§6§l<<TboneSMP>>");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        // We need to think about the best way to get the specific... Maybe we can THROW A HASHMAP AT IT!?!?!?!

        Score score = obj.getScore("§7§l=-=-=-=-=-=-=");
        score.setScore(6);
        Score score2 = obj.getScore(ChatColor.GOLD +"Online Players: " +
                Bukkit.getOnlinePlayers().size());
        score2.setScore(5);
        Score score3 = obj.getScore(ChatColor.GOLD + "Class: §8" + FileStartupEvents.cInfo.getCurrentClass());
        score3.setScore(4);
        Score score4 = obj.getScore(ChatColor.GOLD + "Player Lvl: " + FileStartupEvents.pInfo.getLevel());
        score4.setScore(3);
        Score score5 = obj.getScore(ChatColor.GOLD + "Player XP: " + "§c" + FileStartupEvents.pInfo.getExp() + "/" +
                FileStartupEvents.pInfo.getMaxExp() + " [" +
                (int) Math.floor( ((double) FileStartupEvents.pInfo.getExp() / (double) FileStartupEvents.pInfo.getMaxExp())
                * 100) + "%]");
        score5.setScore(2);
        Score score6 = obj.getScore(ChatColor.GOLD + "Class Lvl: " + FileStartupEvents.cInfo.getClassLvl());
        score6.setScore(1);
        Score score7 = obj.getScore(ChatColor.GOLD + "Class XP: " + "§e" + FileStartupEvents.cInfo.getClassExp() + "/" +
                FileStartupEvents.cInfo.getClassMaxExp() + " [" +
                (int) Math.floor( ((double) FileStartupEvents.cInfo.getClassExp() / (double)
                        FileStartupEvents.cInfo.getClassMaxExp()) * 100) + "%]");
        score7.setScore(0);
        player.setScoreboard(board);

    }
}
