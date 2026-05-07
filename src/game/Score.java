package game;

import java.awt.*;


public class Score {
    private Dimension frameSize;
    private int score;

    private Font font;

    public Score(Dimension frameSize) {
        this.frameSize = frameSize;
        score = 0;

        font = new Font("Arial", Font.BOLD, frameSize.width / 12);
    }

    public void addScore(int offset) {
        if (offset > 0) {
            score = score + offset;
        }
    }

    public int getScore() {
        return score;
    }

    public void draw(Graphics2D gfx) {
        gfx.setFont(font);

        String str = toString();

        int strWidth = gfx.getFontMetrics().stringWidth(str);

        gfx.drawString(str,  frameSize.width / 2 - strWidth / 2, frameSize.height / 2);
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}
