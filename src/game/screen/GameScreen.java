package game.screen;

import game.renderable.GameLogic;
import game.canvas.RenderPanel2D;
import game.data.GameData;

import javax.swing.*;

public class GameScreen extends RenderScreen {
    private final GameLogic gameLogic;


    public GameScreen(GameData gameData, GameLogic gameLogic) {
        super(gameData, new RenderPanel2D(gameData, gameData.getGameScreenSize(), gameLogic));

        this.gameLogic = gameLogic;

        initialize();
    }

    private void initialize() {
        //renderCanvas2D = new GameCanvas2D(gameData, gameLogic);

        this.setContentPane(renderPanel2D);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Monkey Banana Catch!");
        this.setPreferredSize(gameData.getGameScreenSize());

        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }
}