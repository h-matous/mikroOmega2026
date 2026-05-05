package game;

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

    public AnimatedBackground(Dimension size, int targetUPS) {
        bg = new Texture(new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB));
        this.targetUPS = targetUPS;

        this.targetAnimFPS = 10;

        this.updateCounter = 0;
        this.frameDelay = targetUPS / targetAnimFPS;

        droplets = new ArrayList<>();
        dropletCount = 1;
    }

    public void update() {
        updateCounter++;

        if (updateCounter >= frameDelay) {
            updateCounter = 0;

            //Adding droplets
            if (droplets.size() < dropletCount) {
                droplets.add(new BackgroundDroplet(bg.getHeight()));
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
