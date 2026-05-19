package game;

import game.data.GameData;

import javax.swing.*;
import java.awt.*;


public class Canvas2D extends JPanel implements Runnable {
    private final int targetUPS;

    private final GameLogic gameLogic;

    private Thread gameThread;


    public Canvas2D(GameData gameData, GameLogic gameLogic, Dimension frameSize) {
        super();

        this.targetUPS = gameData.getConstants().getTargetUPS();

        this.setDoubleBuffered(true);
        this.setPreferredSize(frameSize);
        this.setBackground(Color.BLACK);

        this.gameLogic = gameLogic;


        this.addKeyListener(gameData.getKeyHandler());
        this.setFocusable(true);
    }

    //TODO: Fix by rendering to a BufferedImage first and passing that to paintComponent (ConcurrentModificationException in ArrayDeque)
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        final double updateInterval = 1_000_000_000D / targetUPS; //time between each update in nanoseconds (100 UPS)
        double accumulator = 0;

        long lastTime = System.nanoTime(); //last time in nanoseconds

        long deltaTime; //difference of time between currentTime and lastTime in nanoseconds
        long currentTime; //current time in nanoseconds


        while (gameThread != null) {
            currentTime = System.nanoTime();
            deltaTime = currentTime - lastTime;
            lastTime = currentTime;

            accumulator = accumulator + deltaTime;

            //Fixed timestep
            while (accumulator >= updateInterval) {
                accumulator = accumulator - updateInterval;
                update();
            }

            this.repaint();


            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        gameLogic.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gfx = (Graphics2D) g;

        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gameLogic.paint(gfx);

        gfx.dispose();
    }
}