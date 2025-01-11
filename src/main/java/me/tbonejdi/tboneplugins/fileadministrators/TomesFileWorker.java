package me.tbonejdi.tboneplugins.fileadministrators;

import org.bukkit.entity.Player;

import java.io.*;

/**
 * Responsible for handling player tome file I/O.
 */
public class TomesFileWorker {

    private boolean[] bookCollection = new boolean[9];
    private File tomeFile;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Player player;

    /**
     * Constructor for TomeFileWorker. Takes tomeFile and player as arguments.\
     *
     * @param tomeFile
     * @param player
     * @throws IOException
     */
    public TomesFileWorker(File tomeFile, Player player) throws IOException {
        this.tomeFile = tomeFile;
        this.reader = new BufferedReader(new FileReader(tomeFile));
        this.writer = new BufferedWriter(new FileWriter(tomeFile, true));
        this.player = player;
        updateBookCollection();
    }

    /**
     * Initialize player's tome file data.
     *
     * @param username
     * @throws IOException
     */
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

    /**
     * Save player's tome information from server to tome file. Gets sent back out to server file system.
     * @throws IOException
     */
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

    /**
     * Updates books found by player. Gets saved in player data and server file eventually.
     *
     * @throws IOException
     */
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

    /**
     * Checks to see if player has discovered a specific book/tome.
     *
     * @param bookNum
     * @return
     */
    public boolean isBookDiscovered(int bookNum) {
        if ((bookNum < 0) || (bookNum > 8)) {
            player.sendMessage("Value was flagged false because number was out of range.");
            return false; // Is out of boundaries
        }
        return bookCollection[bookNum];
    }

    /**
     * Sets a player's specific book status to found/not-found.
     *
     * @param bookNum
     * @param truthVal
     * @throws IOException
     */
    public void setBookStatus(int bookNum, boolean truthVal) throws IOException {
        if (bookNum >= bookCollection.length || bookNum < 0) { return; }
        // Once this conditional has been passed...
        bookCollection[bookNum] = truthVal;
    }

    /**
     * Deletes player tome file (WARNING - use with caution)
     */
    public void deleteFile() {
        tomeFile.delete();
    }

    /**
     * Resets tome file pointer to the head of the file.
     *
     * @throws IOException
     */
    private void resetReader() throws IOException {
        reader = new BufferedReader(new FileReader(tomeFile));
    }


}
