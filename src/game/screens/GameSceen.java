package game.screens;

import game.Canvas2D;
import game.VisibilityController;

import javax.swing.*;
import java.awt.*;

public class GameSceen extends JFrame implements VisibilityController {
    private Canvas2D canvas2D;
    private final Dimension frameDimension;

    public GameSceen() {
        super();

        //1920 * 0,25; 1080 * 0,8
        frameDimension = new Dimension(480, 864);

        initialize();
    }

    private void initialize() {
        canvas2D = new Canvas2D(frameDimension);

        this.setContentPane(canvas2D);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Hra");

        this.setResizable(true);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }


    public void startGameThread() {
        canvas2D.startGameThread();
    }
}