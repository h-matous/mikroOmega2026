package game.data;

/**
 * The PlayerAnimationData is used to store the data about the Player's Animation
 */
public class PlayerAnimationData {
    private String textureId;
    private int frameCount;
    private int targetAnimFPS;

    /**
     * Fully parametric constructor sets the values
     * @param textureId the ID of the Texture spriteSheet
     * @param frameCount number of frames in the spriteSheet
     * @param targetAnimFPS target Animation frames per second, later converts to amount of updates per second
     */
    public PlayerAnimationData(String textureId, int frameCount, int targetAnimFPS) {
        this.textureId = textureId;
        this.frameCount = frameCount;
        this.targetAnimFPS = targetAnimFPS;
    }

    /**
     * Used for getting the ID of the spriteSheet Texture
     * @return returns the mentioned ID as a String
     */
    public String getTextureId() {
        return textureId;
    }

    /**
     * Used for setting the ID of the spriteSheet Texture
     * @param textureId the mentioned ID as a String
     */
    public void setTextureId(String textureId) {
        this.textureId = textureId;
    }

    /**
     * Used for getting the frame count of the spriteSheet Texture
     * @return returns the frame count as an int
     */
    public int getFrameCount() {
        return frameCount;
    }

    /**
     * Used for setting the frame count of the spriteSheet Texture
     * @param frameCount the frame count as an int
     */
    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    /**
     * Used for getting the target Animation frames per second
     * @return returns the mentioned FPS as an int
     */
    public int getTargetAnimFPS() {
        return targetAnimFPS;
    }

    /**
     * Used for setting the target Animation frames per second
     * @param targetAnimFPS the mentioned FPS as an int
     */
    public void setTargetAnimFPS(int targetAnimFPS) {
        this.targetAnimFPS = targetAnimFPS;
    }
}