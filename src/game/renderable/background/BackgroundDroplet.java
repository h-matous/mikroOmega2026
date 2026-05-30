package game.renderable.background;

import game.renderable.DrawableAndUpdatable;
import game.data.AnimatedBackgroundData;
import game.data.GameData;

import java.awt.*;
import java.util.ArrayList;

/**
 * The class BackgroundDroplet is used for the AnimatedBackground
 */
public class BackgroundDroplet implements DrawableAndUpdatable {

    private final Dimension BGsize;

    private final AnimatedBackgroundData data;


    private final int xPos;

    private final ArrayList<Integer> squares;

    private final int[] squareSizes;


    /**
     * Constructor sets default values
     * @param gameData gameData instance
     * @param data data of the AnimatedBackground
     * @param BGsize the size of the background
     */
    public BackgroundDroplet(GameData gameData, AnimatedBackgroundData data, Dimension BGsize) {
        this.BGsize = BGsize;
        this.data = data;

        this.xPos = gameData.getRnd().nextInt(BGsize.width - data.getSquareLen());

        squares = new ArrayList<>();

        for (int i = 0; i < data.getSquareCount(); i++) {
            squares.add(i * data.getSquareLen() - data.getSquareCount() * data.getSquareLen());
        }

        squareSizes = new int[data.getSquareCount()];

        for (int i = 0; i < squareSizes.length; i++) {
            squareSizes[i] = calcLenFromIndex(i);
        }
    }

    /**
     * Used for checking if the droplet has fallen off-screen
     * @return returns a boolean to the corresponding condition
     */
    public boolean isOffScreen() {
        return squares.getFirst() > BGsize.height;
    }


    /**
     * Used for calculating the side length of every square that composes into a droplet
     * @param index index of the square (first square is the biggest, then the following ones are smaller)
     * @return returns the side length of the requested square
     */
    private int calcLenFromIndex(int index) {
        return (index + 1) * (data.getSquareLen() / data.getSquareCount());
    }

    /**
     * Used for updating the child droplet of the parent AnimatedBackground
     * @param gameData data of the Game
     */
    @Override
    public void update(GameData gameData) {
        squares.add(squares.removeFirst() + data.getSquareLen() * data.getSquareCount());
    }

    /**
     * Used for drawing the child droplet of the parent AnimatedBackground
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        gfx.setColor(data.getDropletColor());

        for (int i = 0; i < squares.size(); i++) {
            int currentDropLen = squareSizes[i];
            int xOffset = (data.getSquareLen() - currentDropLen) / 2;

            gfx.fillRect(xPos + xOffset, squares.get(i), currentDropLen, currentDropLen);
        }
    }
}
