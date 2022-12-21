package me.tbonejdi.tboneplugins.fileadministrators;

import me.tbonejdi.tboneplugins.classes.ClassType;

import java.io.*;

public class ClassWorker {
    private File file;
    private BufferedWriter writer;
    private BufferedReader reader;


    public ClassWorker(File file) throws IOException {
        this.file = file;
        this.reader = new BufferedReader(new FileReader(file));
        this.writer = new BufferedWriter(new FileWriter(file, true));
    }

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

    public void deleteFile() {
        file.delete();
    }

    private void resetReader() throws IOException {
        reader = new BufferedReader(new FileReader(file));
    }
}
