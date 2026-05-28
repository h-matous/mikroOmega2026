package game.renderable;

import game.data.GameData;
import game.data.ScoreData;

import java.awt.*;


public class Score implements DrawableAndUpdatable {
    private final ScoreData scoreData;
    private final Dimension frameSize;

    private final Color color;

    public Score(ScoreData scoreData, Dimension frameSize) {
        this.scoreData = scoreData;
        this.frameSize = frameSize;


        this.color = Color.BLACK;
    }

    public void addScore(int offset) {
        if (offset > 0) {
            scoreData.offsetScore(offset);
        }
    }

    @Override
    public void update(GameData gameData) {

    }

    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        gfx.setFont(gameData.getScoreFont());

        String str = toString();

        int strWidth = gfx.getFontMetrics().stringWidth(str);

        gfx.setColor(color);
        gfx.drawString(str, frameSize.width / 2 - strWidth / 2, frameSize.height / 2);
    }

    public int getScore() {
        return scoreData.getScore();
    }

    @Override
    public String toString() {
        return String.valueOf(scoreData.getScore());
    }
}