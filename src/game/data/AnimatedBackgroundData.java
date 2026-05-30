package game.data;

import java.awt.*;

/**
 * The class AnimatedBackgroundData is used to store the data about the AnimatedBackground
 */
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


    /**
     * Constructor sets the values
     */
    public AnimatedBackgroundData() {
        this.targetAnimFPS = 20;

        this.dropletColor = new Color(0, 255, 165, 30);
        this.topColor = new Color(20, 180, 145, 255);
        this.bottomColor = new Color(5, 105, 80, 255);

        this.squareCount = 20;
        this.squareLen = 32;

    }

    /**
     * Used to get the target animated frames per second
     * @return returns the FPS as an int
     */
    public int getTargetAnimFPS() {
        return targetAnimFPS;
    }

    /**
     * Used to get the Color of the droplets
     * @return returns the mentioned color as a Color
     */
    public Color getDropletColor() {
        return dropletColor;
    }

    /**
     * Used to get the Color in the top of the LinearVerticalGradientTexture
     * @return returns the mentioned color as a Color
     */
    public Color getTopColor() {
        return topColor;
    }

    /**
     * Used to get the Color in the bottom of the LinearVerticalGradientTexture
     * @return returns the mentioned color as a Color
     */
    public Color getBottomColor() {
        return bottomColor;
    }

    /**
     * Used to get the square count of the droplets
     * @return returns the count as an int
     */
    public int getSquareCount() {
        return squareCount;
    }

    /**
     * Used to get the maximum square side length of the first square of the droplet
     * @return returns the mention side length as an int
     */
    public int getSquareLen() {
        return squareLen;
    }
}