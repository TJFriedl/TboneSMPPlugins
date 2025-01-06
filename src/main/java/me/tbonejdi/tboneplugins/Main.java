package me.tbonejdi.tboneplugins;

import me.tbonejdi.tboneplugins.commands.*;
import me.tbonejdi.tboneplugins.fileadministrators.*;
import me.tbonejdi.tboneplugins.items.*;
import me.tbonejdi.tboneplugins.tomes.TomesCommands;
import me.tbonejdi.tboneplugins.events.*;
import me.tbonejdi.tboneplugins.scoreboards.LobbyBoard;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.io.IOException;


public final class Main extends JavaPlugin implements Listener {

    private int taskID;
    public static Main mainClassCall;

    public DataManager data;
    public MagicBlockManager magicBlock;

    /**
     * Handles the startup logic for the server.
     *
     * This method should not be called within the API itself. Is called by the server service handler.
     */
    @Override
    public void onEnable() {

        mainClassCall = this;
        File directory = new File(this.getDataFolder().getPath());
        if (!(directory.exists())) { directory.mkdirs(); }
        this.data = new DataManager(mainClassCall);
        this.magicBlock = new MagicBlockManager(mainClassCall);

        // Prints startup message to terminal
        getServer().getConsoleSender().sendMessage("§6§lTboneSMPPlugin: Starting up...");

        // Register all spigot-related events outside of Main.java
        EventRegistrar.EnableAllEvents(mainClassCall);

        // Register all spigot-related events in Main.java
        this.getServer().getPluginManager().registerEvents(this, this);

        // Register custom items defined in the plugin
        getServer().getConsoleSender().sendMessage("§6§lTboneSMPPlugin: Registering Items...");
        ItemHandler.implementCustomItems();
        ItemHandler.populateStaticList();

        // REGISTER ALL CLASS COMMANDS DOWN BELOW
        // ------------ Class Commands ------------
        ClassCommands t = new ClassCommands();
        getCommand("select").setExecutor(t);
        getCommand("chooseclass").setExecutor(t);

        // ------------ Tome Commands -----------
        TomesCommands tc = new TomesCommands();
        getCommand("tomes").setExecutor(tc);
        getCommand("detecttome").setExecutor(tc);

        // ----------- Item Commands ------------
        ItemCommands ic = new ItemCommands();
        getCommand("diamonddetect").setExecutor(ic);
        getCommand("magicitems").setExecutor(ic);

        // ----------- Player Info Commands ------------
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
        getCommand("printlocation").setExecutor(pic);
        getCommand("quit").setExecutor(pic);

        // ------------ Entity Commands ------------
        EntityCommands enc = new EntityCommands();
        getCommand("removechunkentities").setExecutor(enc);
        getCommand("leapingspider").setExecutor(enc);
        getCommand("leveledspider").setExecutor(enc);

        // ------------ World Commands ------------
        WorldCommands wc = new WorldCommands();
        getCommand("sendtoaether").setExecutor(wc);
        getCommand("returnfromaether").setExecutor(wc);
        getCommand("getcurrenttime").setExecutor(wc);


        // RESETS THE SCOREBOARDS IN THE CASE THAT THE SERVER EXECUTES /reload, might need to be changed in order to
        // be a static call from main...
        if(!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player online: Bukkit.getOnlinePlayers()) {
                try {
                    FileStartupEvents.initPlayerData(online);
                    online.sendMessage(ChatColor.GOLD + "Server is restarting, reloading all of your data...");
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

          //TODO: This is disabled for now. Look at issues later and look into why there are potential errors?
//        getServer().getConsoleSender().sendMessage("§6Creating \"Aether\" dimension... may take a minute.");
//        WorldCreator c = new WorldCreator("aether");
//        c.type(WorldType.AMPLIFIED);
//        c.createWorld();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "TboneSMPPlugins: Boot success!");
    }

    /**
     * Handles shutdown logic for the server.
     *
     * This method should not be called within the API itself. Is called by the server service handler.
     */
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "TboneSMP plugin shutting down!");
        this.magicBlock.getConfig().set("magictables", MagicBlockManager.tables);
        this.magicBlock.saveConfig();

        if (MagicBlockManager.tables != null) {
            for (Location loc : MagicBlockManager.tables) {
                Entity[] entities = loc.getChunk().getEntities();
                for (Entity e : entities) {
                    if (e.getType().equals(EntityType.ARMOR_STAND))
                        e.remove();
                }
            }
        }
    }

    /**
     * Handles logic to create player scoreboard.
     *
     * This method currently calls "createBoard" and "start", two other methods responsible in the process
     * of creating and deploying scoreboards to each user.
     *
     * @param event
     * @throws IOException
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        createBoard(event.getPlayer());
        start(event.getPlayer());
    }

    /**
     * Handles logic for scoreboard teardown.
     *
     * Checks to make sure that scoreboard exists for each user (expected to exist). If it does, then the event handler
     * calls the corresponding "stop" function.
     *
     * @param event
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        LobbyBoard board = new LobbyBoard(event.getPlayer().getUniqueId());
        if (board.hasID()) { board.stop(); }
    }

    /**
     * TODO: Move or delete this function eventually. I believe this was made for some type of test and it is not
     * entirely needed for the functionality of this plugin.
     *
     * @param e
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.DIAMOND_ORE)) {
            Player player = e.getPlayer();
            int amount = 0;
            String username = e.getPlayer().getName();

            if (this.data.getConfig().contains("players." + player.getUniqueId() + ".total"))
                amount = this.data.getConfig().getInt("players." + player.getUniqueId() + ".total");

            this.data.getConfig().set("players." + player.getUniqueId() + ".username", username);
            this.data.getConfig().set("players." + player.getUniqueId() + ".total", (amount + 1));
            data.saveConfig();
        }
    }

    /**
     * Control logic for each player's scoreboard.
     *
     * Features a case counter that allows for an "animation" of a cascading character in the title.
     * @param player
     * @throws IOException
     */
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

    /**
     * Creates player scoreboard.
     *
     * Called once a player joins the server using this plugin. Will instantiate the scoreboard and place the UID of
     * scoreboard in Bukkit storage. This is where different fields can be defined for the scoreboard.
     * @param player
     * @throws IOException
     */
    public void createBoard(Player player) throws IOException {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("ScoreBoard-1", "dummy", "§6§l<<TboneSMP>>");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        PackageInitializer data = FileStartupEvents.playerData.get(player.getName()); // Hash Technology!!
        PlayerInfo playerStats = data.pInfo;
        ClassInfo classStats = data.cInfo;

        Score topBar = obj.getScore("§7§l=-=-=-=-=-=-=");
        topBar.setScore(8);
        Score playerCt = obj.getScore(ChatColor.RED +"Time: " + ChatColor.GOLD + handleInGameTime(player));
        playerCt.setScore(7);
        Score currentClass = obj.getScore(ChatColor.RED + "Class: §c" + classStats.getCurrentClass());
        currentClass.setScore(6);
        Score playerLvl = obj.getScore(ChatColor.GOLD + "Player Lv: " + playerStats.getLevel());
        playerLvl.setScore(5);
        Score playerXPPct = obj.getScore(ChatColor.GOLD + "Player XP: " + " §e[" +
                (int) Math.floor( ((double) playerStats.getExp() / (double) playerStats.getMaxExp())
                * 100) + "%]");
        playerXPPct.setScore(4);
        Score playerXP = obj.getScore("§e§l" + playerStats.getExp() + "/" + playerStats.getMaxExp());
        playerXP.setScore(3);
        Score classLvl = obj.getScore(ChatColor.YELLOW + "Class Lvl: " + classStats.getClassLvl());
        classLvl.setScore(2);
        Score classXPPct = obj.getScore(ChatColor.YELLOW + "Class XP: " + " §f[" +
                (int) Math.floor( ((double) classStats.getClassExp() / (double)
                        classStats.getClassMaxExp()) * 100) + "%]");
        classXPPct.setScore(1);
        Score classXP = obj.getScore("§f§l" + classStats.getClassExp() + "/" + classStats.getClassMaxExp());
        classXP.setScore(0);
        player.setScoreboard(board);

    }

    /**
     * Handles in-game time to real day/night time conversion.
     *
     * Allows for the player to see what time of day it is in game with a more readable format.
     * @param player
     * @return
     */
    public static String handleInGameTime(Player player) {

        long currentTime = player.getWorld().getFullTime();
        int hour = (int) ((currentTime / 1000) + 6) % 12;
        String minute = Integer.toString((int) ((currentTime % 1000) * 60 / 1000));

        if (hour == 0) hour = 12;
        if (Integer.parseInt(minute) < 10) minute = "0" + minute;

        String ampm = (currentTime < 18000 && currentTime > 6000) ? "PM" : "AM";

        return hour + ":" + minute + " " + ampm;

    }
}
