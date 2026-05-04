package game;

import game.entity.Player;

import javax.swing.*;
import java.awt.*;


public class Canvas2D extends JPanel implements Runnable {

    private final int targetUPS = 100;

    private int frames = 0;
    private int currentFPS = 0;
    private long lastFPSCheck = System.nanoTime();

    private final TextureManager texMngr;

    private KeyHandler keyH;
    private Thread gameThread;

    private Player player;



    public Canvas2D(Dimension frameSize) {
        super();

        this.setDoubleBuffered(true);
        this.setPreferredSize(frameSize);
        this.setBackground(Color.BLACK);

        keyH = new KeyHandler();

        this.addKeyListener(keyH);
        this.setFocusable(true);

        texMngr = new TextureManager();

        player = new Player(this, keyH, texMngr, targetUPS);
    }


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
                update();
                accumulator = accumulator - updateInterval;
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
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gfx = (Graphics2D) g;

        player.draw(gfx);

        gfx.dispose();
    }
}