package game.renderable;

import game.data.GameData;
import game.data.ScoreData;

import java.awt.*;

/**
 * The Score class implements DrawableAndUpdatable it is responsible for updating and rendering the score to the GameScreen,
 * it is sort of a wrapper over ScoreData
 */
public class Score implements DrawableAndUpdatable {
    private final ScoreData scoreData;
    private final Dimension frameSize;

    private final Color color;

    /**
     * Constructor sets the default values
     * @param scoreData data of the score
     * @param frameSize size of the frame that the Score is being drawn to
     */
    public Score(ScoreData scoreData, Dimension frameSize) {
        this.scoreData = scoreData;
        this.frameSize = frameSize;


        this.color = Color.BLACK;
    }

    /**
     * Used for adding Score after the Player Entity collects the collectable Banana Entity
     * @param offset an offset as an int
     */
    public void addScore(int offset) {
        if (offset > 0) {
            scoreData.offsetScore(offset);
        }
    }

    /**
     * Used for updating the Score at a fixed time step
     * @param gameData data of the Game
     */
    @Override
    public void update(GameData gameData) {

    }

    /**
     * Used for drawing the Score text to the gameScreen
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        gfx.setFont(gameData.getScoreFont());

        String str = toString();

        int strWidth = gfx.getFontMetrics().stringWidth(str);

        gfx.setColor(color);
        gfx.drawString(str, frameSize.width / 2 - strWidth / 2, frameSize.height / 2);
    }

    /**
     * Used for getting the Player's current Score
     * @return returns the score as an int
     */
    public int getScore() {
        return scoreData.getScore();
    }

    /**
     * Converts the Score to a String
     * @return returns a String representing the Score value
     */
    @Override
    public String toString() {
        return String.valueOf(scoreData.getScore());
    }
}