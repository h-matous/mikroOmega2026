package game.screen;

import game.canvas.RenderPanel2D;
import game.data.GameData;

public class RenderScreen extends Screen {
    protected GameData gameData;
    protected RenderPanel2D renderPanel2D;

    private Thread thread;

    public RenderScreen(GameData gameData, RenderPanel2D renderPanel2D) {
        super(gameData);
        this.gameData = gameData;
        this.renderPanel2D = renderPanel2D;
    }

    public void startThread() {
        if (renderPanel2D.isRunning()) {
            return;
        }

        renderPanel2D.setRunning(true);
        thread = new Thread(renderPanel2D);
        thread.start();
    }

    public void stopThread() {
        renderPanel2D.setRunning(false);

        try {
            if (thread != null) {
                thread.join();
                thread = null;
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
