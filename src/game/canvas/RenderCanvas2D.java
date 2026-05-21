package game.canvas;

import game.data.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class RenderCanvas2D extends JPanel implements Runnable {
    private final int targetUPS;

    protected final GameData gameData;

    //Double buffering
    private BufferedImage frontBuffer;
    private BufferedImage backBuffer;

    private final Object bufferLock;

    private volatile boolean running;

    public RenderCanvas2D(GameData gameData, Dimension frameSize) {
        super();

        this.gameData = gameData;

        this.targetUPS = this.gameData.getConstants().getTargetUPS();

        this.setDoubleBuffered(false);
        this.setPreferredSize(frameSize);
        this.setBackground(Color.BLACK);


        this.addKeyListener(gameData.getKeyHandler());
        this.setFocusable(true);

        frontBuffer = new BufferedImage(frameSize.width, frameSize.height, BufferedImage.TYPE_INT_ARGB);
        backBuffer = new BufferedImage(frameSize.width, frameSize.height, BufferedImage.TYPE_INT_ARGB);

        bufferLock = new Object();

        running = false;
    }



    @Override
    public void run() {
        final double updateInterval = 1_000_000_000D / targetUPS; //time between each update in nanoseconds (100 UPS)
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

            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract void update();

    public abstract void draw(Graphics2D gfx);


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

        final BufferedImage renderedImage;

        synchronized (bufferLock) {
            renderedImage = frontBuffer;
        }

        g.drawImage(renderedImage, 0, 0, null);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}