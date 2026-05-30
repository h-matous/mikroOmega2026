package game.data;


import java.io.Serializable;

/**
 * Class ScoreData is used for storing the current score as a numeric value
 */
public class ScoreData implements Comparable<ScoreData>, Serializable {
    private int score;

    /**
     * Default constructor sets the score to zero
     */
    public ScoreData() {
        reset();
    }

    /**
     * Constructor sets the score to an existing value
     * @param score the new score value as an int
     */
    public ScoreData(int score) {
        reset();
        this.score = score;
    }

    /**
     * Used for resetting the scoreData
     */
    public void reset() {
        this.score = 0;
    }

    /**
     * Used for offsetting the score
     * @param offset number representing the offset, supports negative values
     */
    public void offsetScore(int offset) {
        this.score = score + offset;
    }

    /**
     * Used for getting the score
     * @return returns an int representing the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Used for setting the score
     * @param score number representing the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Used for comparing ScoreData
     * @param o the ScoreData object to be compared.
     * @return returns a positive int if this is bigger, zero if equal, a negative int if this is smaller than o
     */
    @Override
    public int compareTo(ScoreData o) {
        return this.score - o.score;
    }

    @Override
    public String toString() {
        return Integer.toString(this.score);
    }
}