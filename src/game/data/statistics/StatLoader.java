package game.data.statistics;

import java.io.*;

/**
 * The StatLoader (loader for Statistics) is a utility class used for loading and saving user Statistics to files
 */
public class StatLoader {
    /**
     * Used for loading the saved user Statistics from a file
     * @param statisticsFileSavePath path to a file with the Statistics
     * @return returns an StatManager Object instance with the loaded Statistics
     * @throws RuntimeException when the loading fails
     */
    public static StatManager loadFromFile(String statisticsFileSavePath) throws RuntimeException {
        StatManager statManager;

        try {
            FileInputStream fis = new FileInputStream(statisticsFileSavePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            statManager = (StatManager) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File containing the user Statistics \"" + statisticsFileSavePath + "\" was not found: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("The class StatManager was not found: " + e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to initiate the communication with the file containing user Statistics: " + e.getMessage());
        }

        return statManager;
    }


    /**
     * Used for saving the current user Statistics to a file
     * @param statManager an instance of the Statistic Manager to be saved
     * @param statisticsFileSavePath path representing where to save the current user Statistics
     * @throws RuntimeException when the saving fails
     */
    public static void saveToFile(StatManager statManager, String statisticsFileSavePath) throws RuntimeException {
        try {
            FileOutputStream fos = new FileOutputStream(statisticsFileSavePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(statManager);

            oos.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot save/create the file \"" + statisticsFileSavePath + "\" Statistics were not saved: " + e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException("An I/O error occurred while writing stream header to the file \"" + statisticsFileSavePath + "\" Statistics were not saved: " + e.getMessage());
        }
    }
}