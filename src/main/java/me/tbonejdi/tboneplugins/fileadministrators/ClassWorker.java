package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.classes.ClassType;

import java.io.*;

/**
 * File I/O handler for player class information. Uses a write and read buffer in order to handle file operations
 * to the server file system.
 */
public class ClassWorker {
    private File file;
    private BufferedWriter writer;
    private BufferedReader reader;


    /**
     * Constructor for class worker. Uses instance of file and creates new reader/writer
     * @param file
     * @throws IOException
     */
    public ClassWorker(File file) throws IOException {
        this.file = file;
        this.reader = new BufferedReader(new FileReader(file));
        this.writer = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Initializes player files for class information.
     * @param username
     * @throws IOException
     */
    public void initialize(String username) throws IOException {

        writer.write("MAIN CLASS FILE:\n");
        writer.write("User:\n");
        writer.write(username + "\n");
        writer.write("CURRENT CLASS:\n");
        writer.write("None\n");
        writer.write("CURRENT CLASS LEVEL:\n0\n");
        writer.write("CURRENT CLASS EXP:\n0\n");
        writer.write("CURRENT CLASS MAX EXP:\n1000\n");
        // We will add more later....
        writer.close();
    }

    /**
     * Grabs the current class that a player is using
     * @return Player's class type
     * @throws IOException
     */
    public ClassType parseClass() throws IOException {
        resetReader();
        String line = reader.readLine();
        while (line != null) {
            if (line.compareTo("CURRENT CLASS:") == 0) {
                line = reader.readLine();
                break;
            }
            line = reader.readLine();
        }
        if (line == null) {
            System.out.printf("There seems to have been an error parsing class from file.");
            return ClassType.NONE;
        }

        ClassType classType = ClassType.valueOf(line.toUpperCase());
        return classType;
    }

    /**
     * Grabs the current class that a player is using
     * @return Player's current class level.
     * @throws IOException
     */
    public int parseLevel() throws IOException {
        resetReader();
        int level = -1; // Returns -1 if error

        String line = reader.readLine();
        while (line != null) {
            if (line.compareTo("CURRENT CLASS LEVEL:") == 0) {
                line = reader.readLine();
                level = Integer.parseInt(line);
                break;
            }
            line = reader.readLine();
        }
        return level;
    }

    /**
     * Grabs the current amount of experience associated with a player's current class and level.
     * @return Player's current class experience.
     * @throws IOException
     */
    public int parseEXP() throws IOException {
        resetReader();
        int EXP = -1; // Returns -1 if error

        String line = reader.readLine();
        while (line != null) {
            if (line.compareTo("CURRENT CLASS EXP:") == 0) {
                line = reader.readLine();
                EXP = Integer.parseInt(line);
                break;
            }
            line = reader.readLine();
        }
        return EXP;
    }

    /**
     * Grabs the maximum amount of experience associated with the specific level a player's class is at.
     * @return Player's current maximum experience for current class.
     * @throws IOException
     */
    public int parseMaxEXP() throws IOException {
        resetReader();
        int EXP = -1; // Returns value for error

        String line = reader.readLine();
        while (line != null) {
            if (line.compareTo("CURRENT CLASS MAX EXP:") == 0) {
                line = reader.readLine();
                EXP = Integer.parseInt(line);
                break;
            }
            line = reader.readLine();
        }
        return EXP;
    }

    /**
     * Saves player class information back to file to be sent out to server file system.
     * @param cf
     * @throws IOException
     */
    public void saveToFile(ClassInfo cf) throws IOException {
        resetReader();
        writer = new BufferedWriter(new FileWriter(file)); // Clears the file temporarily
        writer.write("MAIN CLASS FILE:\n");
        writer.write("User:\n");
        writer.write(cf.player.getName() + "\n");
        writer.write("CURRENT CLASS:\n");
        writer.write(cf.getCurrentClass()+ "\n");
        writer.write("CURRENT CLASS LEVEL:\n");
        writer.write(cf.getClassLvl() + "\n");
        writer.write("CURRENT CLASS EXP:\n");
        writer.write(cf.getClassExp() + "\n");
        writer.write("CURRENT CLASS MAX EXP:\n");
        writer.write(cf.getClassMaxExp() + "\n");
        // We will add more later....
        writer.close();
    }

    /**
     * Deletes player class file (WARNING - careful usage)
     */
    public void deleteFile() {
        file.delete();
    }

    /**
     * Resets read buffer's file pointer to the head of the file which it points to.
     * @throws IOException
     */
    private void resetReader() throws IOException {
        reader = new BufferedReader(new FileReader(file));
    }
}
