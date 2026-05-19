package game.screens;

import game.Canvas2D;
import game.GameLogic;
import game.VisibilityController;
import game.data.GameData;

import javax.swing.*;

public class GameScreen extends JFrame implements VisibilityController {
    private final GameData gameData;
    private final GameLogic gameLogic;

    private Canvas2D canvas2D;


    public GameScreen(GameData gameData, GameLogic gameLogic) {
        super();

        this.gameData = gameData;
        this.gameLogic = gameLogic;

        initialize();
    }

    private void initialize() {
        canvas2D = new Canvas2D(gameData, gameLogic, gameData.getGameScreenSize());

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