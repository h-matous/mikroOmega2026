package game.background;

import game.Texture;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Random;

public class AnimatedBackground {
    private final Texture bg;

    private final int targetUPS;

    private final int targetAnimFPS;

    private int updateCounter;
    private final int frameDelay; //updates needed to switch frame

    private final Dimension frameSize;

    private final ArrayDeque<BackgroundDroplet> droplets;

    private final Color dropletColor;
    private final Color topColor;
    private final Color bottomColor;

    private final Random rnd;

    public AnimatedBackground(Dimension size, Random rnd, int targetUPS) {
        this.targetUPS = targetUPS;

        this.targetAnimFPS = 18;

        this.updateCounter = 0;
        this.frameDelay = targetUPS / targetAnimFPS;

        this.frameSize = size;

        this.rnd = rnd;

        droplets = new ArrayDeque<>();

        dropletColor = new Color(0, 255, 165, 30);
        topColor = new Color(20, 180, 145, 255);
        bottomColor = new Color(5, 105, 80, 255);

        bg = new Texture(new LinearVerticalGradientImage(size, topColor, bottomColor));
    }

    public void update() {
        updateCounter++;

        if (updateCounter >= frameDelay) {
            updateCounter = 0;


            if (!droplets.isEmpty() && droplets.getFirst().isOffScreen()) {
                droplets.removeFirst();
            }

            //Adding droplets
            droplets.add(new BackgroundDroplet(frameSize, rnd, dropletColor));


            for (BackgroundDroplet droplet : droplets) {
                droplet.update();
            }
        }
    }

    public void draw(Graphics2D gfx) {
        gfx.drawImage(bg.getImage(), 0,  0, bg.getWidth(), bg.getHeight(), null);

        for (BackgroundDroplet droplet : droplets) {
            droplet.draw(gfx);
        }
    }

}
