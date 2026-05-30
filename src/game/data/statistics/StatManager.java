package game.data.statistics;

import game.data.GameConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Used for managing all user Statistics
 */
public class StatManager implements Serializable {
    private ArrayList<Statistic> stats;

    /**
     * Empty Constructor
     */
    public StatManager() {

    }

    public void addStatistic(GameConstants gameConstants, Statistic statistic) {
        stats.add(statistic);

        Collections.sort(stats);
        Collections.reverse(stats);

        StatLoader.saveToFile(this, gameConstants.getStatManagerFilePath());
    }

    /**
     * Used for getting the first time execution StatManager (Statistic manager) instance,
     * when the program first runs, it tries to read a non-existent file, so this method gets called to create the first instance
     * @return returns the new StatManager instance
     */
    public static StatManager getFirstTimeExecutionInstance() {
        StatManager statManager = new StatManager();
        statManager.setStats(new ArrayList<>());

        return statManager;
    }

    /**
     * Used for showing the Statistics in a JTable
     * @return returns an 2D array of Objects
     */
    public Object[][] toObject2DArray() {
        Object[][] result = new Object[stats.size()][];

        for (int i = 0; i < result.length; i++) {
            result[i] = stats.get(i).toObjectArray();
        }

        return result;
    }

    /**
     * Used for getting the user Statistics
     * @return returns an ArrayList containing the user Statistics
     */
    public ArrayList<Statistic> getStats() {
        return stats;
    }

    /**
     * Used for setting the user Statistics
     * @param stats an ArrayList containing the user Statistics
     */
    public void setStats(ArrayList<Statistic> stats) {
        this.stats = stats;
    }
}