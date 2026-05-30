package game.screen;

import game.renderable.GameScene;
import game.data.GameData;

import javax.swing.*;

/**
 * The class GameScreen extends RenderScreen and represents the JFrame in which the GameScene of the Game will be played
 */
public class GameScreen extends RenderScreen {
    private final GameScene gameScene;

    /**
     * Constructor sets the values
     * @param gameData data of the Game
     * @param gameScene GameScene renderable (DrawableAndUpdatable)
     */
    public GameScreen(GameData gameData, GameScene gameScene) {
        super(gameData, new RenderPanel2D(gameData, gameData.getGameScreenSize(), gameScene, false));

        this.gameScene = gameScene;

        initialize();
    }

    /**
     * Used for initializing the GameScreen
     */
    private void initialize() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle(gameData.getConstants().getGameName());

        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }
}