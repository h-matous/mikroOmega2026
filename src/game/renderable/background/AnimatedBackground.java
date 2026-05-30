package game.renderable.background;

import game.renderable.DrawableAndUpdatable;
import game.data.AnimatedBackgroundData;
import game.texture.LinearVerticalGradientTexture;
import game.texture.Texture;
import game.data.GameData;

import java.awt.*;
import java.util.ArrayDeque;


/**
 * The AnimatedBackground class is used for
 */
public class AnimatedBackground implements DrawableAndUpdatable {
    private Dimension frameSize;

    private AnimatedBackgroundData data;

    private Texture bg;


    private int updateCounter;
    private int frameDelay; //updates needed to switch frame

    private final ArrayDeque<BackgroundDroplet> droplets;


    /**
     * Constructor sets the values
     * @param gameData data of the Game
     * @param data data for the AnimatedBackground
     * @param size the AnimatedBackground size as a Dimension
     */
    public AnimatedBackground(GameData gameData, AnimatedBackgroundData data, Dimension size) {
        this.data = data;

        this.droplets = new ArrayDeque<>();

        reset(gameData, data, size);
    }

    /**
     * Used for resetting the AnimatedBackground
     */
    public void reset(GameData gameData, AnimatedBackgroundData data, Dimension size) {
        this.data = data;

        droplets.clear();

        this.updateCounter = 0;
        this.frameDelay = gameData.getConstants().getTargetUPS() / data.getTargetAnimFPS();

        this.frameSize = size;

        this.bg = new LinearVerticalGradientTexture(size, data.getTopColor(), data.getBottomColor());
    }

    /**
     * Used for updating the animation if a calculated amount of updates passed
     * @param gameData data of the Game
     */
    @Override
    public void update(GameData gameData) {
        if (!gameData.isBackgroundAnimationDisabled()) {
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
    }


    /**
     * Used for drawing the AnimatedBackground
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        gfx.drawImage(bg.getImage(), 0,  0, bg.getWidth(), bg.getHeight(), null);

        if (!gameData.isBackgroundAnimationDisabled()) {
            for (BackgroundDroplet droplet : droplets) {
                droplet.draw(gfx, gameData);
            }
        }
    }
}