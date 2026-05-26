package game.screen;

import game.renderable.GameScene;
import game.data.GameData;

import javax.swing.*;

public class GameScreen extends RenderScreen {
    private final GameScene gameScene;


    public GameScreen(GameData gameData, GameScene gameScene) {
        super(gameData, new RenderPanel2D(gameData, gameData.getGameScreenSize(), gameScene, false));

        this.gameScene = gameScene;

        initialize();
    }

    private void initialize() {
        //renderCanvas2D = new GameCanvas2D(gameData, gameLogic);

        this.setContentPane(renderPanel2D);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Monkey Banana Catch!");

        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }
}