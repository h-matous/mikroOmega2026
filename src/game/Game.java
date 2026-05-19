package game;

import game.data.GameData;
import game.screens.GameScreen;
import game.screens.TitleScreen;

import javax.swing.*;

public class Game {
    private final GameData gameData;

    private final TitleScreen titleScreen;
    private final GameScreen gameScreen;

    private final GameLogic gameLogic;

    //TODO: Implement GameData and maybe even a Loader

    public Game() {
        setLookAndFeel();

        gameData = new GameData();

        gameLogic = new GameLogic(gameData);

        titleScreen = new TitleScreen(this);
        gameScreen = new GameScreen(gameData, gameLogic);
    }


    public void start() {
        //screens[0].show();
        titleScreen.setVisible(true);
        gameScreen.setVisible(false);
    }

    public void showGame() {
        gameScreen.setVisible(true);
        titleScreen.setVisible(false);

        gameScreen.startGameThread();
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
