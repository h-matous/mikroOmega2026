package game.data.statistics;

import game.data.ScoreData;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * The Statistic class is used for holding the information about the user's play through (gameplay)
 */
public class Statistic implements Comparable<Statistic>, Serializable {
    private String playerName;
    private ScoreData scoreData;
    private String inputMethod;

    private LocalDate localDate;
    private LocalTime localTime;

    /**
     * Constructor for setting the default values and current LocalDate and LocalTime of the Statistic's creation
     * @param playerName player's name as a String
     * @param scoreData player's scoreDate
     * @param inputMethod player's chosen inputMethod
     */
    public Statistic(String playerName, ScoreData scoreData, String inputMethod) {
        this.playerName = playerName;
        this.scoreData = scoreData;
        this.inputMethod = inputMethod;

        this.localDate = LocalDate.now();
        this.localTime = LocalTime.now();
    }

    /**
     * Used for getting the user's chosen name
     * @return returns the player's name as a String
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Used for getting the final ScoreData of the user's play through (gameplay)
     * @return returns the ScoreData holding the final score
     */
    public ScoreData getScoreData() {
        return scoreData;
    }

    /**
     * Used for getting the input method of the user's play through (gameplay)
     * @return returns the used input method as a String
     */
    public String getInputMethod() {
        return inputMethod;
    }

    /**
     * Used for getting the LocalDate of Statistic creation
     * @return LocalDate of creation
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * Used for getting the LocalTime of Statistic creation
     * @return LocalTime of creation
     */
    public LocalTime getLocalTime() {
        return localTime;
    }


    /**
     * Used for comparing user Statistics
     * @param o the Statistic object to be compared.
     * @return returns a positive int if this is bigger, zero if equal, a negative int if this is smaller than o
     */
    @Override
    public int compareTo(Statistic o) {
        return this.scoreData.compareTo(o.scoreData);
    }
}