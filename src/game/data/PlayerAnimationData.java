package game.data;

public class PlayerAnimationData {
    private String textureId;
    private int frameCount;
    private int targetAnimFPS;

    public PlayerAnimationData(String textureId, int frameCount, int targetAnimFPS) {
        this.textureId = textureId;
        this.frameCount = frameCount;
        this.targetAnimFPS = targetAnimFPS;
    }

    public String getTextureId() {
        return textureId;
    }

    public void setTextureId(String textureId) {
        this.textureId = textureId;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getTargetAnimFPS() {
        return targetAnimFPS;
    }

    public void setTargetAnimFPS(int targetAnimFPS) {
        this.targetAnimFPS = targetAnimFPS;
    }
}