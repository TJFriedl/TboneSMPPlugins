package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.entity.Player;

import java.io.*;

public class TomesFileWorker {

    private boolean[] bookCollection = new boolean[9];
    private File tomeFile;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Player player;

    public TomesFileWorker(File tomeFile, Player player) throws IOException {
        this.tomeFile = tomeFile;
        this.reader = new BufferedReader(new FileReader(tomeFile));
        this.writer = new BufferedWriter(new FileWriter(tomeFile, true));
        this.player = player;
        updateBookCollection();
    }

    public void init(String username) throws IOException {
        writer.write("TOMES FILE:\n");
        writer.write("Player name:\n");
        writer.write(username + "\n");
        writer.write("\nTOMES LIST:\n\n");
        writer.write("TUTORIAL TOME\n1\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n2\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n3\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n4\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n5\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n6\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n7\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n8\n");
        writer.write("false\n");
        writer.write("DIAMOND WAND RECIPE\n9\n");
        writer.write("false\n");
        writer.close();
    }

    public void saveToFile() throws IOException {
        resetReader();
        writer = new BufferedWriter(new FileWriter(tomeFile));
        writer.write("TOMES FILE:\n");
        writer.write("Player name:\n");
        writer.write(player.getName() + "\n");
        writer.write("\nTOMES LIST:\n\n");
        writer.write("TUTORIAL TOME\n1\n");
        writer.write(bookCollection[0] + "\n");
        writer.write("DIAMOND WAND RECIPE\n2\n");
        writer.write(bookCollection[1] + "\n");
        writer.write("DIAMOND WAND RECIPE\n3\n");
        writer.write(bookCollection[2] + "\n");
        writer.write("DIAMOND WAND RECIPE\n4\n");
        writer.write(bookCollection[3] + "\n");
        writer.write("DIAMOND WAND RECIPE\n5\n");
        writer.write(bookCollection[4] + "\n");
        writer.write("DIAMOND WAND RECIPE\n6\n");
        writer.write(bookCollection[5] + "\n");
        writer.write("DIAMOND WAND RECIPE\n7\n");
        writer.write(bookCollection[6] + "\n");
        writer.write("DIAMOND WAND RECIPE\n8\n");
        writer.write(bookCollection[7] + "\n");
        writer.write("DIAMOND WAND RECIPE\n9\n");
        writer.write(bookCollection[8] + "\n");
        writer.close();
    }

    public void updateBookCollection() throws IOException {
        resetReader();
        for (int i = 0; i < 6; i++)  { reader.readLine(); }

        for (int i = 0; i < 9; i++) {
            reader.readLine();
            reader.readLine();
            String boolVal = reader.readLine();
            bookCollection[i] = Boolean.parseBoolean(boolVal);
        }
    }

    public boolean isBookDiscovered(int bookNum) {
        if ((bookNum < 0) || (bookNum > 8)) {
            player.sendMessage("Value was flagged false because number was out of range.");
            return false; // Is out of boundaries
        }
        return bookCollection[bookNum];
    }

    public void setBookStatus(int bookNum, boolean truthVal) throws IOException {
        if (bookNum >= bookCollection.length || bookNum < 0) { return; }
        // Once this conditional has been passed...
        bookCollection[bookNum] = truthVal;
    }

    public void deleteFile() {
        tomeFile.delete(); // This should delete the file?
    }

    private void resetReader() throws IOException {
        reader = new BufferedReader(new FileReader(tomeFile));
    }


}
