package game.screen;

import game.renderable.DrawableAndUpdatable;
import game.data.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class RenderPanel2D extends JPanel implements Runnable {
    private final int targetUPS;

    protected final GameData gameData;

    //Double buffering
    private BufferedImage frontBuffer;
    private BufferedImage backBuffer;

    private final Object bufferLock;

    private volatile boolean running;

    private final DrawableAndUpdatable drawableAndUpdatable;

    public RenderPanel2D(GameData gameData, Dimension frameSize, DrawableAndUpdatable drawableAndUpdatable, boolean disableListener) {
        super();

        this.gameData = gameData;

        this.targetUPS = this.gameData.getConstants().getTargetUPS();

        this.setDoubleBuffered(false);
        this.setPreferredSize(frameSize);
        this.setBackground(Color.BLACK);

        if (!disableListener) {
            this.addKeyListener(gameData.getKeyHandler());
        }
        this.setFocusable(true);

        frontBuffer = new BufferedImage(frameSize.width, frameSize.height, BufferedImage.TYPE_INT_ARGB);
        backBuffer = new BufferedImage(frameSize.width, frameSize.height, BufferedImage.TYPE_INT_ARGB);

        bufferLock = new Object();

        running = false;

        this.drawableAndUpdatable = drawableAndUpdatable;
    }



    @Override
    public void run() {
        final double updateInterval = 1000000000d / targetUPS; //time between each update in nanoseconds (100 UPS)
        double accumulator = 0;

        long lastTime = System.nanoTime(); //last time in nanoseconds

        long deltaTime; //difference of time between currentTime and lastTime in nanoseconds
        long currentTime; //current time in nanoseconds


        while (running) {
            currentTime = System.nanoTime();
            deltaTime = currentTime - lastTime;
            lastTime = currentTime;

            accumulator = accumulator + deltaTime;

            //Fixed timestep
            while (accumulator >= updateInterval) {
                accumulator = accumulator - updateInterval;

                update();
            }

            renderFrame();

            this.repaint();


            //Flush pending graphics drawing operations to the screen immediately
            Toolkit.getDefaultToolkit().sync();


            restThread((long) ((updateInterval - accumulator) / 1_000_000));
        }
    }

    private void restThread(long sleepTimeMs) {
        if (sleepTimeMs > 1) {
            try {
                Thread.sleep(sleepTimeMs);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                this.running = false;
            }
        }
    }

    public void update() {
        drawableAndUpdatable.update(gameData);
    }

    public void draw(Graphics2D gfx) {
        drawableAndUpdatable.draw(gfx, gameData);
    }


    public void renderFrame() {
        Graphics2D gfx = backBuffer.createGraphics();

        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gfx.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, backBuffer.getWidth(), backBuffer.getHeight());

        draw(gfx);

        gfx.dispose();

        //Buffer swapping
        synchronized (bufferLock) {
            BufferedImage temp = frontBuffer;
            frontBuffer = backBuffer;
            backBuffer = temp;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        synchronized(bufferLock) {
            g.drawImage(frontBuffer, 0, 0, null);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}