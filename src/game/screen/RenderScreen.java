package game.screen;

import game.data.GameData;

public class RenderScreen extends Screen {
    protected RenderPanel2D renderPanel2D;

    private volatile Thread thread;

    public RenderScreen(GameData gameData, RenderPanel2D renderPanel2D) {
        super(gameData);
        this.renderPanel2D = renderPanel2D;
    }

    public synchronized void startThread() {
        if (renderPanel2D.isRunning()) {
            return;
        }

        renderPanel2D.setRunning(true);
        thread = new Thread(renderPanel2D);
        thread.start();
    }

    public synchronized void stopThread() {
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