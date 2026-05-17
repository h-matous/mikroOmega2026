package game.background;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BackgroundDroplet {
    private final int squareCount;
    private final int squareLen;
    private final Color squareColor;

    private final Dimension frameSize;

    private final int xPos;

    private final ArrayList<Integer> squares;

    private final int[] squareSizes;

    //TODO: Fix
    public BackgroundDroplet(Dimension frameSize, Random rnd, Color squareColor) {
        //squareCount = 20;
        //squareLen = frameSize.width / 30;

        //TODO: For debugging, original is ↑ up above
        squareCount = 959;
        squareLen = frameSize.width - 1;

        this.frameSize = frameSize;

        this.squareColor = squareColor;

        this.xPos = rnd.nextInt(frameSize.width - squareLen);

        squares = new ArrayList<>();

        for (int i = 0; i < squareCount; i++) {
            squares.add(i * squareLen - squareCount * squareLen);
        }

        squareSizes = new int[squareCount];

        for (int i = 0; i < squareSizes.length; i++) {
            squareSizes[i] = calcLenFromIndex(i);
        }
    }

    public boolean isOffScreen() {
        return squares.getFirst() > frameSize.height;
    }


    private int calcLenFromIndex(int index) {
        return (index + 1) * (squareLen / squareCount);
    }

    public void update() {
        squares.add(squares.removeFirst() + squareLen  * squareCount);
    }

    public void draw(Graphics2D gfx) {
        gfx.setColor(squareColor);

        for (int i = 0; i < squares.size(); i++) {
            int currentDropLen = squareSizes[i];
            int xOffset = (squareLen - currentDropLen) / 2;

            gfx.fillRect(xPos + xOffset, squares.get(i), currentDropLen, currentDropLen);
        }
    }
}
