package game;

import game.data.GameData;
import game.renderable.GameScene;
import game.screen.GameScreen;
import game.screen.TitleScreen;

import javax.swing.*;

public class Game {
    private final GameData gameData;

    private final TitleScreen titleScreen;
    private final GameScreen gameScreen;

    private final GameScene gameScene;


    public Game() {
        setLookAndFeel();

        gameData = new GameData();

        gameScene = new GameScene(gameData);

        titleScreen = new TitleScreen(this, gameData);
        gameScreen = new GameScreen(gameData, gameScene);
    }


    public void start() {
        //screens[0].show();
        titleScreen.setVisible(true);
        gameScreen.setVisible(false);

        titleScreen.startThread();
    }

    public void showGame() {
        titleScreen.stopThread();

        gameScreen.setVisible(true);
        titleScreen.setVisible(false);

        gameScreen.startThread();
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to set system LookAndFeel: " + e.getMessage());
        }
    }
}
