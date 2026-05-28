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
        configUI();

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

    private static void configUI() {
        setUILookAndFeel();
        setUIScalingProperty();
    }

    private static void setUILookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to set UI system LookAndFeel: " + e.getMessage());
        }
    }

    private static void setUIScalingProperty() {
        try {
            System.setProperty("sun.java2d.uiScale", "1");
        }
        catch (SecurityException | NullPointerException | IllegalArgumentException e) {
            System.out.println("Failed to set UI system Scale property: " + e.getMessage());
        }
    }
}