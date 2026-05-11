package game;

import game.screens.GameSceen;
import game.screens.TitleScreen;

import javax.swing.*;
import java.awt.*;

public class Game {
    private final TitleScreen titleScreen;
    private final GameSceen gameSceen;

    private final int targetUPS;

    private final Dimension screenSize;

    private final GameLogic gameLogic;

    //TODO: Implement GameData and maybe even a Loader

    public Game() {
        setLookAndFeel();

        targetUPS = 100;

        //1920 * 0,5; 1080 * 0,8
        screenSize = new Dimension(960, 864);

        gameLogic = new GameLogic(screenSize, new KeyHandler(), targetUPS);

        titleScreen = new TitleScreen(this);
        gameSceen = new GameSceen(screenSize, gameLogic, targetUPS);
    }


    public void start() {
        //screens[0].show();
        titleScreen.setVisible(true);
        gameSceen.setVisible(false);
    }

    public void showGame() {
        gameSceen.setVisible(true);
        titleScreen.setVisible(false);

        gameSceen.startGameThread();
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
