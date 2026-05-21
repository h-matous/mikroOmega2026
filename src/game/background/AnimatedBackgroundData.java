package game.background;

import java.awt.*;

public class AnimatedBackgroundData {
    //AnimatedBackground
    private final int targetAnimFPS;

    //Droplet
    private final Color dropletColor;
    //LinearVerticalGradient
    private final Color topColor;
    private final Color bottomColor;

    //Droplet
    private final int squareCount;
    private final int squareLen;

    public AnimatedBackgroundData() {
        this.targetAnimFPS = 20;

        this.dropletColor = new Color(0, 255, 165, 30);
        this.topColor = new Color(20, 180, 145, 255);
        this.bottomColor = new Color(5, 105, 80, 255);

        this.squareCount = 20;
        this.squareLen = 32;
    }

    public int getTargetAnimFPS() {
        return targetAnimFPS;
    }

    public Color getDropletColor() {
        return dropletColor;
    }

    public Color getTopColor() {
        return topColor;
    }

    public Color getBottomColor() {
        return bottomColor;
    }

    public int getSquareCount() {
        return squareCount;
    }

    public int getSquareLen() {
        return squareLen;
    }
}
