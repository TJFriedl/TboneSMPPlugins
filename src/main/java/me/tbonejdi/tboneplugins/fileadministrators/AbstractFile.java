package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AbstractFile {

    protected Main main;
    private File file;
    protected FileConfiguration config;

    public AbstractFile(Main main, String fileName) {
        this.main = main;
        this.file = new File(main.getDataFolder(), fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
