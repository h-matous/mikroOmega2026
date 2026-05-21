package game.background;

import game.data.GameData;

import java.awt.*;
import java.util.ArrayList;

public class BackgroundDroplet {

    private final Dimension BGsize;

    private final AnimatedBackgroundData data;


    private final int xPos;

    private final ArrayList<Integer> squares;

    private final int[] squareSizes;


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

    public boolean isOffScreen() {
        return squares.getFirst() > BGsize.height;
    }


    private int calcLenFromIndex(int index) {
        return (index + 1) * (data.getSquareLen() / data.getSquareCount());
    }

    public void update() {
        squares.add(squares.removeFirst() + data.getSquareLen()  * data.getSquareCount());
    }

    public void draw(Graphics2D gfx) {
        gfx.setColor(data.getDropletColor());

        for (int i = 0; i < squares.size(); i++) {
            int currentDropLen = squareSizes[i];
            int xOffset = (data.getSquareLen() - currentDropLen) / 2;

            gfx.fillRect(xPos + xOffset, squares.get(i), currentDropLen, currentDropLen);
        }
    }
}
