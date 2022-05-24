package me.tbonejdi.tboneplugins.fileadministrators;

import java.io.*;

public class PlayerFileWorker {

    private File currentFile;
    private BufferedWriter writer;
    private BufferedReader reader;

    /*
     * Default constructor
     */
    public PlayerFileWorker(File currentFile) throws IOException {
        this.currentFile = currentFile;
        this.reader = new BufferedReader(new FileReader(currentFile));
        this.writer = new BufferedWriter(new FileWriter(currentFile, true));

    }

    public void initialize(String username) throws IOException {
        // Firstly, we are assuming that the current file we are looking at is new, so
        // it should be empty...
        writer.write("LEVELS FILE:\n");
        writer.write("Player Info:\n");
        writer.write(username + "\n");
        writer.write("CURRENT LVL:\n0\n");
        writer.write("EXP:\n0\n");
        writer.write("EXP NEEDED:\n1000\n");
        writer.close();
    }

    public void saveToFile(PlayerInfo p) throws IOException {
        resetReader();
        writer = new BufferedWriter(new FileWriter(currentFile));
        writer.write("LEVELS FILE:\n");
        writer.write("Player Info:\n");
        writer.write(p.getName() + "\n");
        writer.write("CURRENT LVL:\n" + p.getLevel() + "\n");
        writer.write("EXP:\n" + p.getExp() + "\n");
        writer.write("EXP NEEDED:\n" + p.getMaxExp() + "\n");
        writer.close();
    }

    public int parseLevel() throws IOException {
        resetReader();
        String line = reader.readLine();
        int level = -1;
        while (line != null) {
            if (line.compareTo("CURRENT LVL:") == 0) {
                line = reader.readLine();
                level = Integer.parseInt(line);
                break;
            }
            line = reader.readLine();
        }
        return level;
    }

    public int parseExp() throws IOException {
        resetReader();
        String line = reader.readLine();
        int exp = -1;
        while (line != null) {
            if (line.compareTo("EXP:") == 0) {
                line = reader.readLine();
                exp = Integer.parseInt(line);
                break;
            }
            line = reader.readLine();
        }

        return exp;
    }

    public int parseMaxExp() throws IOException {
        resetReader();
        String line = reader.readLine();
        int maxExp = -1;
        while (line != null) {
            if (line.compareTo("EXP NEEDED:") == 0) {
                line = reader.readLine();
                maxExp = Integer.parseInt(line);
                break;
            }
            line = reader.readLine();
        }

        return maxExp;
    }

    public void deleteFile() {
        currentFile.delete();
    }

    /**
     * This method is meant to re-initialize the reader to the beginning of a file
     * every time a method in this class is called.
     *
     */
    private void resetReader() throws IOException {
        reader = new BufferedReader(new FileReader(currentFile));
    }
}
