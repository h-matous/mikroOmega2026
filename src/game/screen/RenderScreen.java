package game.screen;

import game.canvas.RenderCanvas2D;
import game.data.GameData;

public class RenderScreen extends Screen {
    protected GameData gameData;
    protected RenderCanvas2D renderCanvas2D;

    private Thread gameThread;

    public RenderScreen(GameData gameData, RenderCanvas2D renderCanvas2D) {
        super(gameData);
        this.gameData = gameData;
        this.renderCanvas2D = renderCanvas2D;
    }

    public void startGameThread() {
        if (renderCanvas2D.isRunning()) {
            return;
        }

        renderCanvas2D.setRunning(true);
        gameThread = new Thread(renderCanvas2D);
        gameThread.start();
    }

    public void stopGameThread() {
        renderCanvas2D.setRunning(false);

        try {
            if (gameThread != null) {
                gameThread.join();
                gameThread = null;
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
