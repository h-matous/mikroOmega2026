package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Queue;

public class BackgroundDroplet {
    private int squareCount;
    private int squareLen;
    private Color squareColor;


    private BufferedImage drop;

    private int screenHeight;

    private ArrayDeque<Integer> queue;

    public BackgroundDroplet(int screenHeight) {
        squareCount = 8;
        squareLen = 10;

        squareColor = Color.ORANGE;

        drop = new BufferedImage(squareLen, squareLen * squareCount, BufferedImage.TYPE_INT_ARGB);

        this.screenHeight = screenHeight;

        queue = new ArrayDeque<>();

        for (int i = 0; i < squareCount; i++) {
            queue.add(i * squareLen);
        }
    }

    private int calcLenFromIndex(int index) {
        return squareLen - (index * (squareCount / squareLen));
    }

    public void update() {
        System.out.println(queue);
        queue.addFirst(queue.removeLast() + squareLen);
    }

    public void draw(Graphics2D gfx) {
        gfx.setColor(new Color(255, 200, 0, 50));

        for (int i = 0; i < queue.size(); i++) {
            int currentDropLen = calcLenFromIndex(i);
            gfx.fillRect(0, queue.getFirst(), currentDropLen, currentDropLen);
        }
    }
}
