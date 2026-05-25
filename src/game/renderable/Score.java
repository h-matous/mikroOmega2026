package game.renderable;

import game.data.GameData;

import java.awt.*;


public class Score implements DrawableAndUpdatable {
    private final Dimension frameSize;
    private int score;


    private final Color color;

    public Score(Dimension frameSize) {
        this.frameSize = frameSize;
        score = 0;


        this.color = Color.BLACK;
    }

    public void addScore(int offset) {
        if (offset > 0) {
            score = score + offset;
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
        gfx.drawString(str,  frameSize.width / 2 - strWidth / 2, frameSize.height / 2);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}