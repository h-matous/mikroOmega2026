package game.utilities;

import game.texture.Texture;

/**
 * Acts as an animated spriteSheet Texture holder that can get updated to another frame, used for animated Entities (Player)
 */
public class Animation {
    private final Texture[] frames;
    private int currentFrame;

    private int updateCounter;
    private final int frameDelay; //updates needed to switch frame

    private final boolean isStill;

    public Animation(Texture[] frames, int targetAnimFPS, int targetUPS) {
        this.frames = frames;
        this.currentFrame = 0;

        this.updateCounter = 0;
        this.frameDelay = targetUPS / targetAnimFPS;

        this.isStill = frames.length <= 1;
    }

    public Animation(Texture spriteSheet, int frameCount, int targetAnimFPS, int targetUPS) {
        //Checking if there was no failure when loading the Texture
        if (!spriteSheet.isDefault()) {
            this.frames = new Texture[frameCount];
            for (int i = 0; i < frames.length; i++) {
                frames[i] = spriteSheet.getSubTexture(0, i * (spriteSheet.getHeight() / frameCount), spriteSheet.getWidth(), spriteSheet.getHeight() / frameCount);
            }

            this.frameDelay = targetUPS / targetAnimFPS;
        }
        //If using DEFAULT_TEXTURE, we limit updating, since we only have 1 frame
        else {
            this.frames = new Texture[]{spriteSheet};
            this.frameDelay = targetUPS;
        }


        this.currentFrame = 0;

        this.updateCounter = 0;

        this.isStill = frameCount <= 1;
    }

    public void update() {
        if (!isStill) {
            updateCounter++;

            if (updateCounter >= frameDelay) {
                updateCounter = 0;
                currentFrame = (currentFrame + 1) % frames.length;
            }
        }
    }

    public void resetCurrentFrameCounter() {
        currentFrame = 0;
    }

    public Texture getCurrentFrame() {
        return frames[currentFrame];
    }
}