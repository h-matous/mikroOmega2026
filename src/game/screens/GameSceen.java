package game.screens;

import game.Canvas2D;
import game.GameLogic;
import game.VisibilityController;

import javax.swing.*;
import java.awt.*;

public class GameSceen extends JFrame implements VisibilityController {
    private Canvas2D canvas2D;
    private final Dimension frameDimension;

    private final int targetUPS;
    private GameLogic gameLogic;

    public GameSceen(Dimension screenSize, GameLogic gameLogic, int targetUPS) {
        super();

        frameDimension = screenSize;

        this.targetUPS = targetUPS;
        this.gameLogic = gameLogic;

        initialize();
    }

    private void initialize() {
        canvas2D = new Canvas2D(gameLogic, frameDimension, targetUPS);

        this.setContentPane(canvas2D);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Monkey Banana Catch!");

        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }


    public void startGameThread() {
        canvas2D.startGameThread();
    }
}