package game;

import javax.swing.*;
import java.awt.*;


public class Canvas2D extends JPanel implements Runnable {

    private final int targetUPS;

    private int frames;
    private int currentFPS;
    private long lastFPSCheck;

    private final GameLogic gameLogic;

    private KeyHandler keyH;
    private Thread gameThread;


    public Canvas2D(GameLogic gameLogic, Dimension frameSize, int targetUPS) {
        super();

        frames = 0;
        currentFPS = 0;
        lastFPSCheck = System.nanoTime();


        this.targetUPS = targetUPS;

        this.setDoubleBuffered(true);
        this.setPreferredSize(frameSize);
        this.setBackground(Color.BLACK);

        this.gameLogic = gameLogic;


        keyH = gameLogic.getKeyHandler();

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    //TODO: Fix by rendering to a BufferedImage first and passing that to paintComponent
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        final double updateInterval = 1_000_000_000D / targetUPS; //time between each update in nanoseconds (60 UPS)
        double accumulator = 0;

        long lastTime = System.nanoTime(); //last time in nanoseconds

        long deltaTime; //difference of time between currentTime and lastTime in nanoseconds
        long currentTime; //current time in nanoseconds

        lastFPSCheck = System.nanoTime();

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

            frames++;

            long now = System.nanoTime();

            if (now - lastFPSCheck >= 1_000_000_000L) {
                currentFPS = frames;
                frames = 0;
                lastFPSCheck = now;

                System.out.println("FPS: " + currentFPS);
            }

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