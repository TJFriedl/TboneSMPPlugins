package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PackageInitializer {
    private static String directory;
    private Player player;
    public PlayerInfo pInfo;
    public PlayerFileWorker fw;
    public TomesFileWorker tfw;
    public ClassWorker cw;
    public ClassInfo cInfo;


    public PackageInitializer(String directory, Player player) {
        this.directory = directory;
        this.player = player;
        initializePlayerFolder();
    }

    /**
     * Responsible for making sure that the player files are made in the corresponding folder of the player.
     */
    private void initializePlayerFolder() {
        File folder = new File(directory);

        if(!folder.exists()) {
            folder.mkdirs(); // This should physically create the directory where we want to put the player's file
        }
    }

    public void checkForPlayerLevelsFile() throws IOException {
        File f = new File(directory + "//_" + player.getName().toLowerCase() + "_lvls.txt");
        PlayerFileWorker fw;
        PlayerInfo pInfo;

        if (!f.exists() || f.length() == 0) // File has not been constructed correctly
        {
            f.createNewFile();
            fw = new PlayerFileWorker(f);
            fw.initialize(player.getName());
            pInfo = new PlayerInfo(player.getName(), 0, 0, 1000,
                    player);
        }
        else
        {
            fw = new PlayerFileWorker(f);
            pInfo = new PlayerInfo(player.getName(), fw.parseLevel(), fw.parseExp(), fw.parseMaxExp(),
                    player);
        }
        this.pInfo = pInfo;
        this.fw = fw;
    }

    public void checkForPlayerTomesFile() throws IOException {
        TomesFileWorker tfw;
        File f = new File(directory + "//_" + player.getName().toLowerCase() + "_tomes.txt");

        if (!f.exists() || f.length() == 0)
        {
            player.sendMessage("New tomes file created.");
            f.createNewFile();
            tfw = new TomesFileWorker(f, player);
            tfw.init(player.getName());
        }
        else {
            tfw = new TomesFileWorker(f, player);
            player.sendMessage("Previous tomes file found!");
        }
        tfw.updateBookCollection();
        this.tfw = tfw;
    }

    public void checkForClassInit() throws IOException {
        ClassWorker cw;
        ClassInfo cInfo;
        File f = new File(directory + "//_classes.txt");
        if (!f.exists() || f.length() == 0) {
            f.createNewFile();
            cw = new ClassWorker(f);
            cw.initialize(player.getName());
            cInfo = new ClassInfo(player, cw.parseClass(), 0, 0, 1000);
            player.sendMessage("New classes file created.");
        }
        else {
            cw = new ClassWorker(f);
            player.sendMessage("Previous class file found!");
            cInfo = new ClassInfo(player, cw.parseClass(), cw.parseLevel(), cw.parseEXP(),
                    cw.parseMaxEXP());
        }
        this.cInfo = cInfo;
        this.cw = cw;
    }
}
