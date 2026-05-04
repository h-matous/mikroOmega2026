package game;

import game.screens.GameSceen;
import game.screens.TitleScreen;

import javax.swing.*;

public class Game {
    private final TitleScreen titleScreen;
    private final GameSceen gameSceen;


    public Game() {
        setLookAndFeel();

        titleScreen = new TitleScreen(this);
        gameSceen = new GameSceen();
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
