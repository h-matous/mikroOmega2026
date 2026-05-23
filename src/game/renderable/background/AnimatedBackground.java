package game.renderable.background;

import game.renderable.DrawableAndUpdatable;
import game.data.AnimatedBackgroundData;
import game.texture.LinearVerticalGradientTexture;
import game.texture.Texture;
import game.data.GameData;

import java.awt.*;
import java.util.ArrayDeque;

public class AnimatedBackground implements DrawableAndUpdatable {
    private final Dimension frameSize;

    private final AnimatedBackgroundData data;

    private final Texture bg;


    private int updateCounter;
    private final int frameDelay; //updates needed to switch frame

    private final ArrayDeque<BackgroundDroplet> droplets;


    public AnimatedBackground(GameData gameData, AnimatedBackgroundData data, Dimension size) {
        this.data = data;

        this.updateCounter = 0;
        this.frameDelay = gameData.getConstants().getTargetUPS() / data.getTargetAnimFPS();

        this.frameSize = size;

        droplets = new ArrayDeque<>();

        bg = new Texture(new LinearVerticalGradientTexture(frameSize, data.getTopColor(), data.getBottomColor()));
    }

    @Override
    public void update(GameData gameData) {
        updateCounter++;

        if (updateCounter >= frameDelay) {
            updateCounter = 0;

            //Deleting a droplet if it falls too far down
            if (!droplets.isEmpty() && droplets.getFirst().isOffScreen()) {
                droplets.removeFirst();
            }

            //Adding droplets
            droplets.add(new BackgroundDroplet(gameData, data, frameSize));

            //Updating droplets
            for (BackgroundDroplet droplet : droplets) {
                droplet.update(gameData);
            }
        }
    }


    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        gfx.drawImage(bg.getImage(), 0,  0, bg.getWidth(), bg.getHeight(), null);

        for (BackgroundDroplet droplet : droplets) {
            droplet.draw(gfx, gameData);
        }
    }
}