package game.background;

import game.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedBackground {
    private Texture bg;

    private final int targetUPS;

    private int targetAnimFPS;

    private int updateCounter;
    private int frameDelay; //updates needed to switch frame

    private ArrayList<BackgroundDroplet> droplets;
    private int dropletCount;

    private Color dropletColor;
    private Color topColor;
    private Color bottomColor;

    public AnimatedBackground(Dimension size, int targetUPS) {
        this.targetUPS = targetUPS;

        this.targetAnimFPS = 10;

        this.updateCounter = 0;
        this.frameDelay = targetUPS / targetAnimFPS;

        droplets = new ArrayList<>();
        dropletCount = 1;

        //dropletColor = new Color(50, 200, 200, 255);
        dropletColor = new Color(20, 180, 65, 255);
        //dropletColor = new Color(60, 180, 155, 255);
        topColor = new Color(20, 180, 145, 255);
        bottomColor = new Color(5, 105, 80, 255);

        bg = new Texture(new LinearVerticalGradientImage(size, topColor, bottomColor));
    }

    public void update() {
        updateCounter++;

        if (updateCounter >= frameDelay) {
            updateCounter = 0;

            //Adding droplets
            if (droplets.size() < dropletCount) {
                droplets.add(new BackgroundDroplet(bg.getHeight(), dropletColor));
            }

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
