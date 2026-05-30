package game.data.statistics;

import game.data.InputMethod;
import game.data.ScoreData;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * The Statistic class is used for holding the information about the user's play through (gameplay)
 */
public class Statistic implements Comparable<Statistic>, Serializable {
    private String playerName;
    private final int score;
    private final InputMethod inputMethod;

    private final LocalDate localDate;
    private final LocalTime localTime;

    /**
     * Constructor for setting the default values and current LocalDate and LocalTime of the Statistic's creation
     * @param playerName player's name as a String
     * @param scoreData player's scoreData
     * @param inputMethod player's chosen InputMethod
     */
    public Statistic(String playerName, ScoreData scoreData, InputMethod inputMethod) {
        this.playerName = playerName;
        this.score = scoreData.getScore();
        this.inputMethod = inputMethod;

        this.localDate = LocalDate.now();
        this.localTime = LocalTime.now();
    }

    /**
     * Constructor for settings the default values and current LocalDate and LocalTime of the Statistic's creation
     * @param scoreData player's scoreData
     * @param inputMethod player's chosen InputMethod
     */
    public Statistic(ScoreData scoreData, InputMethod inputMethod) {
        this.playerName = null;
        this.score = scoreData.getScore();
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
     * Used for setting the user's chosen name
     * @param playerName player's new name as a String
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Used for getting the final score of the user's play through (gameplay)
     * @return returns the score as an int
     */
    public int getScore() {
        return score;
    }

    /**
     * Used for getting the input method of the user's play through (gameplay)
     * @return returns the used input method as an InputMethod
     */
    public InputMethod getInputMethod() {
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
     * Used for showing the Statistics in a JTable
     * @return returns this Static as an array of Objects
     */
    public Object[] toObjectArray() {
        return new Object[]{playerName, score, inputMethod.getName(), localDate.toString(), localTime.toString()};
    }

    /**
     * Used for comparing user Statistics
     * @param o the Statistic object to be compared.
     * @return returns a positive int if this is bigger, zero if equal, a negative int if this is smaller than o
     */
    @Override
    public int compareTo(Statistic o) {
        return this.score - o.score;
    }

    @Override
    public String toString() {
        return playerName + ";" + score + ";" + inputMethod + ";" + localDate + ";" + localTime;
    }
}