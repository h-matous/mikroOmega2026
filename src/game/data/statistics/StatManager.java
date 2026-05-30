package game.data.statistics;

import java.io.Serializable;
import java.util.ArrayList;

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

    /**
     * Used for getting the first time exectution StatManager (Statistic manager) instance,
     * when the program first runs, it tries to read a non-existent file, so this method gets called to create the first instance
     * @return returns the new StatManager instance
     */
    public static StatManager getFirstTimeExecutionInstance() {
        StatManager statManager = new StatManager();
        statManager.setStats(new ArrayList<>());

        return statManager;
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