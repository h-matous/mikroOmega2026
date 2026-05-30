package game.screen;

import game.renderable.DrawableAndUpdatable;
import game.data.GameData;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * The RenderPanel2D is an extension of the JPanel class, it contains the gameLoop so it is also made to be on a thread other than Swing's EDT
 */
public class RenderPanel2D extends JPanel implements Runnable {
    private final int targetUPS;

    protected final GameData gameData;

    //Double buffering
    private BufferedImage frontBuffer;
    private BufferedImage backBuffer;

    private final Object bufferLock;

    private volatile boolean running;

    private final DrawableAndUpdatable drawableAndUpdatable;

    /**
     * Constructor for setting the values
     * @param gameData data of the game
     * @param frameSize the size of the parent JFrame as a viewport
     * @param drawableAndUpdatable any Object implementing this interface for updating and drawing to Graphics2D
     * @param disableListeners if
     */
    public RenderPanel2D(GameData gameData, Dimension frameSize, DrawableAndUpdatable drawableAndUpdatable, boolean disableListeners) {
        super();

        this.gameData = gameData;

        this.targetUPS = this.gameData.getConstants().getTargetUPS();

        this.setLayout(new BorderLayout());

        this.setDoubleBuffered(false);
        this.setPreferredSize(frameSize);
        this.setBackground(Color.BLACK);

        if (!disableListeners) {
            this.addMouseListener(gameData.getMouseHandler());
            this.addMouseMotionListener(gameData.getMouseHandler());
            this.addKeyListener(gameData.getKeyHandler());
        }
        this.setFocusable(true);

        frontBuffer = new BufferedImage(frameSize.width, frameSize.height, BufferedImage.TYPE_INT_ARGB);
        backBuffer = new BufferedImage(frameSize.width, frameSize.height, BufferedImage.TYPE_INT_ARGB);

        bufferLock = new Object();

        running = false;

        this.drawableAndUpdatable = drawableAndUpdatable;
    }


    /**
     * Method that will run as soon as the thread starts, holds the game loop
     */
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

            //Fixed time step
            while (accumulator >= updateInterval) {
                accumulator = accumulator - updateInterval;

                update();
            }

            renderFrame();

            //Panel Component repaint request
            this.repaint();


            //Flush pending graphics drawing operations to the screen immediately
            Toolkit.getDefaultToolkit().sync();


            restThread((long) ((updateInterval - accumulator) / 1000000));
        }
    }

    /**
     * Used for sleeping the thread for a certain amount of time (in milliseconds)
     * @param sleepTimeMs time to sleep in milliseconds
     */
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

    /**
     * Calls the drawableAndUpdatable update synchronously at targetUPS per second
     */
    public void update() {
        drawableAndUpdatable.update(gameData);
    }

    /**
     * Calls the drawableAndUpdatable draw asynchronously
     * @param gfx Graphics2D of a buffer to draw to
     */
    public void draw(Graphics2D gfx) {
        drawableAndUpdatable.draw(gfx, gameData);
    }


    /**
     * Renders the whole scene into the backBuffer and swaps the buffers
     */
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

    /**
     * Paints the whole frontBuffer
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        synchronized(bufferLock) {
            g.drawImage(frontBuffer, 0, 0, null);
        }
    }

    /**
     * Used to set the running flag (can stop the game loop)
     * @param running the new boolean that will determine if it continues to run
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Used to get the state of the game loop (if it is currently running)
     * @return returns a boolean that says if the game loop is currently running
     */
    public boolean isRunning() {
        return running;
    }
}