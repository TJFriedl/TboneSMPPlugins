package me.tbonejdi.tboneplugins.testingcases;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;

public class Counter{

    public static int count;
    public BufferedReader reader;
    public BufferedWriter writer;
    public File file;

    public Counter(File file) throws IOException {
        this.file = file;
        this.reader = new BufferedReader(new FileReader(file));
        this.writer = new BufferedWriter(new FileWriter(file, true));
        count = parseCount();
    }

    public void init() throws IOException {
        writer.write(0);
        writer.close();
    }

    public void save() throws IOException {
        writer = new BufferedWriter(new FileWriter(file));
        writer.write(count);
        writer.close();
    }

    private int parseCount() throws IOException {
        resetReader();
        int count = -1;
        String line = reader.readLine();
        count = Integer.parseInt(line);

        return count;
    }

    private void resetReader() throws IOException {
        reader = new BufferedReader(new FileReader(file));
    }
}
