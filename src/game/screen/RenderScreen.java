package game.screen;

import game.data.GameData;

/**
 * The RenderScreen class is an extension of the Screen class, it holds an instance of RenderPanel2D and manages a thread
 */
public class RenderScreen extends Screen {
    protected RenderPanel2D renderPanel2D;

    private volatile Thread thread;

    /**
     * Constructor sets default values
     * @param gameData data needed for the Game
     * @param renderPanel2D a 2D render panel that will be the content pane of this RenderScreen
     */
    public RenderScreen(GameData gameData, RenderPanel2D renderPanel2D) {
        super(gameData);
        this.renderPanel2D = renderPanel2D;

        this.setContentPane(renderPanel2D);
    }

    /**
     * Used for starting a new thread
     */
    public synchronized void startThread() {
        if (renderPanel2D.isRunning()) {
            return;
        }

        renderPanel2D.setRunning(true);
        thread = new Thread(renderPanel2D, getClass().getSimpleName());
        thread.start();
    }

    /**
     * Used for stopping the running thread
     */
    public synchronized void stopThread() {
        renderPanel2D.setRunning(false);

        Thread t = thread;
        thread = null;


        try {
            if (t == Thread.currentThread()) {
                return;
            }

            if (t != null) {
                t.join();
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
    }
}