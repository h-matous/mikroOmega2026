package game.entity;

import game.Texture;

public class Animation {
    private Texture[] frames;
    private int currentFrame;

    private int updateCounter;
    private int frameDelay; //updates needed to switch frame

    public Animation(Texture[] frames, int targetAnimFPS, int targetUPS) {
        this.frames = frames;
        this.currentFrame = 0;

        this.updateCounter = 0;
        this.frameDelay = targetUPS / targetAnimFPS;
    }

    public Animation(Texture spriteSheet, int frameCount, int targetAnimFPS, int targetUPS) {
        this.frames = new Texture[frameCount];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = spriteSheet.getSubTexture(0, i * (spriteSheet.getHeight() / frameCount), spriteSheet.getWidth(), spriteSheet.getHeight() / frameCount);
        }

        this.currentFrame = 0;

        this.updateCounter = 0;
        this.frameDelay = targetUPS / targetAnimFPS;
    }

    public void update() {
        updateCounter++;

        if (updateCounter >= frameDelay) {
            updateCounter = 0;
            currentFrame = (currentFrame + 1) % frames.length;
        }
    }

    public void resetCurrentFrameCounter() {
        currentFrame = 0;
    }

    public Texture getCurrentFrame() {
        return frames[currentFrame];
    }
}
